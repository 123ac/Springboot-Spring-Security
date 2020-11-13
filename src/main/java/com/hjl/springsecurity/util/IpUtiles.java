package com.hjl.springsecurity.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: hjl
 * @Date: 2020/11/13 0013 9:57
 */
public class IpUtiles {
    /**
     * 获取真实IP
     * @param request 请求体
     * @return 真实IP
     */
    public static String getRealIp(HttpServletRequest request) {
        // 这个一般是Nginx反向代理设置的参数
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if  (localhost.equals(ip))  {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return ip;
    }
}