package com.wsw.context;

import com.wsw.annotation.XwellAutoWired;
import com.wsw.annotation.XwellComponent;
import com.wsw.exception.DIException;
import com.wsw.exception.IOCException;
import com.wsw.home.Home;
import com.wsw.home.Test1;
import com.wsw.support.XwellBeanDefinitionReader;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName : ApplicationContex
 * @Description : 容器入口
 * @Author : wsw
 * @Date: 2020-05-11 13:34
 */
public class XwellApplicationContext implements XwellBeanFactory{

    private XwellBeanDefinitionReader beanDefinitionReader;
    /**
     * bean的定义缓存 key-bean工厂名，value-bean的定义 (扫描包下所有class)
     */
    protected final Map<String,XwellBeanDefinition> beanDefinitionMapF = new ConcurrentHashMap<String, XwellBeanDefinition>();
    /**
     * bean的定义缓存 key-bean全限定名，value-bean的定义 (扫描包下所有class)
     */
    protected final Map<String,XwellBeanDefinition> beanDefinitionMapC = new ConcurrentHashMap<String, XwellBeanDefinition>();
    /**
     * IOC容器,key-bean全限定名
     */
    protected final Map<String,XwellBeanWrapper> factoryBeanCache = new ConcurrentHashMap<String, XwellBeanWrapper>();



    private String[] configs;

    public XwellApplicationContext(String... configs){

        try {
            this.configs = configs;
            //"application.properties"
            this.beanDefinitionReader = new XwellBeanDefinitionReader(configs[0]);
            List<XwellBeanDefinition> beanDefinitions = beanDefinitionReader.loadBeanDefinition();
            doRegisterBeanDefinition(beanDefinitions);
            doAutowrited();

        } catch (Exception e) {
            System.out.println("加载XwellApplicationContex异常");
            e.printStackTrace();
        }


    }

    //这里处理非延迟加载的对象
    private void doAutowrited() throws Exception {
        f1:for (Map.Entry<String, XwellBeanDefinition> entry : beanDefinitionMapC.entrySet()) {
            XwellBeanDefinition beanDefinition = entry.getValue();
            if(beanDefinition.isLazyInit()){
                continue f1;
            }
            String className = entry.getKey();
            try {
                getBean(className);
            }catch (Exception e){
                System.out.println("doAutowrited创建对象异常");
                throw e;
            }
        }
    }

    /**
     * 将bean定义注册到map中
     */
    private void doRegisterBeanDefinition(List<XwellBeanDefinition> beanDefinitions) throws IOCException {

        //检查
        for (XwellBeanDefinition beanDefinition : beanDefinitions) {
            String factoryBeanName = beanDefinition.getFactoryBeanName();
            if(beanDefinitionMapF.containsKey(factoryBeanName)){
                throw new IOCException("factoryBeanName="+factoryBeanName+"已存在");
            }
            beanDefinitionMapF.put(factoryBeanName,beanDefinition);
            beanDefinitionMapC.put(beanDefinition.getBeanClassName(),beanDefinition);
        }

    }

    private XwellBeanDefinition getBeanDefinition(String beanName){
        XwellBeanDefinition beanDefinition;
        if((beanDefinition = beanDefinitionMapF.get(beanName)) == null){
            beanDefinition = beanDefinitionMapC.get(beanName);
        }
        return beanDefinition;
    }

    public Object getBean(String beanName) throws IOCException, DIException {

        Object instance;
        XwellBeanDefinition beanDefinition = getBeanDefinition(beanName);

        if(beanDefinition == null){
            throw new IOCException(String.format("未找到beanName=%s对应的beanDefinition",beanName));
        }
        //反射处理，实例化bean
        instance = instantiateBean(beanDefinition);
        XwellBeanWrapper beanWrapper = new XwellBeanWrapper(instance);
        factoryBeanCache.put(beanDefinition.getBeanClassName(),beanWrapper);
        //依赖注入
        populateBean(beanName,beanDefinition,beanWrapper);

        return this.factoryBeanCache.get(beanDefinition.getBeanClassName()).getInstance();
    }

    private void populateBean(String beanName, XwellBeanDefinition beanDefinition, XwellBeanWrapper beanWrapper)
            throws IOCException, DIException {

        Object instance = beanWrapper.getInstance();
        Class instanceClass = beanWrapper.getInstanceClass();
        String beanClassName = beanDefinition.getBeanClassName();

        Field[] fields = instanceClass.getDeclaredFields();
        f1:for (Field field : fields) {
            if(!field.isAnnotationPresent(XwellAutoWired.class)){
                continue f1;
            }
            XwellAutoWired autoWired = field.getAnnotation(XwellAutoWired.class);
            String autoWiredName = autoWired.value().trim();
            //为空则默认为className
            if("".equals(autoWiredName)){
                autoWiredName = field.getType().getName();
            }
            field.setAccessible(true);
            //查询需要注入的对象在xwell-spring容器中是否存在
            //如果不存在则创建并注册该对象实例到容器中
            String fieldClassName;
            if(factoryBeanCache.get(fieldClassName=field.getType().getName()) == null){
                getBean(autoWiredName);
            }
            try {
                field.set(instance,this.factoryBeanCache.get(fieldClassName).getInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new DIException(String.format("注入%s对象异常",beanClassName));
            }

        }

    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Home home = new Home();
        Class<Test1> test1Class = Test1.class;

        Field test1 = home.getClass().getDeclaredField("test1");
        test1.setAccessible(true);
        test1.set(home,new Test1());
        System.out.println(home);

    }

    /**
     * 创建对象实例/AOP代理对象
     */
    private Object instantiateBean(XwellBeanDefinition beanDefinition) throws IOCException {
        String beanClassName = beanDefinition.getBeanClassName();
        Class<?> clazz = null;
        try {
            if(factoryBeanCache.containsKey(beanClassName)){
                return factoryBeanCache.get(beanClassName).getInstance();
            }
            clazz = Class.forName(beanClassName);
            //检查是否需要实例化
            boolean b = clazz.isAnnotationPresent(XwellComponent.class);
            if(!b){
                System.out.println(String.format("%s未被标注自动注入注解，请检查配置或代码。",beanClassName));
                throw new IOCException(String.format("%s未被标注自动注入注解，请检查配置或代码。",beanClassName));
            }

            Object o = clazz.newInstance();
            System.out.println(String.format("创建%s实例完成",beanClassName));
            return o;

        } catch (Exception e) {
            System.out.println(String.format("创建%s实例异常",beanClassName));
            e.printStackTrace();
            throw new IOCException(String.format("创建%s实例异常",beanClassName));
        }
    }



    public <T> T getBean(Class<T> clazz) throws IOCException,DIException{
        return (T) getBean(clazz.getName());
    }


}