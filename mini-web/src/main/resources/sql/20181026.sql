--  app_token
CREATE TABLE `app_token` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` varchar(64) NOT NULL COMMENT 'app_id',
  `secret` varchar(64) NOT NULL COMMENT 'secret',
  `access_token` varchar(256) DEFAULT NULL COMMENT '获取到的凭证',

  `expired_time` datetime DEFAULT null COMMENT 'token过期时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app_token';

--  stock_code
CREATE TABLE `stock_code` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(16) NOT NULL COMMENT 'code',
  `active` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否有效',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='stock_code';

--  dictionary
CREATE TABLE `dictionary` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT 'code',
  `value` varchar(64) NOT NULL COMMENT 'code',
  `active` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否有效',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='dictionary';




