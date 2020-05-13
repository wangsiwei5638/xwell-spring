package com.wsw.exception;

/**
 * @ClassName : WEBException
 * @Description : webmvc异常
 * @Author : wsw
 * @Date: 2020-05-13 14:47
 */
public class WEBException extends Exception{
    public WEBException() {
        super();
    }
    public WEBException(String s) {
        super(s);
    }
}