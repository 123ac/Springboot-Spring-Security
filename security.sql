/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : security

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2020-11-24 15:24:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uri` varchar(255) DEFAULT NULL COMMENT '请求接口',
  `method` varchar(255) DEFAULT NULL COMMENT '请求方式',
  `MethodDescribe` varchar(255) DEFAULT NULL COMMENT '描述',
  `params` varchar(255) DEFAULT NULL COMMENT '参数',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(255) DEFAULT NULL COMMENT 'ip',
  `create_date` varchar(100) DEFAULT NULL COMMENT '操作时间',
  `browser` varchar(255) DEFAULT NULL COMMENT '浏览器类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('13', '/', 'GET', '主页', '[]', 'ABC', '192.168.5.222', '2020-11-13 17:04:06', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('14', '/', 'GET', '主页', '[]', 'ABC', '192.168.5.222', '2020-11-13 17:04:09', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('15', '/', 'GET', '主页', '[]', 'ABC', '192.168.5.222', '2020-11-13 17:04:12', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('16', '/', 'GET', '主页', '[]', 'ABC', '192.168.5.222', '2020-11-13 17:04:17', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('22', '/', 'GET', '主页', '[]', 'ABC', '192.168.5.222', '2020-11-24 09:27:15', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('23', '/', 'GET', '主页', '[]', 'ABC', '192.168.5.222', '2020-11-24 09:33:36', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('24', '/home', 'GET', '登录', '[]', 'ABC', '192.168.5.222', '2020-11-24 09:48:15', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('26', '/home', 'GET', '登录', '[]', 'ABC', '192.168.5.222', '2020-11-24 09:55:29', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('27', '/home', 'GET', '登录', '[]', 'ABC', '192.168.5.222', '2020-11-24 10:07:59', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('28', '/', 'GET', '访问主页', '[]', 'ABC', '192.168.5.222', '2020-11-24 10:08:06', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('29', '/', 'GET', '访问主页', '[]', 'ABC', '192.168.5.222', '2020-11-24 10:12:11', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('31', '/', 'GET', '访问主页', '[]', 'ABC', '192.168.5.222', '2020-11-24 10:13:13', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('33', '/', 'GET', '访问主页', '[]', 'ABC', '192.168.5.222', '2020-11-24 10:15:16', 'Chrome 8');
INSERT INTO `sys_log` VALUES ('34', '/login?logout', 'LoginOut', '退出系统', '[]', 'ABC', '192.168.5.222', '2020-11-24 10:15:19', 'Chrome 8');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleId` int(16) NOT NULL,
  `roleName` varchar(16) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_USER');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$10$foXowBLrQL.lOx5fe695Kuxrp/xkORbICFM2bvP8pRNK0vYh1gaR6');
INSERT INTO `sys_user` VALUES ('2', 'user', '$2a$10$foXowBLrQL.lOx5fe695Kuxrp/xkORbICFM2bvP8pRNK0vYh1gaR6');
INSERT INTO `sys_user` VALUES ('12', '战三', '$2a$10$oZeao4xeg2l98dfIyh/Oz.vsLVWgG8h7ghHeMamG6dm6sUhddJpF.');
INSERT INTO `sys_user` VALUES ('13', 'ABC', '$2a$10$7n1aFDeHfvQ8AsbaqQq0ne0J0Xn.CICuXIrcPFqUyg6PWFYPbxwrG');
INSERT INTO `sys_user` VALUES ('14', 'ABCD', '$2a$10$R.1zk6S51wrB0SAvNxeBl.ad.FZzCPgsTL4fKE3dY2HnQDz0nUBmW');
INSERT INTO `sys_user` VALUES ('15', '李四', '$2a$10$hEhrQaQyYQW19VdKEKty6uevty7LA3UoRV.Wez24XXM06GgXXkzFS');
INSERT INTO `sys_user` VALUES ('16', '王五', '$2a$10$4yRPAz3zheYj166Jr9WWIOkDM8D/4mjJZiijwG.1EUBxgA69Ot.mS');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_role_id` (`role_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`roleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('12', '2');
INSERT INTO `sys_user_role` VALUES ('13', '2');
INSERT INTO `sys_user_role` VALUES ('14', '2');
INSERT INTO `sys_user_role` VALUES ('15', '2');
INSERT INTO `sys_user_role` VALUES ('16', '2');
