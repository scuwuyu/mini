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


