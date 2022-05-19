/*
 Navicat Premium Data Transfer

 Source Server         : myDB
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3309
 Source Schema         : 车辆管理系统

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 17/05/2022 10:59:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cars
-- ----------------------------
DROP TABLE IF EXISTS `cars`;
CREATE TABLE `cars`  (
  `plate` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车牌 主键',
  `model` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆型号',
  `owner` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车主',
  `tel` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车主电话',
  `color` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆颜色',
  PRIMARY KEY (`plate`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cars
-- ----------------------------
INSERT INTO `cars` VALUES ('H111', 'suv', 'yan', '123123123', 'white');
INSERT INTO `cars` VALUES ('H112', 'suv', 'li', '123111', 'white');
INSERT INTO `cars` VALUES ('H113', '卡车', 'fei', '12311232', 'black');
INSERT INTO `cars` VALUES ('H30167', 'suv', 'jack', '13800009999', 'white');
INSERT INTO `cars` VALUES ('H787', '卡车', 'yan', '0000000', 'red');
INSERT INTO `cars` VALUES ('H7921', 'suv', '老严', '13966618368', 'white');
INSERT INTO `cars` VALUES ('ppp', 'suva', '马云', '199999', 'white');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'yanlifei', '123456');
INSERT INTO `users` VALUES (2, 'huangpeng', '123456');
INSERT INTO `users` VALUES (3, 'guozhen', '123456');
INSERT INTO `users` VALUES (4, 'test', 'test');

SET FOREIGN_KEY_CHECKS = 1;
