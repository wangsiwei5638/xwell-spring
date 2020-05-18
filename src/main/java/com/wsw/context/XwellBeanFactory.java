package com.wsw.context;

import com.wsw.exception.DIException;
import com.wsw.exception.IOCException;

/**
 * @ClassName : XwellBeanFactory
 * @Description : 容器工厂
 * @Author : wsw
 * @Date: 2020-05-13 14:26
 */
public interface XwellBeanFactory {

    /**
     * 通过class获取bean
     */
    <T> T getBean(Class<T> clazz) throws IOCException, DIException ;

    /**
     * 通过工厂名称获取bean，
     * <p>例如 @XwellAutoWired("beanName")</p>
     */
    Object getBean(String beanName) throws IOCException, DIException ;

}
