/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : pnsp

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-11-19 11:57:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pnsp_image_t
-- ----------------------------
DROP TABLE IF EXISTS `pnsp_image_t`;
CREATE TABLE `pnsp_image_t` (
  `id` varchar(32) DEFAULT NULL COMMENT '主键',
  `group_id` varchar(32) DEFAULT NULL COMMENT '分组主键',
  `name` varchar(50) DEFAULT NULL COMMENT '图片名称',
  `descr` varchar(255) DEFAULT NULL COMMENT '图片描述',
  `router_name` varchar(50) DEFAULT NULL COMMENT 'router插件name标识',
  `router_path` varchar(255) DEFAULT NULL COMMENT 'router插件path路径',
  `url` varchar(255) DEFAULT NULL COMMENT '图片资源url',
  `src_name` varchar(50) DEFAULT '' COMMENT '本地文件名(无后缀)',
  `suffix` varchar(50) DEFAULT NULL COMMENT '后缀/类型',
  `eff_status` varchar(5) DEFAULT NULL COMMENT '状态：1 正常 0 失效',
  `add_oprid` varchar(50) DEFAULT NULL,
  `add_dttm` datetime DEFAULT NULL,
  `upd_oprid` varchar(50) DEFAULT NULL,
  `upd_dttm` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='--图片资源表\r\n--pnsp_image_t\r\n--查询所有\r\nselect id,group_id,name,descr,router_name,router_path,url,src_name,suffix,eff_status,add_oprid,add_dttm,upd_oprid,upd_dttm from pnsp_image_t;';

-- ----------------------------
-- Records of pnsp_image_t
-- ----------------------------
INSERT INTO `pnsp_image_t` VALUES ('1', 'P0001', '图片1', '图片1', 'cycle', null, null, '1', '.png', '1', '88888888888', '2018-09-09 00:00:00', '88888888888', '2018-09-09 00:00:00');
INSERT INTO `pnsp_image_t` VALUES ('2', 'P0001', '图片2', '图片2', 'cycle', '', '', '2', '.png', '1', '88888888888', '2018-09-09 00:00:00', '88888888888', '2018-09-09 00:00:00');
INSERT INTO `pnsp_image_t` VALUES ('3', 'P0001', '图片3', '图片3', 'cycle', '', '', '3', '.png', '1', '88888888888', '2018-09-09 00:00:00', '88888888888', '2018-09-09 00:00:00');
INSERT INTO `pnsp_image_t` VALUES ('4', 'P0001', '图片4', '图片4', 'cycle', '', '', '4', '.png', '1', '88888888888', '2018-09-09 00:00:00', '88888888888', '2018-09-09 00:00:00');
INSERT INTO `pnsp_image_t` VALUES ('5', 'P0001', '图片5', '图片5', 'cycle', '', '', '5', '.png', '1', '88888888888', '2018-09-09 00:00:00', '88888888888', '2018-09-09 00:00:00');
