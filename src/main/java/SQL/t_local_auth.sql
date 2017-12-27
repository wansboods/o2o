SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_area`
-- ----------------------------

DROP TABLE IF EXISTS `t_local_auth_info`;
CREATE TABLE `t_local_auth_info` (
  `local_auth_id` int(10) NOT NULL auto_increment,
  `user_id` int(10) NOT NULL COMMENT '用户表ID',
  `username` varchar(128) NOT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `create_time` DATETIME default NULL default '0' COMMENT '创建时间',
  `last_edit_time` DATETIME default NULL COMMENT '最后编辑时间',
  PRIMARY KEY  (`local_auth_id`),
  UNIQUE KEY `uk_local_profile`( `username` ),
  CONSTRAINT `fk_localauth` FOREIGN KEY (`user_id`) REFERENCES `t_person_info`(`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='本地表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
# INSERT INTO `t_user` VALUES ('1', '赵大宝', '13285250574', '1045221654@qq.com', '05126a423a9379d529e4ee61a212fa55', 'KJUYT5', '2016-07-15 23:38:56', '2016-07-15 23:39:09', '0');
# INSERT INTO `t_user` VALUES ('2', '张三丰', '15985250574', '1198224554@qq.com', '98bd3a1bebde01ad363d3c5a0d1e56da', '656JHU', '2016-07-15 23:39:01', '2016-07-15 23:39:13', '0');
# INSERT INTO `t_user` VALUES ('3', '王尼玛', '13685250574', '1256221654@qq.com', '5470db9b63c354f6c8d628b80ae2f3c3', '89UIKQ', '2016-07-15 23:39:05', '2016-07-15 23:39:16', '0');
