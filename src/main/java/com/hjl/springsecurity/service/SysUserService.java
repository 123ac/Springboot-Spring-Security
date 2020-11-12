package com.hjl.springsecurity.service;


import com.hjl.springsecurity.entity.SysUser;

/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 14:50
 */
public interface SysUserService {
     SysUser selectById(Integer id);

     SysUser selectByName(String name);

     Boolean insertUser(SysUser sysUser);

     Integer modifyPass(String name,String password);
}
