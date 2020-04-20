/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : trainticket

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2020-04-08 21:30:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `orderNo` varchar(255) NOT NULL COMMENT '订单号',
  `orderTime` datetime NOT NULL COMMENT '下单时间',
  `trainId` int(11) NOT NULL COMMENT '车次id',
  `ticektNum` int(11) NOT NULL COMMENT '订票数量',
  `totalPrice` decimal(10,2) NOT NULL COMMENT '总价',
  `orderStatus` varchar(255) NOT NULL COMMENT '订单状态：已支付，未支付，已取消',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for price
-- ----------------------------
DROP TABLE IF EXISTS `price`;
CREATE TABLE `price` (
  `startStationid` int(11) NOT NULL COMMENT '出发站id',
  `endStationid` int(11) NOT NULL COMMENT '到达站id',
  `APrice` decimal(10,0) NOT NULL COMMENT 'A座价格',
  `BPrice` decimal(10,0) NOT NULL COMMENT 'B座价格',
  `CPrice` decimal(10,0) NOT NULL COMMENT 'C座价格',
  `trainTypeId` int(11) NOT NULL COMMENT '列车类型id',
  PRIMARY KEY (`startStationid`,`endStationid`,`trainTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of price
-- ----------------------------
INSERT INTO `price` VALUES ('1', '2', '1750', '900', '500', '1');
INSERT INTO `price` VALUES ('1', '2', '500', '300', '200', '2');

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `stationId` int(11) NOT NULL COMMENT '站点id',
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
  `ticketId` int(11) NOT NULL AUTO_INCREMENT COMMENT '车票id',
  `orderNo` varchar(255) NOT NULL COMMENT '订单号',
  `trainNum` varchar(255) NOT NULL COMMENT '车次号',
  `seatId` int(11) NOT NULL COMMENT '座位id',
  `seatNo` varchar(255) NOT NULL COMMENT '座位编号',
  `travelTime` date NOT NULL COMMENT '乘车日期',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `identityNum` varchar(255) DEFAULT NULL COMMENT '证件号',
  `startTime` time NOT NULL COMMENT '出发时间',
  `endTime` time NOT NULL COMMENT '到达时间',
  `startStationid` int(11) NOT NULL COMMENT '出发站id',
  `startStationName` varchar(255) NOT NULL COMMENT '出发站名称',
  `endStationid` int(11) NOT NULL COMMENT '到达站id',
  `endStationName` varchar(255) NOT NULL COMMENT '到达站名称',
  `ticketStatus` varchar(255) NOT NULL COMMENT '票状态：正常，已改签，已退票',
  PRIMARY KEY (`ticketId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticket
-- ----------------------------

-- ----------------------------
-- Table structure for ticketseat
-- ----------------------------
DROP TABLE IF EXISTS `ticketseat`;
CREATE TABLE `ticketseat` (
  `seatId` int(11) NOT NULL AUTO_INCREMENT COMMENT '座位id',
  `seatType` varchar(255) NOT NULL COMMENT '座位类型',
  `seatNo` varchar(255) NOT NULL COMMENT '座位编号',
  `trainId` int(11) NOT NULL COMMENT '车次id',
  `travelTime` date NOT NULL COMMENT '乘车日期',
  `ticketSeatStatus` varchar(255) NOT NULL COMMENT '座位状态：已售出，未售出',
  PRIMARY KEY (`seatId`),
  KEY `idx_seat` (`seatType`,`trainId`,`travelTime`,`ticketSeatStatus`)
) ENGINE=InnoDB AUTO_INCREMENT=781 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticketseat
-- ----------------------------
INSERT INTO `ticketseat` VALUES ('1', 'A', '001', '1', '2020-04-10', 'SELL');
INSERT INTO `ticketseat` VALUES ('2', 'A', '002', '1', '2020-04-10', 'SELL');
INSERT INTO `ticketseat` VALUES ('3', 'A', '003', '1', '2020-04-10', 'SELL');
INSERT INTO `ticketseat` VALUES ('4', 'A', '004', '1', '2020-04-10', 'SELL');
INSERT INTO `ticketseat` VALUES ('5', 'A', '005', '1', '2020-04-10', 'SELL');
INSERT INTO `ticketseat` VALUES ('6', 'A', '006', '1', '2020-04-10', 'SELL');
INSERT INTO `ticketseat` VALUES ('7', 'A', '007', '1', '2020-04-10', 'SELL');
INSERT INTO `ticketseat` VALUES ('8', 'A', '008', '1', '2020-04-10', 'SELL');
INSERT INTO `ticketseat` VALUES ('9', 'A', '009', '1', '2020-04-10', 'SELL');
INSERT INTO `ticketseat` VALUES ('10', 'A', '010', '1', '2020-04-10', 'SELL');
INSERT INTO `ticketseat` VALUES ('11', 'B', '001', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('12', 'B', '002', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('13', 'B', '003', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('14', 'B', '004', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('15', 'B', '005', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('16', 'B', '006', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('17', 'B', '007', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('18', 'B', '008', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('19', 'B', '009', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('20', 'B', '010', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('21', 'B', '011', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('22', 'B', '012', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('23', 'B', '013', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('24', 'B', '014', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('25', 'B', '015', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('26', 'B', '016', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('27', 'B', '017', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('28', 'B', '018', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('29', 'B', '019', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('30', 'B', '020', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('31', 'B', '021', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('32', 'B', '022', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('33', 'B', '023', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('34', 'B', '024', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('35', 'B', '025', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('36', 'B', '026', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('37', 'B', '027', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('38', 'B', '028', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('39', 'B', '029', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('40', 'B', '030', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('41', 'B', '031', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('42', 'B', '032', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('43', 'B', '033', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('44', 'B', '034', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('45', 'B', '035', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('46', 'B', '036', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('47', 'B', '037', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('48', 'B', '038', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('49', 'B', '039', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('50', 'B', '040', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('51', 'B', '041', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('52', 'B', '042', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('53', 'B', '043', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('54', 'B', '044', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('55', 'B', '045', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('56', 'B', '046', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('57', 'B', '047', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('58', 'B', '048', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('59', 'B', '049', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('60', 'B', '050', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('61', 'C', '001', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('62', 'C', '002', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('63', 'C', '003', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('64', 'C', '004', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('65', 'C', '005', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('66', 'C', '006', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('67', 'C', '007', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('68', 'C', '008', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('69', 'C', '009', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('70', 'C', '010', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('71', 'C', '011', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('72', 'C', '012', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('73', 'C', '013', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('74', 'C', '014', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('75', 'C', '015', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('76', 'C', '016', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('77', 'C', '017', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('78', 'C', '018', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('79', 'C', '019', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('80', 'C', '020', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('81', 'C', '021', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('82', 'C', '022', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('83', 'C', '023', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('84', 'C', '024', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('85', 'C', '025', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('86', 'C', '026', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('87', 'C', '027', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('88', 'C', '028', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('89', 'C', '029', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('90', 'C', '030', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('91', 'C', '031', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('92', 'C', '032', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('93', 'C', '033', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('94', 'C', '034', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('95', 'C', '035', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('96', 'C', '036', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('97', 'C', '037', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('98', 'C', '038', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('99', 'C', '039', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('100', 'C', '040', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('101', 'C', '041', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('102', 'C', '042', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('103', 'C', '043', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('104', 'C', '044', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('105', 'C', '045', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('106', 'C', '046', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('107', 'C', '047', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('108', 'C', '048', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('109', 'C', '049', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('110', 'C', '050', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('111', 'C', '051', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('112', 'C', '052', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('113', 'C', '053', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('114', 'C', '054', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('115', 'C', '055', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('116', 'C', '056', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('117', 'C', '057', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('118', 'C', '058', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('119', 'C', '059', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('120', 'C', '060', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('121', 'C', '061', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('122', 'C', '062', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('123', 'C', '063', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('124', 'C', '064', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('125', 'C', '065', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('126', 'C', '066', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('127', 'C', '067', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('128', 'C', '068', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('129', 'C', '069', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('130', 'C', '070', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('131', 'C', '071', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('132', 'C', '072', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('133', 'C', '073', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('134', 'C', '074', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('135', 'C', '075', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('136', 'C', '076', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('137', 'C', '077', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('138', 'C', '078', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('139', 'C', '079', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('140', 'C', '080', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('141', 'C', '081', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('142', 'C', '082', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('143', 'C', '083', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('144', 'C', '084', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('145', 'C', '085', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('146', 'C', '086', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('147', 'C', '087', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('148', 'C', '088', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('149', 'C', '089', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('150', 'C', '090', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('151', 'C', '091', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('152', 'C', '092', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('153', 'C', '093', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('154', 'C', '094', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('155', 'C', '095', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('156', 'C', '096', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('157', 'C', '097', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('158', 'C', '098', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('159', 'C', '099', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('160', 'C', '100', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('161', 'C', '101', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('162', 'C', '102', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('163', 'C', '103', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('164', 'C', '104', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('165', 'C', '105', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('166', 'C', '106', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('167', 'C', '107', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('168', 'C', '108', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('169', 'C', '109', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('170', 'C', '110', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('171', 'C', '111', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('172', 'C', '112', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('173', 'C', '113', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('174', 'C', '114', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('175', 'C', '115', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('176', 'C', '116', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('177', 'C', '117', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('178', 'C', '118', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('179', 'C', '119', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('180', 'C', '120', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('181', 'C', '121', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('182', 'C', '122', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('183', 'C', '123', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('184', 'C', '124', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('185', 'C', '125', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('186', 'C', '126', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('187', 'C', '127', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('188', 'C', '128', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('189', 'C', '129', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('190', 'C', '130', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('191', 'C', '131', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('192', 'C', '132', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('193', 'C', '133', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('194', 'C', '134', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('195', 'C', '135', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('196', 'C', '136', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('197', 'C', '137', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('198', 'C', '138', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('199', 'C', '139', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('200', 'C', '140', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('201', 'C', '141', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('202', 'C', '142', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('203', 'C', '143', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('204', 'C', '144', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('205', 'C', '145', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('206', 'C', '146', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('207', 'C', '147', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('208', 'C', '148', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('209', 'C', '149', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('210', 'C', '150', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('211', 'C', '151', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('212', 'C', '152', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('213', 'C', '153', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('214', 'C', '154', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('215', 'C', '155', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('216', 'C', '156', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('217', 'C', '157', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('218', 'C', '158', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('219', 'C', '159', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('220', 'C', '160', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('221', 'C', '161', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('222', 'C', '162', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('223', 'C', '163', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('224', 'C', '164', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('225', 'C', '165', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('226', 'C', '166', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('227', 'C', '167', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('228', 'C', '168', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('229', 'C', '169', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('230', 'C', '170', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('231', 'C', '171', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('232', 'C', '172', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('233', 'C', '173', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('234', 'C', '174', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('235', 'C', '175', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('236', 'C', '176', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('237', 'C', '177', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('238', 'C', '178', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('239', 'C', '179', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('240', 'C', '180', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('241', 'C', '181', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('242', 'C', '182', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('243', 'C', '183', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('244', 'C', '184', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('245', 'C', '185', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('246', 'C', '186', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('247', 'C', '187', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('248', 'C', '188', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('249', 'C', '189', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('250', 'C', '190', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('251', 'C', '191', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('252', 'C', '192', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('253', 'C', '193', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('254', 'C', '194', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('255', 'C', '195', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('256', 'C', '196', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('257', 'C', '197', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('258', 'C', '198', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('259', 'C', '199', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('260', 'C', '200', '1', '2020-04-10', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('261', 'A', '001', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('262', 'A', '002', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('263', 'A', '003', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('264', 'A', '004', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('265', 'A', '005', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('266', 'A', '006', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('267', 'A', '007', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('268', 'A', '008', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('269', 'A', '009', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('270', 'A', '010', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('271', 'B', '001', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('272', 'B', '002', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('273', 'B', '003', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('274', 'B', '004', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('275', 'B', '005', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('276', 'B', '006', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('277', 'B', '007', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('278', 'B', '008', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('279', 'B', '009', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('280', 'B', '010', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('281', 'B', '011', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('282', 'B', '012', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('283', 'B', '013', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('284', 'B', '014', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('285', 'B', '015', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('286', 'B', '016', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('287', 'B', '017', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('288', 'B', '018', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('289', 'B', '019', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('290', 'B', '020', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('291', 'B', '021', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('292', 'B', '022', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('293', 'B', '023', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('294', 'B', '024', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('295', 'B', '025', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('296', 'B', '026', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('297', 'B', '027', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('298', 'B', '028', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('299', 'B', '029', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('300', 'B', '030', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('301', 'B', '031', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('302', 'B', '032', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('303', 'B', '033', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('304', 'B', '034', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('305', 'B', '035', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('306', 'B', '036', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('307', 'B', '037', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('308', 'B', '038', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('309', 'B', '039', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('310', 'B', '040', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('311', 'B', '041', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('312', 'B', '042', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('313', 'B', '043', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('314', 'B', '044', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('315', 'B', '045', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('316', 'B', '046', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('317', 'B', '047', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('318', 'B', '048', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('319', 'B', '049', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('320', 'B', '050', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('321', 'C', '001', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('322', 'C', '002', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('323', 'C', '003', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('324', 'C', '004', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('325', 'C', '005', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('326', 'C', '006', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('327', 'C', '007', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('328', 'C', '008', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('329', 'C', '009', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('330', 'C', '010', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('331', 'C', '011', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('332', 'C', '012', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('333', 'C', '013', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('334', 'C', '014', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('335', 'C', '015', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('336', 'C', '016', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('337', 'C', '017', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('338', 'C', '018', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('339', 'C', '019', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('340', 'C', '020', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('341', 'C', '021', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('342', 'C', '022', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('343', 'C', '023', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('344', 'C', '024', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('345', 'C', '025', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('346', 'C', '026', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('347', 'C', '027', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('348', 'C', '028', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('349', 'C', '029', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('350', 'C', '030', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('351', 'C', '031', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('352', 'C', '032', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('353', 'C', '033', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('354', 'C', '034', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('355', 'C', '035', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('356', 'C', '036', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('357', 'C', '037', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('358', 'C', '038', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('359', 'C', '039', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('360', 'C', '040', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('361', 'C', '041', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('362', 'C', '042', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('363', 'C', '043', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('364', 'C', '044', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('365', 'C', '045', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('366', 'C', '046', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('367', 'C', '047', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('368', 'C', '048', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('369', 'C', '049', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('370', 'C', '050', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('371', 'C', '051', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('372', 'C', '052', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('373', 'C', '053', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('374', 'C', '054', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('375', 'C', '055', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('376', 'C', '056', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('377', 'C', '057', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('378', 'C', '058', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('379', 'C', '059', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('380', 'C', '060', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('381', 'C', '061', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('382', 'C', '062', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('383', 'C', '063', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('384', 'C', '064', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('385', 'C', '065', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('386', 'C', '066', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('387', 'C', '067', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('388', 'C', '068', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('389', 'C', '069', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('390', 'C', '070', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('391', 'C', '071', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('392', 'C', '072', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('393', 'C', '073', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('394', 'C', '074', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('395', 'C', '075', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('396', 'C', '076', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('397', 'C', '077', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('398', 'C', '078', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('399', 'C', '079', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('400', 'C', '080', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('401', 'C', '081', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('402', 'C', '082', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('403', 'C', '083', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('404', 'C', '084', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('405', 'C', '085', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('406', 'C', '086', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('407', 'C', '087', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('408', 'C', '088', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('409', 'C', '089', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('410', 'C', '090', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('411', 'C', '091', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('412', 'C', '092', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('413', 'C', '093', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('414', 'C', '094', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('415', 'C', '095', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('416', 'C', '096', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('417', 'C', '097', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('418', 'C', '098', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('419', 'C', '099', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('420', 'C', '100', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('421', 'C', '101', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('422', 'C', '102', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('423', 'C', '103', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('424', 'C', '104', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('425', 'C', '105', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('426', 'C', '106', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('427', 'C', '107', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('428', 'C', '108', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('429', 'C', '109', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('430', 'C', '110', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('431', 'C', '111', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('432', 'C', '112', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('433', 'C', '113', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('434', 'C', '114', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('435', 'C', '115', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('436', 'C', '116', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('437', 'C', '117', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('438', 'C', '118', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('439', 'C', '119', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('440', 'C', '120', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('441', 'C', '121', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('442', 'C', '122', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('443', 'C', '123', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('444', 'C', '124', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('445', 'C', '125', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('446', 'C', '126', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('447', 'C', '127', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('448', 'C', '128', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('449', 'C', '129', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('450', 'C', '130', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('451', 'C', '131', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('452', 'C', '132', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('453', 'C', '133', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('454', 'C', '134', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('455', 'C', '135', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('456', 'C', '136', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('457', 'C', '137', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('458', 'C', '138', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('459', 'C', '139', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('460', 'C', '140', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('461', 'C', '141', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('462', 'C', '142', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('463', 'C', '143', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('464', 'C', '144', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('465', 'C', '145', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('466', 'C', '146', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('467', 'C', '147', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('468', 'C', '148', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('469', 'C', '149', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('470', 'C', '150', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('471', 'C', '151', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('472', 'C', '152', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('473', 'C', '153', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('474', 'C', '154', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('475', 'C', '155', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('476', 'C', '156', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('477', 'C', '157', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('478', 'C', '158', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('479', 'C', '159', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('480', 'C', '160', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('481', 'C', '161', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('482', 'C', '162', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('483', 'C', '163', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('484', 'C', '164', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('485', 'C', '165', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('486', 'C', '166', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('487', 'C', '167', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('488', 'C', '168', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('489', 'C', '169', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('490', 'C', '170', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('491', 'C', '171', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('492', 'C', '172', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('493', 'C', '173', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('494', 'C', '174', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('495', 'C', '175', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('496', 'C', '176', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('497', 'C', '177', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('498', 'C', '178', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('499', 'C', '179', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('500', 'C', '180', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('501', 'C', '181', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('502', 'C', '182', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('503', 'C', '183', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('504', 'C', '184', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('505', 'C', '185', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('506', 'C', '186', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('507', 'C', '187', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('508', 'C', '188', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('509', 'C', '189', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('510', 'C', '190', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('511', 'C', '191', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('512', 'C', '192', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('513', 'C', '193', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('514', 'C', '194', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('515', 'C', '195', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('516', 'C', '196', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('517', 'C', '197', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('518', 'C', '198', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('519', 'C', '199', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('520', 'C', '200', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('521', 'A', '001', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('522', 'A', '002', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('523', 'A', '003', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('524', 'A', '004', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('525', 'A', '005', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('526', 'A', '006', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('527', 'A', '007', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('528', 'A', '008', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('529', 'A', '009', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('530', 'A', '010', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('531', 'B', '001', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('532', 'B', '002', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('533', 'B', '003', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('534', 'B', '004', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('535', 'B', '005', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('536', 'B', '006', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('537', 'B', '007', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('538', 'B', '008', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('539', 'B', '009', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('540', 'B', '010', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('541', 'B', '011', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('542', 'B', '012', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('543', 'B', '013', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('544', 'B', '014', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('545', 'B', '015', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('546', 'B', '016', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('547', 'B', '017', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('548', 'B', '018', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('549', 'B', '019', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('550', 'B', '020', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('551', 'B', '021', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('552', 'B', '022', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('553', 'B', '023', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('554', 'B', '024', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('555', 'B', '025', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('556', 'B', '026', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('557', 'B', '027', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('558', 'B', '028', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('559', 'B', '029', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('560', 'B', '030', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('561', 'B', '031', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('562', 'B', '032', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('563', 'B', '033', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('564', 'B', '034', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('565', 'B', '035', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('566', 'B', '036', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('567', 'B', '037', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('568', 'B', '038', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('569', 'B', '039', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('570', 'B', '040', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('571', 'B', '041', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('572', 'B', '042', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('573', 'B', '043', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('574', 'B', '044', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('575', 'B', '045', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('576', 'B', '046', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('577', 'B', '047', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('578', 'B', '048', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('579', 'B', '049', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('580', 'B', '050', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('581', 'C', '001', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('582', 'C', '002', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('583', 'C', '003', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('584', 'C', '004', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('585', 'C', '005', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('586', 'C', '006', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('587', 'C', '007', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('588', 'C', '008', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('589', 'C', '009', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('590', 'C', '010', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('591', 'C', '011', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('592', 'C', '012', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('593', 'C', '013', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('594', 'C', '014', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('595', 'C', '015', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('596', 'C', '016', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('597', 'C', '017', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('598', 'C', '018', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('599', 'C', '019', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('600', 'C', '020', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('601', 'C', '021', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('602', 'C', '022', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('603', 'C', '023', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('604', 'C', '024', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('605', 'C', '025', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('606', 'C', '026', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('607', 'C', '027', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('608', 'C', '028', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('609', 'C', '029', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('610', 'C', '030', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('611', 'C', '031', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('612', 'C', '032', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('613', 'C', '033', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('614', 'C', '034', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('615', 'C', '035', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('616', 'C', '036', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('617', 'C', '037', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('618', 'C', '038', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('619', 'C', '039', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('620', 'C', '040', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('621', 'C', '041', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('622', 'C', '042', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('623', 'C', '043', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('624', 'C', '044', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('625', 'C', '045', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('626', 'C', '046', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('627', 'C', '047', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('628', 'C', '048', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('629', 'C', '049', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('630', 'C', '050', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('631', 'C', '051', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('632', 'C', '052', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('633', 'C', '053', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('634', 'C', '054', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('635', 'C', '055', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('636', 'C', '056', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('637', 'C', '057', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('638', 'C', '058', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('639', 'C', '059', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('640', 'C', '060', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('641', 'C', '061', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('642', 'C', '062', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('643', 'C', '063', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('644', 'C', '064', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('645', 'C', '065', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('646', 'C', '066', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('647', 'C', '067', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('648', 'C', '068', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('649', 'C', '069', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('650', 'C', '070', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('651', 'C', '071', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('652', 'C', '072', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('653', 'C', '073', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('654', 'C', '074', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('655', 'C', '075', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('656', 'C', '076', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('657', 'C', '077', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('658', 'C', '078', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('659', 'C', '079', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('660', 'C', '080', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('661', 'C', '081', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('662', 'C', '082', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('663', 'C', '083', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('664', 'C', '084', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('665', 'C', '085', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('666', 'C', '086', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('667', 'C', '087', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('668', 'C', '088', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('669', 'C', '089', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('670', 'C', '090', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('671', 'C', '091', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('672', 'C', '092', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('673', 'C', '093', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('674', 'C', '094', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('675', 'C', '095', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('676', 'C', '096', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('677', 'C', '097', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('678', 'C', '098', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('679', 'C', '099', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('680', 'C', '100', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('681', 'C', '101', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('682', 'C', '102', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('683', 'C', '103', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('684', 'C', '104', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('685', 'C', '105', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('686', 'C', '106', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('687', 'C', '107', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('688', 'C', '108', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('689', 'C', '109', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('690', 'C', '110', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('691', 'C', '111', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('692', 'C', '112', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('693', 'C', '113', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('694', 'C', '114', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('695', 'C', '115', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('696', 'C', '116', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('697', 'C', '117', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('698', 'C', '118', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('699', 'C', '119', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('700', 'C', '120', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('701', 'C', '121', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('702', 'C', '122', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('703', 'C', '123', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('704', 'C', '124', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('705', 'C', '125', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('706', 'C', '126', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('707', 'C', '127', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('708', 'C', '128', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('709', 'C', '129', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('710', 'C', '130', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('711', 'C', '131', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('712', 'C', '132', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('713', 'C', '133', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('714', 'C', '134', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('715', 'C', '135', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('716', 'C', '136', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('717', 'C', '137', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('718', 'C', '138', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('719', 'C', '139', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('720', 'C', '140', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('721', 'C', '141', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('722', 'C', '142', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('723', 'C', '143', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('724', 'C', '144', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('725', 'C', '145', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('726', 'C', '146', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('727', 'C', '147', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('728', 'C', '148', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('729', 'C', '149', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('730', 'C', '150', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('731', 'C', '151', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('732', 'C', '152', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('733', 'C', '153', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('734', 'C', '154', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('735', 'C', '155', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('736', 'C', '156', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('737', 'C', '157', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('738', 'C', '158', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('739', 'C', '159', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('740', 'C', '160', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('741', 'C', '161', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('742', 'C', '162', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('743', 'C', '163', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('744', 'C', '164', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('745', 'C', '165', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('746', 'C', '166', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('747', 'C', '167', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('748', 'C', '168', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('749', 'C', '169', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('750', 'C', '170', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('751', 'C', '171', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('752', 'C', '172', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('753', 'C', '173', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('754', 'C', '174', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('755', 'C', '175', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('756', 'C', '176', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('757', 'C', '177', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('758', 'C', '178', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('759', 'C', '179', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('760', 'C', '180', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('761', 'C', '181', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('762', 'C', '182', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('763', 'C', '183', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('764', 'C', '184', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('765', 'C', '185', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('766', 'C', '186', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('767', 'C', '187', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('768', 'C', '188', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('769', 'C', '189', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('770', 'C', '190', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('771', 'C', '191', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('772', 'C', '192', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('773', 'C', '193', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('774', 'C', '194', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('775', 'C', '195', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('776', 'C', '196', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('777', 'C', '197', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('778', 'C', '198', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('779', 'C', '199', '1', '2020-04-11', 'NORMAL');
INSERT INTO `ticketseat` VALUES ('780', 'C', '200', '1', '2020-04-11', 'NORMAL');

-- ----------------------------
-- Table structure for train
-- ----------------------------
DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `trainId` int(11) NOT NULL AUTO_INCREMENT COMMENT '车次id',
  `trainNum` varchar(255) NOT NULL COMMENT '车次号',
  `trainTypeId` int(11) NOT NULL COMMENT '列车类型id',
  `startTime` time NOT NULL COMMENT '出发时间',
  `endTime` time NOT NULL COMMENT '到达时间',
  `startStationid` int(11) NOT NULL COMMENT '出发站id',
  `startStationName` varchar(255) NOT NULL COMMENT '出发站名称',
  `endStationid` int(11) NOT NULL COMMENT '到达站id',
  `endStationName` varchar(255) NOT NULL COMMENT '到达站名称',
  PRIMARY KEY (`trainId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of train
-- ----------------------------
INSERT INTO `train` VALUES ('1', 'G001', '1', '19:00:00', '07:00:00', '1', '北京', '2', '上海');

-- ----------------------------
-- Table structure for traintype
-- ----------------------------
DROP TABLE IF EXISTS `traintype`;
CREATE TABLE `traintype` (
  `trainTypeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '列车类型id',
  `ANum` int(11) NOT NULL COMMENT 'A座数量',
  `BNum` int(11) NOT NULL COMMENT 'B座数量',
  `CNum` int(11) NOT NULL COMMENT 'C座数量',
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
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userName` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `identityNum` varchar(255) DEFAULT NULL COMMENT '证件号',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `type` varchar(255) NOT NULL COMMENT '用户类型:普通用户，管理员',
  `activated` boolean DEFAULT TRUE COMMENT '账号激活状态',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'gzy', 'D3B039EADE6DB21031C208444CFC38EA', null, '666666', '18817555183', '123@qq.com', 'User',true);
INSERT INTO `user` (userName,password,email,phone,name,identityNum,type) VALUES ('superadmin','4297f44b13955235245b2497399d7a93',null,'1111111','123456789','admin@qq.com','admin');
