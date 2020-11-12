package com.hjl.springsecurity.entity;

import java.io.Serializable;

/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 14:45
 */
public class SysUser implements Serializable {
    static final long serialVersionUID= 1l;

    private Integer id;
    private String name;
    private String password;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
