SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_area`
-- ----------------------------

DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `shop_id` INT(10) NOT NULL auto_increment,
  `owner_id`INT(10) NOT NULL COMMENT '店铺创建人',
  `area_id` INT(5) DEFAULT NULL COMMENT '区域ID',
  `shop_category_id` INT(11) DEFAULT NULL COMMENT '店铺类型ID',
  `shop_name` VARCHAR( 128 ) DEFAULT NULL COMMENT '店铺名称',
  `shop_desc` VARCHAR( 128 ) DEFAULT NULL COMMENT '店铺描述',
  `shop_addr` VARCHAR( 256 ) DEFAULT NULL COMMENT '店铺地址',
  `shop_img` VARCHAR( 1024 ) DEFAULT NULL COMMENT '店铺图片',
  `advice` VARCHAR( 1024 ) DEFAULT NULL COMMENT '通知',
  `phone` VARCHAR( 12 ) DEFAULT NULL COMMENT '联系电话',
  `priority` int(3) DEFAULT '0' COMMENT '权重',
  `enable_status` int(2) NOT NULL DEFAULT '0' COMMENT '是否有效, 0 禁止使用本商城',
  `create_time` DATETIME default NULL default '0' COMMENT '创建时间',
  `last_edit_time` DATETIME default NULL COMMENT '最后编辑时间',
  PRIMARY KEY  (`shop_id`),
  CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `t_area`(`area_id`),
  CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `t_person_info`(`user_id`),
  CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shop_category_id`) REFERENCES `t_shop_category`(`shop_category_id`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='商店信息';

-- ----------------------------
-- Records of t_user
-- ----------------------------
# INSERT INTO `t_user` VALUES ('1', '赵大宝', '13285250574', '1045221654@qq.com', '05126a423a9379d529e4ee61a212fa55', 'KJUYT5', '2016-07-15 23:38:56', '2016-07-15 23:39:09', '0');
# INSERT INTO `t_user` VALUES ('2', '张三丰', '15985250574', '1198224554@qq.com', '98bd3a1bebde01ad363d3c5a0d1e56da', '656JHU', '2016-07-15 23:39:01', '2016-07-15 23:39:13', '0');
# INSERT INTO `t_user` VALUES ('3', '王尼玛', '13685250574', '1256221654@qq.com', '5470db9b63c354f6c8d628b80ae2f3c3', '89UIKQ', '2016-07-15 23:39:05', '2016-07-15 23:39:16', '0');
