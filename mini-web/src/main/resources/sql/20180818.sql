--  股票查询数据库

--  ipo股票
CREATE TABLE `stock_ipo` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` char(6) NOT NULL COMMENT '股票代码',
  `name` varchar(8) NOT NULL COMMENT '股票名称',
  `total_initial_issue` varchar(16) DEFAULT NULL COMMENT '初始发行总量（万股）',
  `online_issuance_date` varchar(16) NOT NULL COMMENT '网上发行日',
  `issue_price` varchar(8) DEFAULT NULL COMMENT '发行价(元)',
  `announce_success_rate_result_date` varchar(16) NOT NULL COMMENT '中签结果公告日',
  `listed_date` varchar(16) NOT NULL COMMENT '上市日',

  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ipo股票';


