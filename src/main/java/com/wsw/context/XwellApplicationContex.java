package com.wsw.context;

import com.wsw.exception.DIException;
import com.wsw.exception.IOCException;
import com.wsw.support.XwellBeanDefinitionReader;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName : ApplicationContex
 * @Description : 容器入口
 * @Author : wsw
 * @Date: 2020-05-11 13:34
 */
public class XwellApplicationContex implements XwellBeanFactory{

    private XwellBeanDefinitionReader beanDefinitionReader;
    /**
     * bean的定义缓存 key-bean工厂名，value-bean的定义
     */
    protected final Map<String,XwellBeanDefinition> beanDefinitionMapF = new ConcurrentHashMap<String, XwellBeanDefinition>();
    /**
     * bean的定义缓存 key-bean全限定名，value-bean的定义
     */
    protected final Map<String,XwellBeanDefinition> beanDefinitionMapC = new ConcurrentHashMap<String, XwellBeanDefinition>();
    /**
     * IOC容器
     */
    protected final Map<String,XwellBeanWrapper> factoryBeanCache = new ConcurrentHashMap<String, XwellBeanWrapper>();



    private String[] configs;

    public XwellApplicationContex(String... configs){

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


    //TODO
    public Object getBean(String beanName) throws IOCException, DIException {

        Object instance;
        XwellBeanDefinition beanDefinition;
        if((beanDefinition = beanDefinitionMapF.get(beanName)) == null){
            beanDefinition = beanDefinitionMapC.get(beanName);
        }
        if(beanDefinition == null){
            throw new IOCException(String.format("未找到beanName=%s对应的beanDefinition",beanName));
        }
        //反射处理
        instance = instantiateBean(beanDefinition);
        return null;
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
            Object o = clazz.newInstance();
            XwellBeanWrapper xwellBeanWrapper = new XwellBeanWrapper();
            xwellBeanWrapper.setInstance(o);
            xwellBeanWrapper.setInstanceClass(clazz);
            factoryBeanCache.put(beanClassName,xwellBeanWrapper);
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