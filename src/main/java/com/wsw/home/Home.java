package com.wsw.home;


import com.wsw.annotation.XwellAutoWired;
import com.wsw.annotation.XwellComponent;
import com.wsw.annotation.XwellRequestMapping;
import com.wsw.context.XwellApplicationContext;

@XwellComponent
@XwellRequestMapping("/home")
public class Home {

    @XwellAutoWired
    private Test1 test1;

    public static void main(String[] args) throws Exception {
        System.out.println( String.format("初始化完成，用时%s秒",1000));
    }


    @XwellRequestMapping("/say")
    public void say(){
        System.out.println("hello");
    }
}
