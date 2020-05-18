package com.wsw.home;

import com.wsw.annotation.XwellComponent;
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
@XwellComponent
public class Test1 {
    public static void main(String[] args) throws Exception {


    }

    public void say(){
        System.out.println("hello2");
    }
}