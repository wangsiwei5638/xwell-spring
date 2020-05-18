package com.wsw.context;

/**
 * @ClassName : XwellBeanWrapper
 * @Description : 原生对象或代理对象的包装类
 * @Author : wsw
 * @Date: 2020-05-11 13:50
 */
public class XwellBeanWrapper {

    private Object instance;
    private Class instanceClass;

    public XwellBeanWrapper() {
        super();
    }

    public XwellBeanWrapper(Object instance) {
        this.instance = instance;
        this.instanceClass = instance.getClass();
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public Class getInstanceClass() {
        return instanceClass;
    }

    public void setInstanceClass(Class instanceClass) {
        this.instanceClass = instanceClass;
    }
}