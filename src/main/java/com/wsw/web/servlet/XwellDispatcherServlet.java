package com.wsw.web.servlet;

import com.wsw.context.XwellApplicationContex;
import com.wsw.exception.DIException;
import com.wsw.exception.IOCException;
import com.wsw.home.Home;
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

    }

    private void initStrategies(XwellApplicationContex applicationContex) {

    }


    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {


        try {
            XwellApplicationContex applicationContex = new XwellApplicationContex("application.properties");
            Home bean = applicationContex.getBean(Home.class);
            bean.say();
        } catch (IOCException e) {
            e.printStackTrace();
        } catch (DIException e) {
            e.printStackTrace();
        }

    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}