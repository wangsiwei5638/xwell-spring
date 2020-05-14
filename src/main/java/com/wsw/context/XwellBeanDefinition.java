package com.wsw.context;

import com.wsw.common.Constant;

/**
 * @ClassName : XwellBeanDefinition
 * @Description : bean定义
 * @Author : wsw
 * @Date: 2020-05-11 13:45
 */
public class XwellBeanDefinition {

    /**
     * bean的全限定名 ,例如 com.wsw.context.XwellBeanDefinition
     */
    private String beanClassName;
    /**
     * bean在xwell-spring工厂中的名称
     */
    private String factoryBeanName;
    /**
     * 是否懒加载
     */
    private boolean isLazyInit = true;
    /**
     * bean的生命周期
     * @see Constant
     */
    private String scope = Constant.SCOPE_SINGLETON.name;


    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isLazyInit() {
        return isLazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        isLazyInit = lazyInit;
    }

    @Override
    public String toString() {
        return "XwellBeanDefinition{" +
                "beanClassName='" + beanClassName + '\'' +
                ", factoryBeanName='" + factoryBeanName + '\'' +
                '}';
    }
}