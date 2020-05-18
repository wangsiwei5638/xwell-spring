package com.wsw.web.servlet;

import com.wsw.annotation.XwellComponent;
import com.wsw.annotation.XwellRequestMapping;
import com.wsw.context.XwellApplicationContext;
import com.wsw.context.XwellBeanDefinition;
import com.wsw.context.XwellBeanFactory;
import com.wsw.exception.DIException;
import com.wsw.exception.IOCException;
import com.wsw.web.mvc.XwellHanderAdapter;
import com.wsw.web.mvc.XwellHanderMapping;
import com.wsw.web.mvc.XwellViewResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;


/**
 * @ClassName : XwellDispatcherServlet
 * @Description : 调度servlet
 * @Author : wsw
 * @Date: 2020-05-11 13:35
 */
public class XwellDispatcherServlet extends XwellFrameworkServlet {
//    XwellApplicationContex applicationContex = new XwellApplicationContex("application.properties");

    //Bean工厂 IOC容器
    private XwellBeanFactory applicationContext;
    private List<XwellHanderMapping> handerMappings = new ArrayList<>();
    private Map<XwellHanderMapping,XwellHanderAdapter> handerAdapters = new ConcurrentHashMap<>();
    private List<XwellViewResolver> viewResolvers = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        long s = System.currentTimeMillis();
        System.out.println("初始化开始");
        //容器
        this.applicationContext = new XwellApplicationContext("application.properties");
        //mvc组件
        initStrategies((XwellApplicationContext) applicationContext);
        System.out.println(String.format("初始化完成，用时%s秒",(System.currentTimeMillis()-s)/1000));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    /**
     * 请求调度器，执行handler
     */
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //根据请求URL获取handerMapping
        XwellHanderMapping handerMapping = getHandler(request);

        //根据handerMapping获取handerAdapter

        //执行hander，获取modeAndView

        //结果处理

    }

    private XwellHanderMapping getHandler(HttpServletRequest request) {



        return null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            //todo initHandlerExceptionResolvers
            e.printStackTrace();
        }


    }


    /**
     * 初始化
     */
    protected void initStrategies(XwellApplicationContext context) {
//        initMultipartResolver(context);
//        initLocaleResolver(context);
//        initThemeResolver(context);
        initHandlerMappings(context);
        initHandlerAdapters(context);
//        initHandlerExceptionResolvers(context);
//        initRequestToViewNameTranslator(context);
        initViewResolvers(context);
//        initFlashMapManager(context);
    }

    private void initViewResolvers(XwellApplicationContext context) {

    }

    /**
     * 初始化handerAdapter
     */
    private void initHandlerAdapters(XwellApplicationContext context) {


    }

    /**
     * 初始化handerMapping
     */
    private void initHandlerMappings(XwellApplicationContext context) {
        Map<String, XwellBeanDefinition> beanDefinitions = context.getBeanDefinitions();
        if (beanDefinitions.size() == 0){
            return;
        }
        try {
            f1:for (Map.Entry<String, XwellBeanDefinition> entry : beanDefinitions.entrySet()) {
                String beanClassName = entry.getKey();
                Class<?> clazz = null;
                try {
                    clazz = Class.forName(beanClassName);
                    //检查是否为xwell-spring托管对象
                    if(!clazz.isAnnotationPresent(XwellComponent.class)){
                        continue f1;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    continue f1;
                }
                Object bean = context.getBean(beanClassName);

                String url = "";
                if(clazz.isAnnotationPresent(XwellRequestMapping.class)){
                    XwellRequestMapping annotation = clazz.getAnnotation(XwellRequestMapping.class);
                    String value = annotation.value();
                    url = value;
                }
//                Method[] methods = clazz.getDeclaredMethods();
                f2:for (Method method : clazz.getMethods()) {
                    if(!method.isAnnotationPresent(XwellRequestMapping.class)){
                        continue f2;
                    }
                    XwellRequestMapping annotation = method.getAnnotation(XwellRequestMapping.class);
                    String regex = ("/" + url + "/" + annotation.value().replaceAll("\\*",".*"))
                            .replaceAll("/+","/");
                    Pattern pattern = Pattern.compile(regex);
                    handerMappings.add(new XwellHanderMapping(bean,method,pattern));

                    System.out.println("加载" + regex + "," + method.toString());
                }

            }
        } catch (IOCException e) {
            e.printStackTrace();
        } catch (DIException e) {
            e.printStackTrace();
        }


    }


}