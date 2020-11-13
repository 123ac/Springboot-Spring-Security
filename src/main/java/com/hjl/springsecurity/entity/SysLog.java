package com.hjl.springsecurity.entity;

import java.io.Serializable;
/**
 * @Author: hjl
 * @Date: 2020/11/13 0013 9:54
 */
public class SysLog implements Serializable {
    private Long id;

    private String uri;//请求接口

    private String method;//请求方式

    private String MethodDescribe;//描述

    private String params;//参数

    private String username; //用户名

    private String ip; //ip地址

    private String createDate; //操作时间

    private String browser;//浏览器类型

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethodDescribe() {
        return MethodDescribe;
    }

    public void setMethodDescribe(String methodDescribe) {
        MethodDescribe = methodDescribe;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
