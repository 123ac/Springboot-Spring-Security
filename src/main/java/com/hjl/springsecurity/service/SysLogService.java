package com.hjl.springsecurity.service;

import com.hjl.springsecurity.entity.SysLog;

/**
 * @Author: hjl
 * @Date: 2020/11/13 0013 15:33
 */
public interface SysLogService {
    //添加用户操作日志
    void InsertSysLog(SysLog sysLog);
}
