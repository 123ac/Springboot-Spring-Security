package com.hjl.springsecurity.service.impl;

import com.hjl.springsecurity.dao.SysUserMapper;
import com.hjl.springsecurity.entity.SysUser;
import com.hjl.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 14:57
 */
@Service(value = "SysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser selectById(Integer id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public SysUser selectByName(String name) {
        return sysUserMapper.selectByName(name);
    }

    @Override
    public Boolean insertUser(SysUser sysUser) {
        return sysUserMapper.insertUser(sysUser);
    }

    @Override
    public Integer modifyPass(String name, String password) {
        return sysUserMapper.modifyPass(name,password);
    }
}
