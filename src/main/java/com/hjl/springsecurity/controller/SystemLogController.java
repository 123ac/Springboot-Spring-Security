package com.hjl.springsecurity.controller;

import java.lang.annotation.*;

/**
 * @Author: hjl
 * @Date: 2020/11/13 0013 10:20
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用在参数和方法上
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Documented//表明这个注解应该被 javadoc工具记录
public @interface SystemLogController {
    String description() default "";
}