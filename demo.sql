/*
Navicat MySQL Data Transfer

Source Server         : 47.105.194.89
Source Server Version : 50635
Source Host           : 47.105.194.89:32306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2020-02-26 22:04:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for Address
-- ----------------------------
DROP TABLE IF EXISTS `Address`;
CREATE TABLE `Address` (
  `id` int(20) NOT NULL,
  `customer_id` int(20) DEFAULT NULL,
  `country` varchar(40) DEFAULT NULL,
  `province` varchar(40) DEFAULT NULL,
  `city` varchar(40) DEFAULT NULL,
  `zone` varchar(40) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Address
-- ----------------------------

-- ----------------------------
-- Table structure for Customer
-- ----------------------------
DROP TABLE IF EXISTS `Customer`;
CREATE TABLE `Customer` (
  `id` int(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `identification` varchar(20) DEFAULT NULL,
  `phone_number` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Customer
-- ----------------------------
INSERT INTO `Customer` VALUES ('10001', '李秋水', '女', '1979-10-01', '510110197910012312', '13388990123');
INSERT INTO `Customer` VALUES ('10002', '逍遥子', '男', '1976-07-10', '510110197607103322', '13422584349');

-- ----------------------------
-- Table structure for Order
-- ----------------------------
DROP TABLE IF EXISTS `Order`;
CREATE TABLE `Order` (
  `id` int(20) NOT NULL,
  `customer_id` int(20) DEFAULT NULL,
  `address_id` int(20) DEFAULT NULL,
  `amount` decimal(20,2) DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `flag` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Order
-- ----------------------------

-- ----------------------------
-- Table structure for OrderItem
-- ----------------------------
DROP TABLE IF EXISTS `OrderItem`;
CREATE TABLE `OrderItem` (
  `id` int(20) NOT NULL,
  `order_id` int(20) DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  `quantity` int(20) DEFAULT NULL,
  `price` decimal(20,2) DEFAULT NULL,
  `amount` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OrderItem
-- ----------------------------

-- ----------------------------
-- Table structure for Product
-- ----------------------------
DROP TABLE IF EXISTS `Product`;
CREATE TABLE `Product` (
  `id` int(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `unit` varchar(20) DEFAULT NULL,
  `supplier_id` int(20) DEFAULT NULL,
  `classify` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Product
-- ----------------------------
INSERT INTO `Product` VALUES ('30001', '笔记本电脑', '4000.00', '台', '20001', '电子产品');
INSERT INTO `Product` VALUES ('30002', '打印机', '12600.00', '台', '20001', '电子产品');
INSERT INTO `Product` VALUES ('30003', '记事本', '2.00', '本', '20002', '办公用品');
INSERT INTO `Product` VALUES ('30004', '计算器', '26.00', '个', '20002', '办公用品');
INSERT INTO `Product` VALUES ('30005', '签字笔', '50.00', '盒', '20002', '办公用品');

-- ----------------------------
-- Table structure for Supplier
-- ----------------------------
DROP TABLE IF EXISTS `Supplier`;
CREATE TABLE `Supplier` (
  `id` int(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Supplier
-- ----------------------------
INSERT INTO `Supplier` VALUES ('20001', '国际商用机器公司(IBM)');
INSERT INTO `Supplier` VALUES ('20002', '上海晨光文具股份有限公司(M&G)');
