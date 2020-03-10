CREATE DATABASE `seara_order`;
CREATE DATABASE `seara_storage`;
CREATE DATABASE `seara_account`;

CREATE TABLE `t_order` (
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT(11) DEFAULT NULL COMMENT '用户id',
    `product_id` BIGINT(11) DEFAULT NULL COMMENT '产品id',
    `count` INT(11) DEFAULT NULL COMMENT '数量',
    `money` DECIMAL(11, 0) DEFAULT NULL COMMENT '金额',
    `status` INT(1) DEFAULT NULL COMMENT '订单状态：0创建中，1已完结'
) ENGINE = Innodb AUTO_INCREMENT = 7 DEFAULT CHARSET = utf8;

CREATE TABLE `t_storage` (
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product_id` BIGINT(11) DEFAULT NULL COMMENT '产品id',
    `total` INT(11) DEFAULT NULL COMMENT '总库存',
    `used` INT(11) DEFAULT NULL COMMENT '已用库存',
    `residue` INT(11) DEFAULT NULL COMMENT '剩余库存'
) ENGINE = Innodb AUTO_INCREMENT = 2 DEFAULT CHARSET = utf8;

CREATE TABLE `` (
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT(11) DEFAULT NULL COMMENT '用户id',
    `total` DECIMAL(10, 0) DEFAULT NULL COMMENT '总额度',
    `used` DECIMAL(10, 0) DEFAULT NULL COMMENT '已用额度',
    `residue` DECIMAL(10, 0) DEFAULT '0' COMMENT '剩余可用额度'
) ENGINE = Innodb AUTO_INCREMENT = 2 DEFAULT CHARSET = utf8;

# 订单-库存-账户3个库都需要建立各自的回滚日志表
# 执行seata/conf/db_undo_log.sql创建