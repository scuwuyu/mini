--  增加小程序数据库mini
--  用户表(包括C端和B端)
CREATE TABLE `user` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `nickName` varchar(128) DEFAULT NULL COMMENT '用户昵称',
  `open_id` varchar(32) NOT NULL COMMENT '微信openid',
  `avatar_url` varchar(256) DEFAULT NULL COMMENT '用户头像',
  `is_seller` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否开通卖家:0.否，1.是',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
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
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
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
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_number` (`order_number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='套餐资费表';

--  会员有效期
CREATE TABLE `member_date` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `start_time` TIMESTAMP NOT NULL COMMENT '开始时间',
  `end_time` TIMESTAMP NOT NULL COMMENT '结束时间',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
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
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
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
  `address` varchar(256) DEFAULT NULL COMMENT '活动详细地址',
  `activity_time` datetime DEFAULT NULL COMMENT '活动时间',
  `desc` varchar(256) DEFAULT NULL COMMENT '简介',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态：1:进行中 5：结束',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
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

  `desc` text COMMENT '商品简介',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：1:有效 0：删除',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
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

  `receiver_name` varchar(64) DEFAULT NULL COMMENT '收货人名称',
  `receiver_address` varchar(256) DEFAULT NULL COMMENT '收货人地址',
  `receiver_mobile` varchar(32) DEFAULT NULL COMMENT '收货人电话',

  `express_number` varchar(32) DEFAULT NULL COMMENT '快递单号',
  `express_name` varchar(32) DEFAULT NULL COMMENT '快递名称',

  `seller_comment` varchar(256) DEFAULT NULL COMMENT '卖家备注',
  `comment` varchar(256) DEFAULT NULL COMMENT '备注',
	`status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态：1:待发货 5：已发货',


  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_order_number` (`order_number`) USING BTREE,
  KEY `idx_activity_id` (`activity_id`) USING BTREE,
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
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
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
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址表';

--  小程序问题表
CREATE TABLE `mini_question` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `question` varchar(64) DEFAULT NULL COMMENT '问题',
  `answer` varchar(256) DEFAULT NULL COMMENT '答案',
  `type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '类型：1:卖家问题 2：买家问题',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小程序问题表';

--  建议与反馈
CREATE TABLE `advise` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `advise` varchar(256) DEFAULT NULL COMMENT '建议',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小程序问题表';



--  初始化问题列表
INSERT INTO `mini_question` (`question`, `answer`, `type`) VALUES ('代购超人是什么？', '代购助手是一款面向代购卖家的下单管理工具', 1);
INSERT INTO `mini_question` (`question`, `answer`, `type`) VALUES ('代购超人包括哪些功能？', '1、支持创建活动，比如“十一飞韩国”\r\\n2、支持活动分享给买家，让买家自助下单\\n\r3、支持订单汇总和采购清单统计，方便卖家管理和采购\\n\r4、支持订单发货功能', 1);
INSERT INTO `mini_question` (`question`, `answer`, `type`) VALUES ('代购超人能给你带来什么帮助？', '1、帮助卖家无纸化、电子化管理订单\r\\n2、大大提升下单效率，减少卖家一个个记录订单操作\\n\r3、历史订单、历史活动数据一览无余\r\\n4、系统自动帮你统计每个商品的需采购的数量，减少卖家工作\r\\n5、简易的发货和物流管理', 1);
INSERT INTO `mini_question` (`question`, `answer`, `type`) VALUES ('作为卖家，你该如何有效使用代购超人？', '1、第一步你可以先在商品库建立你能代购的商品，比如“SKII”\r\\n2、第二步当你有一个行程时，可以发布一个活动，比如“十一飞韩国“\r\\n3、第三步将你的活动分享给你的朋友、群，让他们自助下单（与买家的沟通、支付依然在微信中完成），可以点击“活动订单”查看所有买家下的订单，你也可以对订单进行备注，比如“这个订单已支付”。点击“采购清单”可以看到系统自动统计采购单\r\\n4、第四步就是设置活动结束（这样买家就无法下单了），然后十一飞到韩国进行采购，完成采购后，进行一一发货', 1);
INSERT INTO `mini_question` (`question`, `answer`, `type`) VALUES ('目前不支持什么功能？', '1、目前不支持商品关联活动，即不同的活动（比如韩国）只能下单对应活动的商品\r\\n2、目前不支持直接在小程序上直接进行支付，建议依然微信打款，可在小程序订单中进行备注\\n\r3、\r物流未对接第三方，暂未开放物流查询', 1);
INSERT INTO `mini_question` (`question`, `answer`, `type`) VALUES ('买家如何使用代购超人？', '买家可以通过卖家分享给自己的小程序卡片，进入当前活动进行下单，并在“我的—我是买家—我的订单”中查看自己下的订单，以及订单详情', 2);
INSERT INTO `mini_question` (`question`, `answer`, `type`) VALUES ('如果我下错了，或者不想要了怎么办？', '你可以在订单列表中取消订单', 2);

