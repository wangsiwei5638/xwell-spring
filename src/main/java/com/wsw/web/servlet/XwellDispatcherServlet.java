package com.wsw.web.servlet;

import com.wsw.context.XwellApplicationContext;
import com.wsw.context.XwellBeanFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    /**
     * 请求调度器，执行handler
     */
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //根据请求URL获取handerMapping

        //根据handerMapping获取handerAdapter

        //执行hander，获取modeAndView

        //结果处理

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

    private void initHandlerAdapters(XwellApplicationContext context) {

    }

    private void initHandlerMappings(XwellApplicationContext context) {
    }


}