/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : pnsp

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-11-19 11:58:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pnsp_op_log_t
-- ----------------------------
DROP TABLE IF EXISTS `pnsp_op_log_t`;
CREATE TABLE `pnsp_op_log_t` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `phone` varchar(50) DEFAULT NULL COMMENT '操作人手机号码',
  `req_url` varchar(255) DEFAULT NULL COMMENT '请求路径',
  `op_content` varchar(2000) DEFAULT NULL COMMENT '操作内容',
  `op_type` varchar(10) DEFAULT NULL COMMENT '操作类型',
  `op_descr` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `op_status` varchar(1) DEFAULT NULL COMMENT '操作状态：0 失败 1 成功',
  `op_ip` varchar(50) DEFAULT NULL COMMENT '操作人IP',
  `op_mac` varchar(50) DEFAULT NULL COMMENT '操作人mac',
  `op_dttm` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
  `key_word` varchar(255) DEFAULT NULL COMMENT '关键字',
  `add_oprid` varchar(50) DEFAULT NULL,
  `add_dttm` datetime DEFAULT NULL,
  `upd_oprid` varchar(50) DEFAULT NULL,
  `upd_dttm` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='--日志记录表\r\n--pnsp_op_log_t\r\n--查询所有\r\nselect id,phone,req_url,op_content,op_type,op_descr,op_status,op_ip,op_mac,op_dttm,key_word,add_oprid,add_dttm,upd_oprid,upd_dttm from pnsp_op_log_t;\r\n--起始时间到目前时间的日志按分钟统计\r\nselect \r\nconcat(\r\nextract(year from op_dttm),''-'',\r\nextract(month from op_dttm),''-'',\r\nextract(day from op_dttm)\r\n)as date,\r\nconcat(\r\nextract(hour from op_dttm),'':'',\r\nextract(minute from op_dttm)\r\n)as time,\r\ncount(0) as quality\r\nfrom pnsp_op_log_t polt\r\nwhere polt.op_dttm between ''2018-01-01'' and current_timestamp\r\ngroup by extract(minute from polt.op_dttm)';

-- ----------------------------
-- Records of pnsp_op_log_t
-- ----------------------------
INSERT INTO `pnsp_op_log_t` VALUES ('061bce536bc846a184f9b7e4332141a3', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', '00-00-00-00-00-00', '2018-10-24 12:21:50', 'getAuthCode', '88888888888', '2018-10-24 12:21:50', '88888888888', '2018-10-24 12:21:50');
INSERT INTO `pnsp_op_log_t` VALUES ('080813acc595481292a2ed13c38c2ee2', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', '00-00-00-00-00-00', '2018-10-24 12:21:51', 'getAuthCode', '88888888888', '2018-10-24 12:21:51', '88888888888', '2018-10-24 12:21:51');
INSERT INTO `pnsp_op_log_t` VALUES ('361a7887d05e497ba9c62f70d7a218cd', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', 'a8-7c-01-52-b0-2e', '2018-11-15 01:24:17', 'getAuthCode', '88888888888', '2018-11-15 01:24:17', '88888888888', '2018-11-15 01:24:17');
INSERT INTO `pnsp_op_log_t` VALUES ('37e65664958f49df835d368eb051c22e', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', '94-87-e0-06-d9-ae', '2018-11-15 01:31:21', 'getAuthCode', '88888888888', '2018-11-15 01:31:21', '88888888888', '2018-11-15 01:31:21');
INSERT INTO `pnsp_op_log_t` VALUES ('3fd9246e9be34aad9405a91c8f644f6e', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', '00-00-00-00-00-00', '2018-10-24 12:22:59', 'getAuthCode', '88888888888', '2018-10-24 12:22:59', '88888888888', '2018-10-24 12:22:59');
INSERT INTO `pnsp_op_log_t` VALUES ('4137e658daef4eb8a9b46854cfea66f9', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', 'a8-7c-01-52-b0-2e', '2018-11-15 01:11:17', 'getAuthCode', '88888888888', '2018-11-15 01:11:17', '88888888888', '2018-11-15 01:11:17');
INSERT INTO `pnsp_op_log_t` VALUES ('44bb531e15e9457bafd0454e77193145', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', '00-00-00-00-00-00', '2018-10-24 12:23:05', 'getAuthCode', '88888888888', '2018-10-24 12:23:05', '88888888888', '2018-10-24 12:23:05');
INSERT INTO `pnsp_op_log_t` VALUES ('52603234b0c844a6b81401fcd9be1476', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', '00-00-00-00-00-00', '2018-10-24 12:21:51', 'getAuthCode', '88888888888', '2018-10-24 12:21:51', '88888888888', '2018-10-24 12:21:51');
INSERT INTO `pnsp_op_log_t` VALUES ('52a6f0de065c408fa6977b1a398ec9ac', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', '00-00-00-00-00-00', '2018-10-24 12:22:59', 'getAuthCode', '88888888888', '2018-10-24 12:22:59', '88888888888', '2018-10-24 12:22:59');
INSERT INTO `pnsp_op_log_t` VALUES ('53f36df938644243931b88a5505a812f', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', '00-00-00-00-00-00', '2018-10-24 12:21:50', 'getAuthCode', '88888888888', '2018-10-24 12:21:50', '88888888888', '2018-10-24 12:21:50');
INSERT INTO `pnsp_op_log_t` VALUES ('77a700562d204667bbc409d0181768f2', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', '00-00-00-00-00-00', '2018-10-24 12:23:12', 'getAuthCode', '88888888888', '2018-10-24 12:23:12', '88888888888', '2018-10-24 12:23:12');
INSERT INTO `pnsp_op_log_t` VALUES ('c3e2f875c01547f1a840c5bc01c7544c', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', '00-00-00-00-00-00', '2018-10-24 12:22:59', 'getAuthCode', '88888888888', '2018-10-24 12:22:59', '88888888888', '2018-10-24 12:22:59');
INSERT INTO `pnsp_op_log_t` VALUES ('d8a406d99808477da7536ebebb1c91cc', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', '00-00-00-00-00-00', '2018-10-24 12:21:50', 'getAuthCode', '88888888888', '2018-10-24 12:21:50', '88888888888', '2018-10-24 12:21:50');
INSERT INTO `pnsp_op_log_t` VALUES ('e863edd1f4e9404e8bf9237e991cf5e8', '88888888888', '/pnspUserT/getAuthCode', '', 'query', 'getAuthCode', '1', '127.0.0.1', '00-00-00-00-00-00', '2018-10-24 12:21:14', 'getAuthCode', '88888888888', '2018-10-24 12:21:14', '88888888888', '2018-10-24 12:21:14');
