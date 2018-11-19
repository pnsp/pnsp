/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : pnsp

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-11-19 11:58:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pnsp_user_t
-- ----------------------------
DROP TABLE IF EXISTS `pnsp_user_t`;
CREATE TABLE `pnsp_user_t` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `last_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `id_card` varchar(50) DEFAULT '' COMMENT '身份证',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `sex` varchar(5) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `eff_status` varchar(5) DEFAULT NULL COMMENT '状态：1 正常 0 失效',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色主键',
  `add_oprid` varchar(50) DEFAULT NULL,
  `add_dttm` datetime DEFAULT NULL,
  `upd_oprid` varchar(50) DEFAULT NULL,
  `upd_dttm` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='--用户表\r\n--pnsp_user_t \r\n--查询所有\r\nselect id,last_name,phone,id_card,email,sex,age,password,eff_status,role_id,add_oprid,add_dttm,upd_oprid,upd_dttm from pnsp_user_t;\r\n';

-- ----------------------------
-- Records of pnsp_user_t
-- ----------------------------
INSERT INTO `pnsp_user_t` VALUES ('4e4fd34e353e409ba3f77503690ab35b', '', '13670253693', '', '', '', '20', '54b79a401753aa1a0edea47466978881', '1', 'R001', '88888888888', '2018-07-18 15:34:47', '88888888888', '2018-07-18 15:34:47');
INSERT INTO `pnsp_user_t` VALUES ('5130cfc0340d40bea6eaf175cd0c069b', '', '13670253691', '', '', '', '20', '54b79a401753aa1a0edea47466978881', '1', 'R001', '88888888888', '2018-07-18 14:56:45', '88888888888', '2018-07-18 14:56:45');
INSERT INTO `pnsp_user_t` VALUES ('b8ce6734f7074e538ff5c2e7e4569308', '', '13800002222', '', '', '', '20', '54b79a401753aa1a0edea47466978881', '1', 'R001', '88888888888', '2018-07-18 15:04:11', '88888888888', '2018-07-18 15:04:11');
INSERT INTO `pnsp_user_t` VALUES ('cb86398ccaa54fc88ef477bdb428550e', '', '13670253692', '', '', '', '19', '54b79a401753aa1a0edea47466978881', '1', 'R001', '88888888888', '2018-07-18 15:02:37', '88888888888', '2018-07-18 15:02:37');
INSERT INTO `pnsp_user_t` VALUES ('f22ace4be2674ab993caba70f89cbe4b', '', '13812235455', '', '', '', '18', '54b79a401753aa1a0edea47466978881', '1', 'R001', '88888888888', '2018-07-18 15:05:03', '88888888888', '2018-07-18 15:05:03');
