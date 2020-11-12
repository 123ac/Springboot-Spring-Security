package com.hjl.springsecurity.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: hjl
 * @Date: 2020/11/11 0011 9:26
 */
public class main {

        public static void main(String[] args) {
            String pass = "123";
            BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
            String hashPass = bcryptPasswordEncoder.encode(pass);//加密
            System.out.println(hashPass);

            String mysqlPass="$2a$10$foXowBLrQL.lOx5fe695Kuxrp/xkORbICFM2bvP8pRNK0vYh1gaR6";//例子：数据库中密码
            boolean f = bcryptPasswordEncoder.matches("123",mysqlPass);//比较密码
            System.out.println(f);

        }

}
