package com.wsw.home;


import com.wsw.annotation.XwellAutoWired;
import com.wsw.annotation.XwellComponent;
import com.wsw.context.XwellApplicationContex;
import com.wsw.exception.DIException;
import com.wsw.exception.IOCException;

import java.util.regex.Pattern;

@XwellComponent
public class Home {

    @XwellAutoWired
    private Test1 test1;

    public static void main(String[] args) throws Exception {
//        System.out.println(Home.class.isAnnotationPresent(XwellComponent.class));
        XwellApplicationContex applicationContex = new XwellApplicationContex("application.properties");
        Home bean = applicationContex.getBean(Home.class);
    }


    public void say(){
        System.out.println("hello");
    }
}
