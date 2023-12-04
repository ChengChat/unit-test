CREATE TABLE `students`(
    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `first_name` varchar(20)  NOT NULL DEFAULT '' COMMENT '用户名',
    `last_name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
    `email` varchar(20) NOT NULL DEFAULT '' COMMENT '邮箱',
    `create_time` DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '创建时间',
    `update_time` DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '更新时间',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;