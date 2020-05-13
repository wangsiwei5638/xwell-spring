package com.wsw.annotation;

import java.lang.annotation.*;

/**
 * IOC组件，被此注解标注后，将被Xwell-spring容器托管
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XwellComponent {
    String value() default "";
}
