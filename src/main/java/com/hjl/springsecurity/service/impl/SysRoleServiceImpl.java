package com.hjl.springsecurity.service.impl;

import com.hjl.springsecurity.dao.SysRoleMapper;
import com.hjl.springsecurity.entity.SysRole;
import com.hjl.springsecurity.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 15:08
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public SysRole selectById(Integer id) {
        return sysRoleMapper.selectById(id);
    }
}
