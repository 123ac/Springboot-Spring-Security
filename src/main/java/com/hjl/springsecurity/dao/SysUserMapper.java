package com.hjl.springsecurity.dao;

import com.hjl.springsecurity.entity.SysUser;
import org.apache.ibatis.annotations.*;

/**
 * @Author: hjl
 * @Date: 2020/11/10 0010 14:48
 */
@Mapper
public interface SysUserMapper {
    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    SysUser selectById(Integer id);

    @Select("SELECT * FROM sys_user WHERE name = #{name}")
    SysUser selectByName(String name);

    @Insert("insert into sys_user(name,password) values(#{name},#{password})")
    Boolean insertUser(SysUser sysUser);

    @Update("update sys_user set password = #{password} where name=#{name}")
    Integer modifyPass(@Param("name") String name,@Param("password") String password);
}
