create database `ai-invest` character set utf8mb4 COLLATE = utf8mb4_general_ci;

create table `user`
(
    `user_id`     varchar(64) primary key not null comment '主键',
    `name`        varchar(64)  default '' not null comment '用名称',
    `mobile`      varchar(12)  default '' not null comment '手机号',
    `header_img`  varchar(255) default '' not null comment '头像',
    `type`        int          default 1  not null comment '删除标志 1：注册用户   2：管理 ',
    `wx_id`       varchar(64)  default '' not null comment '微信开发平台ID',
    `create_time` datetime                not null comment '创建时间',
    `update_time` datetime     default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
    key `wx_id` (`wx_id`),
    key `mobile` (`mobile`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户表';



create table `user_invest_account`
(
    `account_id`       varchar(64) primary key     not null comment '主键',
    `user_id`          varchar(64)                 not null comment '用户id',
    `plan_amount`      decimal(15, 2) DEFAULT 0.00 not null comment '计划资金',
    `actual_amount`    decimal(15, 2) DEFAULT 0.00 not null comment '实际资金',
    `used_amount`      decimal(15, 2) DEFAULT 0.00 not null comment '已用资金',
    `frozen_amount`    decimal(15, 2) DEFAULT 0.00 not null comment '冻结资金',
    `available_amount` decimal(15, 2) DEFAULT 0.00 not null comment '可用资金',
    `create_time`      datetime                    not null comment '创建时间',
    `update_time`      datetime       default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间'

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户投资账户';


CREATE TABLE `stocks`
(
    `stock_id`    bigint AUTO_INCREMENT PRIMARY KEY COMMENT '股票ID',
    `stock_code`  varchar(20)  NOT NULL COMMENT '股票代码',
    `stock_name`  varchar(255) NOT NULL COMMENT '股票名称',
    `exchange`    varchar(50)  NOT NULL COMMENT '交易所',
    `industry`    varchar(255) COMMENT '所属行业',
    `market_cap`  decimal(20, 2) COMMENT '市值',
    `open_price`  decimal(15, 2) COMMENT '开盘价',
    `close_price` decimal(15, 2) COMMENT '收盘价',
    `high_price`  decimal(15, 2) COMMENT '最高价',
    `low_price`   decimal(15, 2) COMMENT '最低价',
    `volume`      bigint COMMENT '成交量',
    `update_time` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
    COMMENT = '股票信息表';

