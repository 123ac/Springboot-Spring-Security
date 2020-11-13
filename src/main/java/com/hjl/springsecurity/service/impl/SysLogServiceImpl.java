package com.hjl.springsecurity.service.impl;

import com.hjl.springsecurity.dao.SysLogMapper;
import com.hjl.springsecurity.entity.SysLog;
import com.hjl.springsecurity.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: hjl
 * @Date: 2020/11/13 0013 15:34
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper  sysLogMapper;

    @Override
    public void InsertSysLog(SysLog sysLog) {
        sysLogMapper.InsertSysLog(sysLog);
    }


}
