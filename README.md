## 待改进
1. 增加实体仓库库存
```sql
/*
Navicat MySQL Data Transfer

Source Server         : tencent
Source Server Version : 50718
Source Host           : cdb-l4dg6xvy.cd.tencentcdb.com:10001
Source Database       : logisticssystems

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-12-21 20:10:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `warehouse_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_count` int(255) unsigned NOT NULL COMMENT '下单量',
  `out_count` int(255) unsigned NOT NULL COMMENT '出库量',
  `collect_count` int(255) unsigned NOT NULL COMMENT '揽收量',
  `appropriate_count` int(255) unsigned NOT NULL COMMENT '妥投量',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `warehouse_name` varchar(255) NOT NULL,
  PRIMARY KEY (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

```
