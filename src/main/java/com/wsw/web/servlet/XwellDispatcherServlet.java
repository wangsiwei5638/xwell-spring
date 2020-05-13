package com.wsw.web.servlet;

import com.wsw.context.XwellApplicationContex;
import com.wsw.exception.IOCException;
import com.wsw.support.XwellBeanDefinitionReader;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
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


    public void init(ServletConfig config) throws ServletException {
//        System.out.println("init");
//        XwellBeanDefinitionReader xwellBeanDefinitionReader = new XwellBeanDefinitionReader();
//        xwellBeanDefinitionReader.init("application.properties");
//
//        XwellApplicationContex applicationContex = null;//todo
//        initStrategies(applicationContex);

    }

    private void initStrategies(XwellApplicationContex applicationContex) {

    }


    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        try {
            new XwellBeanDefinitionReader("application.properties").loadBeanDefinition();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOCException e) {
            e.printStackTrace();
        }

    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}