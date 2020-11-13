package com.hjl.springsecurity.service;


import com.hjl.springsecurity.entity.SysUser;

/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 14:50
 */
public interface SysUserService {

     SysUser selectByName(String name);

     Integer modifyPass(String name,String password);

     String Register(SysUser sysUser);
}
