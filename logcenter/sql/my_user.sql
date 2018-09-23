/*
Navicat MySQL Data Transfer

Source Server         : poorman
Source Server Version : 50553
Source Host           : 139.224.194.154:3306
Source Database       : my_user

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-07-28 17:52:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for email_log
-- ----------------------------
DROP TABLE IF EXISTS `email_log`;
CREATE TABLE `email_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `recipients` varchar(100) DEFAULT NULL,
  `title` varchar(125) DEFAULT NULL,
  `content` varchar(999) DEFAULT NULL,
  `sendDate` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of email_log
-- ----------------------------
INSERT INTO `email_log` VALUES ('1', 'type', 'donwilliamlone@gmail.com', '邮箱验证', '请您立刻检出邮件内的链接', '2018-05-01 12:21:00');

-- ----------------------------
-- Table structure for signin_log
-- ----------------------------
DROP TABLE IF EXISTS `signin_log`;
CREATE TABLE `signin_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `createDatetime` varchar(30) DEFAULT NULL COMMENT '登录时间',
  `userId` int(10) NOT NULL COMMENT '登录用户id',
  `type` varchar(10) DEFAULT 'SIGNIN' COMMENT '登录类型',
  `way` varchar(255) DEFAULT NULL COMMENT '登录方式',
  `IP` varchar(15) DEFAULT NULL COMMENT '登录时的ip',
  `deviceId` varchar(255) DEFAULT NULL COMMENT '登录设备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of signin_log
-- ----------------------------
INSERT INTO `signin_log` VALUES ('1', '2018-07-23 12:47:00', '1', 'SIGNIN', 'web登入', '127.0.0.1', 'apple');
INSERT INTO `signin_log` VALUES ('2', '2018-07-23 12:47:00', '1', 'SIGNIN', 'web登入', '127.0.0.1', 'apple');
INSERT INTO `signin_log` VALUES ('3', '2018-07-23 12:47:00', '1', 'SIGNIN', 'web登入', '127.0.0.1', 'apple');
INSERT INTO `signin_log` VALUES ('4', '2018-07-23 12:47:00', '1', 'SIGNIN', 'web登入', '127.0.0.1', 'apple');
INSERT INTO `signin_log` VALUES ('5', '2018-07-23 12:47:00', '1', 'SIGNIN', 'web登入', '192.168.1.1', 'apple');

-- ----------------------------
-- Table structure for signin_log_2018-07-29
-- ----------------------------
DROP TABLE IF EXISTS `signin_log_2018-07-29`;
CREATE TABLE `signin_log_2018-07-29` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `createDatetime` varchar(30) DEFAULT NULL COMMENT '登录时间',
  `userId` int(10) NOT NULL COMMENT '登录用户id',
  `type` varchar(10) DEFAULT 'SIGNIN' COMMENT '登录类型',
  `way` varchar(255) DEFAULT NULL COMMENT '登录方式',
  `IP` varchar(15) DEFAULT NULL COMMENT '登录时的ip',
  `deviceId` varchar(255) DEFAULT NULL COMMENT '登录设备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of signin_log_2018-07-29
-- ----------------------------

-- ----------------------------
-- Table structure for signin_log_2018-07-30
-- ----------------------------
DROP TABLE IF EXISTS `signin_log_2018-07-30`;
CREATE TABLE `signin_log_2018-07-30` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `createDatetime` varchar(30) DEFAULT NULL COMMENT '登录时间',
  `userId` int(10) NOT NULL COMMENT '登录用户id',
  `type` varchar(10) DEFAULT 'SIGNIN' COMMENT '登录类型',
  `way` varchar(255) DEFAULT NULL COMMENT '登录方式',
  `IP` varchar(15) DEFAULT NULL COMMENT '登录时的ip',
  `deviceId` varchar(255) DEFAULT NULL COMMENT '登录设备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of signin_log_2018-07-30
-- ----------------------------

-- ----------------------------
-- Table structure for table_maintenance
-- ----------------------------
DROP TABLE IF EXISTS `table_maintenance`;
CREATE TABLE `table_maintenance` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(120) DEFAULT NULL,
  `table_create_day` varchar(20) DEFAULT NULL,
  `table_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of table_maintenance
-- ----------------------------
INSERT INTO `table_maintenance` VALUES ('1', 'sign_2018-07-30', '2018-07-30', 'sign');
