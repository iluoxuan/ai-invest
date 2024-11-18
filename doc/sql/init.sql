create database `ai-invest` character set utf8mb4 COLLATE = utf8mb4_general_ci;

create table `user`
(
    `user_id`     varchar(64)             not null comment '主键',
    `name`        varchar(64)  default '' not null comment '用名称',
    `mobile`      varchar(12)  default '' not null comment '手机号',
    `header_img`  varchar(255) default '' not null comment '头像',
    `type`        int          default 1  not null comment '删除标志 1：注册用户   2：管理 ',
    `wx_id`       varchar(64)  default '' not null comment '微信开发平台ID',
    `create_time` datetime                not null comment '创建时间',
    `update_time` datetime     default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (`user_id`),
    key `wx_id` (`wx_id`),
    key `mobile` (`mobile`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户表';



create table `user_invest_account`
(
    `account_id`       varchar(64)                 not null comment '主键',
    `user_id`          varchar(64)                 not null comment '用户id',
    `plan_amount`      decimal(15, 2) DEFAULT 0.00 not null comment '计划资金',
    `actual_amount`    decimal(15, 2) DEFAULT 0.00 not null comment '实际资金',
    `used_amount`      decimal(15, 2) DEFAULT 0.00 not null comment '已用资金',
    `frozen_amount`    decimal(15, 2) DEFAULT 0.00 not null comment '冻结资金',
    `available_amount` decimal(15, 2) DEFAULT 0.00 not null comment '可用资金',
    `create_time`      datetime                    not null comment '创建时间',
    `update_time`      datetime       default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key (account_id),
    key `user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户投资账户';


CREATE TABLE `stock`
(
    `stock_id`    bigint auto_increment comment '股票ID',
    `stock_code`  varchar(20)  not null comment '股票代码',
    `stock_name`  varchar(128) not null comment '股票名称',
    `exchange`    varchar(50)  not null comment '交易所',
    `industry`    varchar(255) comment '所属行业',
    `market_cap`  decimal(20, 2) comment '市值',
    `open_price`  decimal(15, 2) comment '开盘价',
    `close_price` decimal(15, 2) comment '收盘价',
    `high_price`  decimal(15, 2) comment '最高价',
    `low_price`   decimal(15, 2) comment '最低价',
    `volume`      bigint comment '成交量',
    `update_time` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '修改时间',
    PRIMARY KEY (stock_id),
    key `stock_code` (`stock_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
    COMMENT = '股票信息表';

create table tbl_stock_kline
(
    `id`             bigint auto_increment comment '自增主键',
    `stock_code`     varchar(20)  not null comment '股票代码',
    `stock_name`     varchar(128) not null comment '股票名称',
    `trading_date`   date         not null comment '交易日期（yyyy-MM-dd）',
    `opening_price`  decimal(15, 2) comment '开盘价格（单位：元）',
    `closing_price`  decimal(15, 2) comment '收盘价格（单位：元）',
    `peak_price`     decimal(15, 2) comment '当天最高价格（单位：元）',
    `bottom_price`   decimal(15, 2) comment '当天最底价格（单位：元）',
    `change_rate`    decimal(8, 2) comment '涨跌幅%',
    `change_amount`  decimal(8, 2) comment '涨跌额（单位：元）',
    `trading_volume` int(11) comment '成交量（单位：手）',
    `trading_amount` decimal(16, 2) comment '成交额（单位：元）',
    `amplitude_rate` decimal(8, 2) comment '振幅%',
    `turnover_rate`  decimal(8, 2) comment '换手率%',
    `create_time`    datetime     not null comment '创建时间',
    `update_time`    datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id),
    key `stock_code` (`stock_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
    COMMENT = '股票K线数据';