package com.hjl.springsecurity.controller;

import com.hjl.springsecurity.entity.SysUser;
import com.hjl.springsecurity.service.SysUserRoleService;
import com.hjl.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: hjl
 * @Date: 2020/11/11 0011 10:12
 */
@Controller
public class RegController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @RequestMapping("/register")
    public String Register(SysUser sysUser){
        System.out.println("name ;"+sysUser.getName()+"  pwd:"+sysUser.getPassword());
        return sysUserService.Register(sysUser);
    }

    @RequestMapping("/modifyPass")
    public String modifyPass(String name,String password,String rePassword){
        System.out.println("name ;"+name + " 旧密码："+password +" 新密码："+rePassword);
        SysUser sysUser=sysUserService.selectByName(name);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(password,sysUser.getPassword())) {
            String encode = encoder.encode(rePassword);
            rePassword = encode;
            Integer i=sysUserService.modifyPass(name,rePassword);
            System.out.println("成功！"+i);
            return "home";
        }else {
            return "home";
        }
    }
}
