package com.hjl.springsecurity.dao;

import com.hjl.springsecurity.entity.SysUserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.*;

/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 14:52
 */
@Mapper
public interface SysUserRoleMapper {
    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);

    @Insert("Insert into sys_user_role values(#{userId},#{roleId})")
    Boolean insertUserRole(SysUserRole sysUserRole);
}
