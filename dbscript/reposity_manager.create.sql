USE repo_manager;
-- 仓库管理表
DROP TABLE IF EXISTS `tbl_reposity_info`; 
CREATE TABLE `tbl_reposity_info` (
  `repo_code` varchar(8) NOT NULL,
  `sep_num` int(8) NOT NULL DEFAULT 0,
  `admin` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`repo_code`)
) DEFAULT CHARSET=utf8;

-- 果农信息表
DROP TABLE IF EXISTS `tbl_farmer_info`; 
CREATE TABLE `tbl_farmer_info` (
  `storage_sn` varchar(11) NOT NULL,
  `farmer_name` varchar(50) NOT NULL,
  `farmer_tel` varchar(50) NOT NULL,
  `unit_price` varchar(11) NOT NULL,
  `status` int(2) NOT NULL DEFAULT 1,
  PRIMARY KEY (`storage_sn`)
) DEFAULT CHARSET=utf8;

-- 库存记录表(用入入库、出库记录)
DROP TABLE IF EXISTS `tbl_storage_record`; 
CREATE TABLE `tbl_storage_record` (
  `id` int NOT NULL auto_increment,
  `storage_sn` varchar(11) NOT NULL,
  `apple_type` int(3) NOT NULL,
  `apple_size` int(3) NOT NULL,
  `repo_code` varchar(8) NOT NULL,
  `repo_sep` int(8) NOT NULL,
  `goods_count` int(8) NOT NULL,
  `total_price` varchar(11) NOT NULL,
  `operate_time` datetime,
  `type` int(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

-- 库存记录表(用存量记录)
DROP TABLE IF EXISTS `tbl_storage_keep`; 
CREATE TABLE `tbl_storage_keep` (
  `id` int NOT NULL auto_increment,
  `storage_sn` varchar(11) NOT NULL,
  `apple_type` int(3) NOT NULL,
  `apple_size` int(3) NOT NULL,
  `repo_code` varchar(8) NOT NULL,
  `repo_sep` int(8) NOT NULL,
  `goods_count` int(8) NOT NULL,
  `total_price` varchar(11) NOT NULL,
  `operate_time` datetime,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`storage_sn`, `apple_type`, `apple_size`, `repo_code`, `repo_sep`)
  
) DEFAULT CHARSET=utf8;

-- 付款统计表
DROP TABLE IF EXISTS `tbl_pay_stat`; 
CREATE TABLE `tbl_pay_stat` (
  `storage_sn` varchar(11) NOT NULL,
  `total_price` varchar(11) NOT NULL,
  `pay_price` varchar(11) NOT NULL,
  `owe_price` varchar(11) NOT NULL,
  `pay_time` datetime NOT NULL,
  PRIMARY KEY (`storage_sn`)
) DEFAULT CHARSET=utf8;

-- 付款记录表
DROP TABLE IF EXISTS `tbl_pay_record`; 
CREATE TABLE `tbl_pay_record` (
  `storage_sn` varchar(11) NOT NULL,
  `psn` int NOT NULL,
  `total_price` varchar(11) NOT NULL,
  `pay_price` varchar(11) NOT NULL,
  `owe_price` varchar(11) NOT NULL,
  `pay_time` datetime NOT NULL,
  PRIMARY KEY (`storage_sn`, `psn`)
) DEFAULT CHARSET=utf8;

-- 苹果类型表
DROP TABLE IF EXISTS `dic_apple_type`; 
CREATE TABLE `dic_apple_type` (
  `name` int(10) NOT NULL,
  `value` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) DEFAULT CHARSET=utf8;

-- 苹果尺寸表
DROP TABLE IF EXISTS `dic_apple_size`; 
CREATE TABLE `dic_apple_size` (
  `name` int(10) NOT NULL,
  `value` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) DEFAULT CHARSET=utf8;

-- 库存记录状态表
DROP TABLE IF EXISTS `dic_storage_status`; 
CREATE TABLE `dic_storage_status` (
  `name` int(10) NOT NULL,
  `value` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) DEFAULT CHARSET=utf8;

-- 果农记录状态表
DROP TABLE IF EXISTS `dic_farmer_status`; 
CREATE TABLE `dic_farmer_status` (
  `name` int(10) NOT NULL,
  `value` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) DEFAULT CHARSET=utf8;

-- 系统参数表
DROP TABLE IF EXISTS `tbl_sys_para`; 
CREATE TABLE `tbl_sys_para` (
  `name` varchar(20) NOT NULL,
  `value` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) DEFAULT CHARSET=utf8;

