/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : shopping

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 09/05/2019 11:37:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_category
-- ----------------------------
DROP TABLE IF EXISTS `tbl_category`;
CREATE TABLE `tbl_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `descr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  `isleaf` int(11) NULL DEFAULT NULL,
  `grade` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_category
-- ----------------------------
INSERT INTO `tbl_category` VALUES (1, '手机', '手机', 0, 1, 1);

-- ----------------------------
-- Table structure for tbl_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_log`;
CREATE TABLE `tbl_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `controller` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `act` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `logdate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_log
-- ----------------------------
INSERT INTO `tbl_log` VALUES (1, 'admin', '/admin', '/admin', '2019-05-09 11:17:09');
INSERT INTO `tbl_log` VALUES (2, 'admin', '/admin', '/admin', '2019-05-09 11:29:48');
INSERT INTO `tbl_log` VALUES (3, 'admin', '/admin', '/admin', '2019-05-09 11:29:52');
INSERT INTO `tbl_log` VALUES (4, 'admin', '/admin', '/admin/listUser', '2019-05-09 11:29:57');
INSERT INTO `tbl_log` VALUES (5, 'admin', '/admin', '/admin', '2019-05-09 11:30:46');
INSERT INTO `tbl_log` VALUES (6, 'admin', '/admin', '/admin/listUser', '2019-05-09 11:30:50');
INSERT INTO `tbl_log` VALUES (7, 'admin', '/admin', '/admin/addUser', '2019-05-09 11:31:05');
INSERT INTO `tbl_log` VALUES (8, 'admin', '/admin', '/admin/addUser', '2019-05-09 11:31:11');
INSERT INTO `tbl_log` VALUES (9, 'admin', '/admin', '/admin/listUser', '2019-05-09 11:31:19');
INSERT INTO `tbl_log` VALUES (10, 'admin', '/product', '/product/list', '2019-05-09 11:31:28');
INSERT INTO `tbl_log` VALUES (11, 'admin', '/product', '/product/add', '2019-05-09 11:31:36');
INSERT INTO `tbl_log` VALUES (12, 'admin', '/category', '/category/list', '2019-05-09 11:32:03');
INSERT INTO `tbl_log` VALUES (13, 'admin', '/category', '/category/ajax', '2019-05-09 11:32:04');
INSERT INTO `tbl_log` VALUES (14, 'admin', '/category', '/category/add', '2019-05-09 11:32:06');
INSERT INTO `tbl_log` VALUES (15, 'admin', '/category', '/category/add', '2019-05-09 11:32:16');
INSERT INTO `tbl_log` VALUES (16, 'admin', '/product', '/product/list', '2019-05-09 11:33:15');
INSERT INTO `tbl_log` VALUES (17, 'admin', '/product', '/product/add', '2019-05-09 11:33:16');
INSERT INTO `tbl_log` VALUES (18, 'admin', '/product', '/product/add', '2019-05-09 11:33:34');
INSERT INTO `tbl_log` VALUES (19, 'admin', '/', '/', '2019-05-09 11:34:24');
INSERT INTO `tbl_log` VALUES (20, 'admin', '/product', '/product/detail', '2019-05-09 11:34:36');

-- ----------------------------
-- Table structure for tbl_product
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product`;
CREATE TABLE `tbl_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `descr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `normalprice` double NULL DEFAULT NULL,
  `memberprice` double NULL DEFAULT NULL,
  `pdate` datetime(0) NULL DEFAULT NULL,
  `categoryid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_product
-- ----------------------------
INSERT INTO `tbl_product` VALUES (1, 'Iphone', '贵不贵', 10000, 900, '2019-05-09 11:33:34', 1);

-- ----------------------------
-- Table structure for tbl_salesitem
-- ----------------------------
DROP TABLE IF EXISTS `tbl_salesitem`;
CREATE TABLE `tbl_salesitem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productid` int(11) NULL DEFAULT NULL,
  `unitprice` double NULL DEFAULT NULL,
  `pcount` int(11) NULL DEFAULT NULL,
  `orderid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_salesorder
-- ----------------------------
DROP TABLE IF EXISTS `tbl_salesorder`;
CREATE TABLE `tbl_salesorder`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NULL DEFAULT NULL,
  `addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `odate` datetime(0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rdate` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES (1, 'admin', '21232F297A57A5A743894A0E4A801FC3', '10086', '中国广东省广州市越秀区环市东路419号 邮政编码: 510245', '2019-05-09 11:30:36');
INSERT INTO `tbl_user` VALUES (2, 'test', '2a60ed9325f63a5027953fd87290527d', '10086', '请修改你的地址', '2019-05-09 11:31:11');

SET FOREIGN_KEY_CHECKS = 1;
