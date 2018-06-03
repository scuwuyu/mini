--  增加小程序数据库mini
--  用户表(包括C端和B端)
CREATE TABLE `user` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `nickname` varchar(64) NOT NULL COMMENT '用户昵称',
  `open_id` varchar(32) NOT NULL COMMENT '微信openid',
  `picture` varchar(128) DEFAULT NULL COMMENT '用户头像',
  `is_seller` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否开通卖家:0.否，1.是',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表(包括C端和B端)';

--  会员资费类型表
CREATE TABLE `member_type` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) NOT NULL COMMENT '套餐名称',
  `months` INT(11) NOT NULL COMMENT '有效月数',
  `price` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '套餐价格，单位元',
  `condition` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否有限制条件:0.否，1.仅限新用户',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效 1:是 0:否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员资费类型表';

--  会员充值流水表
CREATE TABLE `member_transaction` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_number` varchar(32) NOT NULL COMMENT '充值订单号',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `member_type_id` BIGINT(20) NOT NULL COMMENT '会员类型id',
  `amount` DECIMAL(10,2) NOT NULL COMMENT '金额，单位元',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态：0:创建 1:成功 2：失败 10：删除',
  `transaction_id` BIGINT(20) DEFAULT NULL COMMENT '流水主表id',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_number` (`order_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='套餐资费表';

--  流水表主表
CREATE TABLE `transaction` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_number` varchar(32) NOT NULL COMMENT '充值订单号',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `type` tinyint(2) NOT NULL COMMENT '充值类型：1:会员充值 ',

  `open_id` varchar(32) NOT NULL COMMENT '微信openid',
  `amount` DECIMAL(10,2) NOT NULL COMMENT '金额，单位元',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态：0:创建 1:成功 2：失败 10：删除',
  `comment` varchar(2000) DEFAULT NULL COMMENT '支付信息备注',


  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_number` (`order_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流水表主表';

--  活动表
CREATE TABLE `activity` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `name` varchar(64) NOT NULL COMMENT '活动名称',
  `picture` varchar(128) DEFAULT NULL COMMENT '活动图片',

  `longitude` DOUBLE DEFAULT NULL COMMENT '活动经度',
  `latitude` DOUBLE DEFAULT NULL COMMENT '活动纬度',
  `activity_time` datetime DEFAULT NULL COMMENT '活动时间',
  `desc` varchar(256) DEFAULT NULL COMMENT '简介',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态：1:进行中 5：结束',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动表';

--  商品表
CREATE TABLE `goods` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `name` varchar(64) NOT NULL COMMENT '商品名称',
  `picture` varchar(128) DEFAULT NULL COMMENT '商品图片',
  `price` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '商品价格，单位元',

  `desc` varchar(256) DEFAULT NULL COMMENT '简介',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：1:有效 0：删除',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

--  订单表
CREATE TABLE `order` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_number` varchar(32) NOT NULL COMMENT '订单编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `seller_id` varchar(32) NOT NULL COMMENT '卖家用户id',
  `activity_id` BIGINT(20) unsigned NOT NULL DEFAULT '0' COMMENT '活动id',

  `address_id` BIGINT(20) unsigned NOT NULL COMMENT '收货地址id',
  `express_number` varchar(32) DEFAULT NULL COMMENT '快递单号',
  `express_name` varchar(32) DEFAULT NULL COMMENT '快递名称',

  `comment` varchar(256) DEFAULT NULL COMMENT '备注',
	`status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态：1:待发货 5：已发货',


  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_number` (`order_number`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

--  订单商品表
CREATE TABLE `order_item` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_number` varchar(32) NOT NULL COMMENT '订单编号',
  `goods_id` BIGINT(20) unsigned NOT NULL COMMENT '活动id',
  `goods_name` varchar(64) NOT NULL COMMENT '商品名称',
  `quantity` INT(11) NOT NULL COMMENT '商品数量',
  `price` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '商品单价，单位元',
  `total_price` DECIMAL(10,2) NOT NULL DEFAULT '0' COMMENT '商品总价，单位元',

  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：1:有效 0：无效',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_number` (`order_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表';

--  收货地址表
CREATE TABLE `address` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `name` varchar(64) NOT NULL COMMENT '收货人名称',
  `address` varchar(256) NOT NULL COMMENT '收货人地址',
  `mobile` varchar(32) NOT NULL COMMENT '收货人电话',

  `comment` varchar(256) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：1:有效 0：无效',
  `default_address` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认地址：1:是 0：否',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址表';


--  问题：
--  	1、开启专属代购套餐全部月为单位。
--    2、活动列表，活动地址显示什么？


