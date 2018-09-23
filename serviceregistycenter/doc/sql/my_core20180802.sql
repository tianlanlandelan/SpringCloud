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

 Date: 02/08/2018 14:04:07
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
-- Records of role_routers
-- ----------------------------
BEGIN;
INSERT INTO `role_routers` VALUES (3, 10000, NULL);
INSERT INTO `role_routers` VALUES (3, 10001, NULL);
INSERT INTO `role_routers` VALUES (3, 10002, NULL);
INSERT INTO `role_routers` VALUES (3, 10003, NULL);
INSERT INTO `role_routers` VALUES (3, 10004, NULL);
INSERT INTO `role_routers` VALUES (3, 10005, NULL);
INSERT INTO `role_routers` VALUES (3, 10006, NULL);
INSERT INTO `role_routers` VALUES (3, 10007, NULL);
INSERT INTO `role_routers` VALUES (3, 10008, NULL);
COMMIT;

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
  `requestMethod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of router
-- ----------------------------
BEGIN;
INSERT INTO `router` VALUES (10000, 'LogCenter', 'http', 'get', 'http://LogCenter/http/get', NULL, NULL, 'GET');
INSERT INTO `router` VALUES (10001, 'UserManagerCenter', 'permission', 'getAllRoles', 'http://UserManagerCenter/permission/getAllRoles', NULL, NULL, 'GET');
INSERT INTO `router` VALUES (10002, 'UserManagerCenter', 'permission', 'getRoleByUserId', 'http://UserManagerCenter/permission/getRoleByUserId', NULL, NULL, 'GET');
INSERT INTO `router` VALUES (10003, 'UserManagerCenter', 'permission', 'getRolesByRouterId', 'http://UserManagerCenter/permission/getRolesByRouterId', NULL, NULL, 'GET');
INSERT INTO `router` VALUES (10004, 'UserManagerCenter', 'permission', 'getUsersByRoleId', 'http://UserManagerCenter/permission/getUsersByRoleId', NULL, NULL, 'GET');
INSERT INTO `router` VALUES (10005, 'UserManagerCenter', 'permission', 'getAllRouters', 'http://UserManagerCenter/permission/getAllRouters', NULL, NULL, 'GET');
INSERT INTO `router` VALUES (10006, 'UserManagerCenter', 'permission', 'getRoutersByRoleId', 'http://UserManagerCenter/permission/getRoutersByRoleId', NULL, NULL, 'GET');
INSERT INTO `router` VALUES (10007, 'UserManagerCenter', 'permission', 'getRouterIdsByUserId', 'http://UserManagerCenter/permission/getRouterIdsByUserId', NULL, NULL, 'GET');
INSERT INTO `router` VALUES (10008, 'UserManagerCenter', 'userInfo', 'get', 'http://UserManagerCenter/userInfo/get', NULL, NULL, 'GET');
INSERT INTO `router` VALUES (20000, 'LogCenter', 'http', 'post', 'http://LogCenter/http/post', NULL, NULL, 'POST');
INSERT INTO `router` VALUES (20001, 'UserManagerCenter', 'permissionManager', 'addRole', 'http://UserManagerCenter/permissionManager/addRole', NULL, NULL, 'POST');
INSERT INTO `router` VALUES (20002, 'UserManagerCenter', 'permissionManager', 'addRoleForUser', 'http://UserManagerCenter/permissionManager/addRoleForUser', NULL, NULL, 'POST');
INSERT INTO `router` VALUES (20003, 'UserManagerCenter', 'permissionManager', 'addRouterForRole', 'http://UserManagerCenter/permissionManager/addRouterForRole', NULL, NULL, 'POST');
INSERT INTO `router` VALUES (30000, 'UserManagerCenter', 'permissionManager', 'deleteRoleById', 'http://UserManagerCenter/permissionManager/deleteRoleById', NULL, NULL, 'DELETE');
INSERT INTO `router` VALUES (40000, 'UserManagerCenter', 'permissionManager', 'updateRole', 'http://UserManagerCenter/permissionManager/updateRole', NULL, NULL, 'PUT');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (8, 'user1', '18911927890', NULL, 'mamian@com', '4QrcOUm6Wau+VuBX8g+IPg==', 7, 0, '2018-08-02 13:59:51');
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL COMMENT '用户ID',
  `nickName` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `gender` int(1) DEFAULT NULL COMMENT '性别 0:男 1:女',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `signature` varchar(255) DEFAULT NULL COMMENT '签名',
  `userPortrait` varchar(255) DEFAULT NULL COMMENT '头像Url',
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `mask` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES (8, NULL, NULL, 0, NULL, NULL, NULL, 'mamian@com', '18911927890', '2018-08-02 13:59:51', NULL);
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

-- ----------------------------
-- Records of user_roles
-- ----------------------------
BEGIN;
INSERT INTO `user_roles` VALUES (8, 3, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
