package com.wsw.annotation;

import java.lang.annotation.*;

/**
 * 用于URL映射
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XwellRequestMapping {
    String value() ; //default "";
}
