package com.hjl.springsecurity.controller;

import com.alibaba.fastjson.JSONObject;
import com.hjl.springsecurity.dao.SysUserMapper;
import com.hjl.springsecurity.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.*;

/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 15:11
 *
 * 如代码所示，获取当前登录用户：SecurityContextHolder.getContext().getAuthentication()
 *
 * 注解 @PreAuthorize 用于判断用户是否有指定权限，没有就不能访问
 */
@Controller
@CrossOrigin //跨域
public class LoginController {
    private Logger logger= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @RequestMapping("/")
    @SystemLogController(description = "主页")
    public String showHome(){
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登录用户:{}",name);
        return "home.html";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String  test(@Param("name")String name){
        System.out.println("name:"+name);
        HashMap<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("data","成功");

        List<SysUser> sysUser=sysUserMapper.query();
        map.put("items",sysUser);
        String jsonString = JSONObject.toJSONString(map);
        return jsonString;
    }

    @RequestMapping("/test/{Id}/{name}")
    @SystemLogController(description = "测试方法")
    public String test(@PathVariable("Id")String Id,@PathVariable("name")String name){
        logger.info("测试参数{}-{}",Id,name);
        return "home.html";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/regis")
    public String register(){
        return "regis";
    }

    @RequestMapping("/modify")
    public ModelAndView modifyPass(){
        ModelAndView mav=new ModelAndView();
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        mav.addObject("name",name);
        mav.setViewName("modifyPass");
        return mav;
    }
    @RequestMapping("/500")
    public String error() {
        return "500";
    }

    @RequestMapping("/400")
    public String error1() {
        return "400";
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }
}
