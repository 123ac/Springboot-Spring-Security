package com.hjl.springsecurity.config;


import com.hjl.springsecurity.entity.SysLog;
import com.hjl.springsecurity.service.SysLogService;
import com.hjl.springsecurity.util.IpUtiles;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: hjl
 * @Date: 2020/11/24 0024 10:00
 */
@Component
public class MyHttpSessionListener implements HttpSessionListener {
    @Autowired
    SysLogService sysLogService;
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //读取用户
        String  name = SecurityContextHolder.getContext().getAuthentication().getName();
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取ip
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = IpUtiles.getRealIp(request);
        //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        Browser browser = userAgent.getBrowser();
        String browserName = browser.getName();

        SysLog sysLog=new SysLog();
        sysLog.setUsername(name);
        sysLog.setUri("/login?logout");
        sysLog.setMethod("LoginOut");
        sysLog.setMethodDescribe("退出系统");
        sysLog.setParams("[]");
        sysLog.setCreateDate(df.format(new Date()));
        sysLog.setIp(ip);
        sysLog.setBrowser(browserName);
        //保存记录
        sysLogService.InsertSysLog(sysLog);
    }
}
