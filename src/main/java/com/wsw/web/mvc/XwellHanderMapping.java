package com.wsw.web.mvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @ClassName : XwellHanderMapping
 * @Description : 处理器映射器
 * @Author : wsw
 * @Date: 2020-05-18 10:23
 */
public class XwellHanderMapping {

    /**
     * controller实例对象
     */
    private Object controller;
    /**
     * URL映射的方法
     */
    private Method method;
    /**
     * URL
     */
    private Pattern urlPattern;


    public XwellHanderMapping(Object controller, Method method, Pattern urlPattern) {
        this.controller = controller;
        this.method = method;
        this.urlPattern = urlPattern;
    }

    public XwellHanderMapping() {
        super();
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(Pattern urlPattern) {
        this.urlPattern = urlPattern;
    }
}