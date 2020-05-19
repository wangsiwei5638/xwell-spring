package com.wsw.annotation;


import java.lang.annotation.*;

/**
 *  web请求参数，用于接收运行时实参
 */
@Target({ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XwellRequestParam {

    String value() default "";

    boolean required() default true;

}
