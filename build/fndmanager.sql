/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : fndmanager

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-06-01 11:10:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for function
-- ----------------------------
DROP TABLE IF EXISTS `function`;
CREATE TABLE `function` (
  `func_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '功能模块id',
  `fun_name` varchar(20) NOT NULL COMMENT '功能名称',
  `fun_pid` int(4) DEFAULT '0' COMMENT '父子级联关系',
  `fun_url` varchar(255) NOT NULL COMMENT '访问url',
  `fun_seq_no` tinyint(4) NOT NULL DEFAULT '0' COMMENT '菜单排序',
  `fun_level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '菜单级别。-1：非菜单 0：菜单根节点 1：1级菜单 2：2级菜单，依次类推',
  `fun_icon_class` varchar(20) DEFAULT NULL COMMENT '用于控制图片显示的样式',
  `fun_create_at` datetime NOT NULL COMMENT '创建时间',
  `fun_code` varchar(50) NOT NULL COMMENT '权限code值',
  PRIMARY KEY (`func_id`),
  UNIQUE KEY `uidx_code` (`fun_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='功能模块表';

-- ----------------------------
-- Records of function
-- ----------------------------
INSERT INTO `function` VALUES ('1', '系统管理', null, 'javascript:void(0)', '1', '1', '', '2016-05-17 15:09:32', 'system:manager');
INSERT INTO `function` VALUES ('2', '平台用户', '1', 'user/userList.html', '1', '2', '', '2016-05-17 15:10:09', 'user:list');
INSERT INTO `function` VALUES ('3', '权限设置', '1', 'user/roleList.html', '2', '2', '', '2016-05-17 15:10:32', 'role:list');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色表id',
  `role_code` varchar(50) NOT NULL COMMENT '角色code值',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ADMIN', '管理员', '2016-05-18 16:00:10');

-- ----------------------------
-- Table structure for role_func
-- ----------------------------
DROP TABLE IF EXISTS `role_func`;
CREATE TABLE `role_func` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色对应的功能模块表id',
  `d_role_id` int(11) NOT NULL COMMENT '角色ID',
  `d_func_id` int(11) NOT NULL COMMENT '模块ID',
  PRIMARY KEY (`id`),
  KEY `fk_role_func_role_idx` (`d_role_id`),
  KEY `fk_role_func_func_idx` (`d_func_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色对应的功能模块表';

-- ----------------------------
-- Records of role_func
-- ----------------------------
INSERT INTO `role_func` VALUES ('1', '1', '1');
INSERT INTO `role_func` VALUES ('2', '1', '2');
INSERT INTO `role_func` VALUES ('3', '1', '3');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(45) NOT NULL COMMENT '用户名',
  `pass_word` varchar(200) NOT NULL COMMENT '密码',
  `digest` varchar(30) DEFAULT NULL COMMENT '加密码',
  `user_status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '用户状态: 0 未合作 1 合作中',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uidx_user_name` (`user_name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='平台账户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '7a57a5a743894a0e', 'admin', '1', '2016-04-25 15:46:34', null);
INSERT INTO `user` VALUES ('2', 'wx', 'f44f4964e6c998de', 'asdasd', '1', '2016-05-26 11:00:29', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_role_userid` (`user_id`) USING BTREE,
  KEY `idx_user_role_roleid` (`role_id`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', '2016-05-26 10:46:25');
INSERT INTO `user_role` VALUES ('2', '2', '1', '2016-05-26 11:00:29');
