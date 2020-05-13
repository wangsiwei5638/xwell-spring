package com.wsw.home;

import com.wsw.exception.IOCException;
import com.wsw.support.XwellBeanDefinitionReader;

import java.io.File;
import java.net.URL;

/**
 * @ClassName : Test1
 * @Description : 1
 * @Author : wsw
 * @Date: 2020-05-13 15:01
 */
public class Test1 {
    public static void main(String[] args) throws Exception {

        new XwellBeanDefinitionReader("application.properties").loadBeanDefinition();

    }

}