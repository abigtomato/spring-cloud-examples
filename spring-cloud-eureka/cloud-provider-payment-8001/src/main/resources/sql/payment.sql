CREATE TABLE `payment` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `serial` varchar(200) DEFAULT '支付流水号',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8