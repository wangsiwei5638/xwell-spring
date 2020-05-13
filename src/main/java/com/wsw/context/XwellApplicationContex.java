package com.wsw.context;

/**
 * @ClassName : ApplicationContex
 * @Description : 容器入口
 * @Author : wsw
 * @Date: 2020-05-11 13:34
 */
public class XwellApplicationContex {


    public Object getBean(String beanName){
        return null;
    }


    public Object getBean(Class clazz){
        return getBean(clazz.getName());
    }


}