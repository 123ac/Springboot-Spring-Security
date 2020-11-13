package com.hjl.springsecurity.service.impl;

import com.hjl.springsecurity.controller.SystemLogController;
import com.hjl.springsecurity.dao.SysUserMapper;
import com.hjl.springsecurity.dao.SysUserRoleMapper;
import com.hjl.springsecurity.entity.SysUser;
import com.hjl.springsecurity.entity.SysUserRole;
import com.hjl.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 14:57
 */
@Service(value = "SysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUser selectByName(String name) {
        return sysUserMapper.selectByName(name);
    }

    @Override
    public Integer modifyPass(String name, String password) {
        return sysUserMapper.modifyPass(name,password);
    }

    @Override
    public String Register(SysUser sysUser) {
        SysUser user=sysUserMapper.selectByName(sysUser.getName());
        if (user !=null){
            System.out.println("用户名称重复");
            return "500";
        }
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String Password = bcryptPasswordEncoder.encode(sysUser.getPassword());//加密
        sysUser.setPassword(Password);
        System.out.println("加密pwd;"+ sysUser.getPassword());
        Boolean in=sysUserMapper.insertUser(sysUser);
        SysUser user1=sysUserMapper.selectByName(sysUser.getName());
        //给用户默认user权限
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setUserId(user1.getId());
        sysUserRole.setRoleId(2);//ROLE_USER：2
        Boolean is=sysUserRoleMapper.insertUserRole(sysUserRole);
        return "login";
    }

}
