/*
Navicat MySQL Data Transfer

Source Server         : dyb
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : trainticket

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-04-21 20:29:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderId` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `orderNo` varchar(255) NOT NULL COMMENT '订单号',
  `orderTime` datetime NOT NULL COMMENT '下单时间',
  `trainId` int NOT NULL COMMENT '车次id',
  `ticektNum` int NOT NULL COMMENT '订票数量',
  `totalPrice` decimal(10,2) NOT NULL COMMENT '总价',
  `orderStatus` varchar(255) NOT NULL COMMENT '订单状态：已支付，未支付，已取消',
  `orderUserId` int NOT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for price
-- ----------------------------
DROP TABLE IF EXISTS `price`;
CREATE TABLE `price` (
  `startStationid` int NOT NULL COMMENT '出发站id',
  `endStationid` int NOT NULL COMMENT '到达站id',
  `APrice` decimal(10,0) NOT NULL COMMENT 'A座价格',
  `BPrice` decimal(10,0) NOT NULL COMMENT 'B座价格',
  `CPrice` decimal(10,0) NOT NULL COMMENT 'C座价格',
  `trainTypeId` int NOT NULL COMMENT '列车类型id',
  PRIMARY KEY (`startStationid`,`endStationid`,`trainTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of price
-- ----------------------------
INSERT INTO `price` VALUES ('1', '2', '1750', '900', '500', '1');
INSERT INTO `price` VALUES ('1', '2', '500', '300', '200', '2');
INSERT INTO `price` VALUES ('4', '5', '470', '43', '65', '1');

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `stationId` int NOT NULL COMMENT '站点id',
  `stationName` varchar(255) NOT NULL COMMENT '站点名称',
  PRIMARY KEY (`stationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of station
-- ----------------------------
INSERT INTO `station` VALUES ('1', '北京');
INSERT INTO `station` VALUES ('2', '上海');
INSERT INTO `station` VALUES ('3', '广州');
INSERT INTO `station` VALUES ('4', '西安');
INSERT INTO `station` VALUES ('5', '武汉');
INSERT INTO `station` VALUES ('6', '昆明');
INSERT INTO `station` VALUES ('7', '乌鲁木齐');
INSERT INTO `station` VALUES ('8', '郑州');
INSERT INTO `station` VALUES ('9', '哈尔滨');
INSERT INTO `station` VALUES ('10', '济南');

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `ticketId` int NOT NULL AUTO_INCREMENT COMMENT '车票id',
  `orderNo` varchar(255) NOT NULL COMMENT '订单号',
  `trainNum` varchar(255) NOT NULL COMMENT '车次号',
  `seatId` int NOT NULL COMMENT '座位id',
  `seatNo` varchar(255) NOT NULL COMMENT '座位编号',
  `travelTime` date NOT NULL COMMENT '乘车日期',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `identityNum` varchar(255) DEFAULT NULL COMMENT '证件号',
  `startTime` time NOT NULL COMMENT '出发时间',
  `endTime` time NOT NULL COMMENT '到达时间',
  `startStationid` int NOT NULL COMMENT '出发站id',
  `startStationName` varchar(255) NOT NULL COMMENT '出发站名称',
  `endStationid` int NOT NULL COMMENT '到达站id',
  `endStationName` varchar(255) NOT NULL COMMENT '到达站名称',
  `ticketStatus` varchar(255) NOT NULL COMMENT '票状态：正常，已改签，已退票',
  `ticketUserId` int NOT NULL,
  PRIMARY KEY (`ticketId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES ('1', '582cf8a85a554d68bdbe1c0a07de8d9f', 'G452', '1041', 'A1', '2020-04-23', '65.00', '董俞伯', '142716199712042133', '16:03:03', '20:04:01', '4', '西安', '5', '武汉', 'refunded', '5');
INSERT INTO `ticket` VALUES ('2', '1481ff465db84271a5517450c00bf897', 'G452', '1301', 'A1', '2020-04-24', '65.00', '董俞伯', '142716199712042133', '16:03:03', '20:04:01', '4', '西安', '5', '武汉', 'refunded', '5');

-- ----------------------------
-- Table structure for ticketseat
-- ----------------------------
DROP TABLE IF EXISTS `ticketseat`;
CREATE TABLE `ticketseat` (
  `seatId` int NOT NULL AUTO_INCREMENT COMMENT '座位id',
  `seatType` varchar(255) NOT NULL COMMENT '座位类型',
  `seatNo` varchar(255) NOT NULL COMMENT '座位编号',
  `trainId` int NOT NULL COMMENT '车次id',
  `travelTime` date NOT NULL COMMENT '乘车日期',
  `ticketSeatStatus` varchar(255) NOT NULL COMMENT '座位状态：已售出，未售出',
  PRIMARY KEY (`seatId`),
  KEY `idx_seat` (`seatType`,`trainId`,`travelTime`,`ticketSeatStatus`)
) ENGINE=InnoDB AUTO_INCREMENT=4421 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticketseat
-- ----------------------------

-- ----------------------------
-- Table structure for train
-- ----------------------------
DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `trainId` int NOT NULL AUTO_INCREMENT COMMENT '车次id',
  `trainNum` varchar(255) NOT NULL COMMENT '车次号',
  `trainTypeId` int NOT NULL COMMENT '列车类型id',
  `startTime` time NOT NULL COMMENT '出发时间',
  `endTime` time NOT NULL COMMENT '到达时间',
  `startStationid` int NOT NULL COMMENT '出发站id',
  `startStationName` varchar(255) NOT NULL COMMENT '出发站名称',
  `endStationid` int NOT NULL COMMENT '到达站id',
  `endStationName` varchar(255) NOT NULL COMMENT '到达站名称',
  PRIMARY KEY (`trainId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of train
-- ----------------------------
INSERT INTO `train` VALUES ('1', 'G451', '1', '02:03:04', '16:04:04', '4', '西安', '5', '武汉');

-- ----------------------------
-- Table structure for traintype
-- ----------------------------
DROP TABLE IF EXISTS `traintype`;
CREATE TABLE `traintype` (
  `trainTypeId` int NOT NULL AUTO_INCREMENT COMMENT '列车类型id',
  `ANum` int NOT NULL COMMENT 'A座数量',
  `BNum` int NOT NULL COMMENT 'B座数量',
  `CNum` int NOT NULL COMMENT 'C座数量',
  PRIMARY KEY (`trainTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of traintype
-- ----------------------------
INSERT INTO `traintype` VALUES ('1', '10', '50', '200');
INSERT INTO `traintype` VALUES ('2', '20', '100', '300');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userName` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `identityNum` varchar(255) DEFAULT NULL COMMENT '证件号',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `type` varchar(255) NOT NULL COMMENT '用户类型:普通用户，管理员',
  `activated` tinyint(1) DEFAULT '1' COMMENT '账号激活状态',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'gzy', '18817555183', null, '666666', '18817555183', '123@qq.com', 'User', '1');
INSERT INTO `user` VALUES ('2', 'superadmin', '4297f44b13955235245b2497399d7a93', '123456789', 'admin@qq.com', '1111111', null, 'admin', '1');
