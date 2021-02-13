/*
Navicat MySQL Data Transfer

Source Server         : 118.190.201.78
Source Server Version : 50635
Source Host           : 118.190.201.78:32306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2021-02-10 11:24:13
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
INSERT INTO `Address` VALUES ('100110', '10011', 'China', 'Shandong', 'Jinan', 'Lixiaqu', 'Happy street No.12', '010-88896666');
INSERT INTO `Address` VALUES ('100111', '10011', 'China', 'Zhejiang', 'Hangzhou', 'Xihuqu', 'The park of Gushan', '010-88896666');
INSERT INTO `Address` VALUES ('1000100', '10001', '中国', '湖北', '武汉', '洪山区', '珞瑜路726号', '13300224466');
INSERT INTO `Address` VALUES ('1000101', '10001', '中国', '湖北', '荆州', '沙市区', '北京西路410号', '13388990123');
INSERT INTO `Address` VALUES ('1000200', '10002', '中国', '山东', '济南', '历下区', '山大路202号', '13422584349');

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
INSERT INTO `Customer` VALUES ('10003', '巫行云', '女', '1985-12-05', '110100198512057777', '19216212345');
INSERT INTO `Customer` VALUES ('10004', '常青子', '男', '1975-10-20', '110100197510207812', '13613671267');
INSERT INTO `Customer` VALUES ('10010', 'Johnwood', 'Male', '1999-01-01', '110211199901013322', '010-88897070');
INSERT INTO `Customer` VALUES ('10011', 'Swaarzi', 'Male', '1995-01-01', '110211199501013344', '010-88896666');

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
INSERT INTO `Order` VALUES ('10010', '10001', '1000100', '5000.00', '2019-12-29 00:00:00', 'CREATE');
INSERT INTO `Order` VALUES ('10011', '10001', '1000100', '6600.00', '2019-12-29 00:00:00', 'CREATE');
INSERT INTO `Order` VALUES ('20001', '10001', '1000100', '8026.00', '2020-12-28 17:07:19', 'CREATE');

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
INSERT INTO `OrderItem` VALUES ('100110', '10011', '30001', '1', '4000.00', '4000.00');
INSERT INTO `OrderItem` VALUES ('100111', '10011', '30002', '100', '26.00', '2600.00');
INSERT INTO `OrderItem` VALUES ('200010', '20001', '30003', '2', '4000.00', '8000.00');
INSERT INTO `OrderItem` VALUES ('200011', '20001', '30004', '1', '26.00', '26.00');

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
  `image` varchar(255) DEFAULT NULL,
  `original_price` decimal(10,2) DEFAULT NULL,
  `tip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Product
-- ----------------------------
INSERT INTO `Product` VALUES ('30001', 'Apple iPhone X 256GB 深空灰色 移动联通电信4G手机', '4000.00', '台', '20001', '电子产品', '/static/img/product1.jpg', '5600.00', '自营');
INSERT INTO `Product` VALUES ('30002', 'Apple iPad 平板电脑 2018年新款9.7英寸', '12600.00', '台', '20001', '电子产品', '/static/img/product2.jpg', '13000.00', '优惠');
INSERT INTO `Product` VALUES ('30003', 'Apple MacBook Pro 13.3英寸笔记本电脑（2017款Core i5处理器/8GB内存/256GB硬盘 MupxT2CH/A）', '10688.00', '台', '20001', '电子产品', '/static/img/product3.jpg', '12999.00', '秒杀');
INSERT INTO `Product` VALUES ('30004', 'Kindle Paperwhite电纸书阅读器 电子书墨水屏 6英寸wifi 黑色', '958.00', '个', '20002', '电子产品', '/static/img/product4.jpg', '999.00', '秒杀');
INSERT INTO `Product` VALUES ('30005', '微软（Microsoft）新Surface Pro 二合一平板电脑笔记本 12.3英寸（i5 8G内存 256G存储）', '8288.00', '台', '20002', '电子产品', '/static/img/product5.jpg', '8888.00', '优惠');
INSERT INTO `Product` VALUES ('30006', 'Apple Watch Series 3智能手表（GPS款 42毫米 深空灰色铝金属表壳 黑色运动型表带 MQL12CH/A）', '2799.00', '块', '20002', '电子产品', '/static/img/product6.jpg', '2899.00', '自营');

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
