package com.hjl.springsecurity.service;

import com.hjl.springsecurity.entity.SysUserRole;

import java.util.*;

/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 14:51
 */
public interface SysUserRoleService {
    List<SysUserRole> listByUserId(Integer userId);

    Boolean insertUserRole(SysUserRole sysUserRole);
}
