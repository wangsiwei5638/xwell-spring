package com.wsw.context;

/**
 * @ClassName : XwellBeanDefinition
 * @Description : bean定义
 * @Author : wsw
 * @Date: 2020-05-11 13:45
 */
public class XwellBeanDefinition {

    private String beanClassName;
    private String factoryBeanName;
    private Boolean lazyInit = true;


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

    @Override
    public String toString() {
        return "XwellBeanDefinition{" +
                "beanClassName='" + beanClassName + '\'' +
                ", factoryBeanName='" + factoryBeanName + '\'' +
                '}';
    }
}