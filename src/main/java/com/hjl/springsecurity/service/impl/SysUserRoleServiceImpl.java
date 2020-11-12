package com.hjl.springsecurity.service.impl;

import com.hjl.springsecurity.dao.SysUserRoleMapper;
import com.hjl.springsecurity.entity.SysUserRole;
import com.hjl.springsecurity.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 15:08
 */
@Service
public class SysUserRoleServiceImpl  implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Override
    public List<SysUserRole> listByUserId(Integer userId) {
        return sysUserRoleMapper.listByUserId(userId);
    }

    @Override
    public Boolean insertUserRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insertUserRole(sysUserRole);
    }
}
