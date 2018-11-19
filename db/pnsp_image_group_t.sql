/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : pnsp

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-11-19 11:57:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pnsp_image_group_t
-- ----------------------------
DROP TABLE IF EXISTS `pnsp_image_group_t`;
CREATE TABLE `pnsp_image_group_t` (
  `id` varchar(32) DEFAULT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '分组名称',
  `descr` varchar(255) DEFAULT NULL COMMENT '分组描述',
  `add_oprid` varchar(50) DEFAULT NULL,
  `add_dttm` datetime DEFAULT NULL,
  `upd_oprid` varchar(50) DEFAULT NULL,
  `upd_dttm` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='--图片资源分组表\r\n--pnsp_image_group_t \r\n--查询所有\r\nselect id,name,descr,add_oprid,add_dttm,upd_oprid,upd_dttm from pnsp_image_group_t;';

-- ----------------------------
-- Records of pnsp_image_group_t
-- ----------------------------
INSERT INTO `pnsp_image_group_t` VALUES ('P0001', '首页顶部循环图', '首页顶部循环图', '88888888888', '2018-09-09 00:00:00', '88888888888', '2018-09-09 00:00:00');
