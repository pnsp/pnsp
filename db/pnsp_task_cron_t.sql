/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : pnsp

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-11-19 11:58:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pnsp_task_cron_t
-- ----------------------------
DROP TABLE IF EXISTS `pnsp_task_cron_t`;
CREATE TABLE `pnsp_task_cron_t` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `task_name` varchar(50) DEFAULT NULL COMMENT '定时任务函数名',
  `task_descr` varchar(255) DEFAULT NULL COMMENT '定时任务描述',
  `cron` varchar(50) DEFAULT NULL COMMENT '定时任务周期表达式',
  `effdt` date DEFAULT NULL COMMENT '生效日期',
  `eff_status` varchar(1) DEFAULT NULL COMMENT '生效状态：1 正常 0 失效',
  `add_dttm` datetime DEFAULT NULL,
  `add_oprid` varchar(50) DEFAULT NULL,
  `upd_dttm` datetime DEFAULT NULL,
  `upd_oprid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='--定时任务周期配置表\r\n--pnsp_task_cron_t\r\n--查询所有\r\nselect id,task_name,cron,effdt,eff_status,add_oprid,add_dttm,upd_oprid,upd_dttm from pnsp_task_cron_t;';

-- ----------------------------
-- Records of pnsp_task_cron_t
-- ----------------------------
INSERT INTO `pnsp_task_cron_t` VALUES ('1', 'testTaskRun', '测试定时任务', '0/5 * * * * *', '2018-10-17', '0', '2018-10-18 14:03:39', '88888888888', '2018-10-18 14:03:42', '88888888888');
