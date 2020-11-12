package com.hjl.springsecurity.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 15:11
 *
 * 如代码所示，获取当前登录用户：SecurityContextHolder.getContext().getAuthentication()
 *
 * 注解 @PreAuthorize 用于判断用户是否有指定权限，没有就不能访问
 */
@Controller
public class LoginController {
    private Logger logger= LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/")
    public String showHome(){
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登录用户:{}",name);
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
