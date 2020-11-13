package com.hjl.springsecurity.dao;

import com.hjl.springsecurity.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: hjl
 * @Date: 2020/11/13 0013 15:44
 */
@Mapper
public interface SysLogMapper {
       void InsertSysLog(SysLog sysLog);
}
