package com.wsw.common;

/**
 * @ClassName : Func
 * @Description : 函数库
 * @Author : wsw
 * @Date: 2020-05-13 16:03
 */
public class Func {

    public static String toLowerFristCase(String n){
        if(n == null){
            return null;
        }
        char[] chars = n.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}