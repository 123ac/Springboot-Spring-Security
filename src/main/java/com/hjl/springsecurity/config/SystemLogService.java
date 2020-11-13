package com.hjl.springsecurity.config;

import java.lang.annotation.*;

/**
 * @Author: hjl
 * @Date: 2020/11/13 0013 10:22
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLogService {
    String description() default "";
}