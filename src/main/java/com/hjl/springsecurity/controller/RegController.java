package com.hjl.springsecurity.controller;

import com.hjl.springsecurity.entity.SysUser;
import com.hjl.springsecurity.entity.SysUserRole;
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
        SysUser user=sysUserService.selectByName(sysUser.getName());
        if (user !=null){
            System.out.println("用户名称重复");
            return "500";
        }
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String Password = bcryptPasswordEncoder.encode(sysUser.getPassword());//加密
        sysUser.setPassword(Password);
        System.out.println("加密pwd;"+ sysUser.getPassword());
        Boolean in=sysUserService.insertUser(sysUser);
        SysUser user1=sysUserService.selectByName(sysUser.getName());
        //给用户默认user权限
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setUserId(user1.getId());
        sysUserRole.setRoleId(2);//ROLE_USER：2
        Boolean is=sysUserRoleService.insertUserRole(sysUserRole);
        return "login";
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
