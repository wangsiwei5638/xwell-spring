package com.wsw.exception;

/**
 * @ClassName : DIException
 * @Description : 注入异常
 * @Author : wsw
 * @Date: 2020-05-13 14:47
 */
public class DIException extends Exception{
    public DIException() {
        super();
    }
    public DIException(String s) {
        super(s);
    }
}