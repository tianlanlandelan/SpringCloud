/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : my_core

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 17/07/2018 22:17:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`name`) COMMENT '角色名不允许重复'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (3, 'User', '一般用户，用户注册时的默认角色', '2018-07-17 21:56:04');
INSERT INTO `role` VALUES (4, 'Admin', '普通管理员，可以查看权限、日志等', '2018-07-17 21:56:43');
COMMIT;

-- ----------------------------
-- Table structure for role_routers
-- ----------------------------
DROP TABLE IF EXISTS `role_routers`;
CREATE TABLE `role_routers` (
  `roleId` int(11) NOT NULL,
  `routerId` int(11) NOT NULL,
  `createTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for router
-- ----------------------------
DROP TABLE IF EXISTS `router`;
CREATE TABLE `router` (
  `id` int(5) NOT NULL,
  `serviceName` varchar(255) DEFAULT NULL,
  `controllerName` varchar(255) DEFAULT NULL,
  `methodName` varchar(255) NOT NULL,
  `routerUrl` varchar(255) DEFAULT NULL,
  `firstMask` bigint(255) DEFAULT NULL,
  `secondMask` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of router
-- ----------------------------
BEGIN;
INSERT INTO `router` VALUES (1000, 'LogCenter', 'http', 'get', 'http://LogCenter/http/get', 1, 1);
INSERT INTO `router` VALUES (1001, 'LogCenter', 'http', 'post', 'http://LogCenter/http/post', 1, 1);
INSERT INTO `router` VALUES (2000, 'UserManagerCenter', 'permission', 'getAllRoles', 'http://UserManagerCenter/permission/getAllRoles', 2, 1);
INSERT INTO `router` VALUES (2001, 'UserManagerCenter', 'permission', 'getRolesByUserId', 'http://UserManagerCenter/permission/getRolesByUserId', 2, 1);
INSERT INTO `router` VALUES (2002, 'UserManagerCenter', 'permission', 'getRolesByRouterId', 'http://UserManagerCenter/permission/getRolesByRouterId', 2, 1);
INSERT INTO `router` VALUES (2003, 'UserManagerCenter', 'permission', 'getUsersByRoleId', 'http://UserManagerCenter/permission/getUsersByRoleId', 2, 1);
INSERT INTO `router` VALUES (2004, 'UserManagerCenter', 'permission', 'getAllRouters', 'http://UserManagerCenter/permission/getAllRouters', 2, 1);
INSERT INTO `router` VALUES (2005, 'UserManagerCenter', 'permission', 'getRoutersByRoleId', 'http://UserManagerCenter/permission/getRoutersByRoleId', 2, 1);
INSERT INTO `router` VALUES (2100, 'UserManagerCenter', 'permissionManager', 'addRole', 'http://UserManagerCenter/permissionManager/addRole', 2, 2);
INSERT INTO `router` VALUES (2101, 'UserManagerCenter', 'permissionManager', 'addRoleForUser', 'http://UserManagerCenter/permissionManager/addRoleForUser', 2, 2);
INSERT INTO `router` VALUES (2102, 'UserManagerCenter', 'permissionManager', 'addRouterForRole', 'http://UserManagerCenter/permissionManager/addRouterForRole', 2, 2);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户昵称',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `wxid` varchar(255) DEFAULT NULL COMMENT '微信ID',
  `email` varchar(255) DEFAULT NULL COMMENT '用户注册时间',
  `password` varchar(255) DEFAULT NULL COMMENT '用户类型:记录用户注册的方式（手机号注册 0；微信注册 1）',
  `mask` bigint(20) DEFAULT NULL,
  `isDelete` int(1) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (6, 'user1', '17600100001', NULL, NULL, '4QrcOUm6Wau+VuBX8g+IPg==', 3, 0, '2018-07-17 21:14:57');
INSERT INTO `user` VALUES (7, 'user2', NULL, NULL, 'guyexing@foxmail.com', '4QrcOUm6Wau+VuBX8g+IPg==', 5, 0, '2018-07-17 21:15:10');
COMMIT;

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  `createTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
