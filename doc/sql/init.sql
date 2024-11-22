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
    `id`           BIGINT UNSIGNED AUTO_INCREMENT COMMENT '自增主键',
    `ts_code`      VARCHAR(16)  NOT NULL COMMENT '股票的TS代码，用于唯一标识一只股票',
    `symbol`       VARCHAR(16)  NOT NULL COMMENT '股票代码，通常由数字组成',
    `name`         VARCHAR(64)  NOT NULL COMMENT '股票名称，用于标识股票的中文名',
    `area`         VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '地域信息，如省份或城市',
    `industry`     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '所属行业，用于分类股票',
    `full_name`    VARCHAR(125) NOT NULL DEFAULT '' COMMENT '股票的完整名称，可能包括公司名称等详细信息',
    `en_name`      VARCHAR(125) NOT NULL DEFAULT '' COMMENT '英文全称，国际投资者参考',
    `cn_spell`     VARCHAR(16)  NOT NULL DEFAULT '' COMMENT '股票的拼音缩写，便于快速搜索',
    `market`       VARCHAR(255) NOT NULL DEFAULT '' COMMENT '市场类型，如主板、创业板等',
    `exchange`     VARCHAR(16)  NOT NULL DEFAULT '' COMMENT '交易所代码，如SZSE表示深圳证券交易所',
    `curr_type`    VARCHAR(10)  NOT NULL DEFAULT '' COMMENT '交易货币，如CNY表示人民币',
    `list_status`  VARCHAR(12)  NOT NULL DEFAULT '' COMMENT '上市状态，L表示已上市，D表示已退市，P表示暂停上市',
    `list_date`    DATE COMMENT '上市日期，格式为YYYY-MM-DD',
    `de_list_date` DATE COMMENT '退市日期，如果有，格式为YYYY-MM-DD',
    `is_hs`        CHAR(1)      NOT NULL DEFAULT '' COMMENT '是否沪深港通标的，N表示不是，H表示沪股通，S表示深股通',
    `act_name`     VARCHAR(255) NOT NULL DEFAULT '' COMMENT '实际控制人名称，即拥有实际控制权的个人或企业名称',
    `act_ent_type` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '实际控制人的企业性质，如国有企业、民营企业等',
    `create_time`  DATETIME     NOT NULL COMMENT '创建时间',
    `update_time`  DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tsCode` (`ts_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '股票信息表';

CREATE TABLE `stock_daily_line`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT COMMENT '主键',
    `ts_code`     VARCHAR(16)    NOT NULL COMMENT '股票代码',
    `trade_date`  DATE           NOT NULL COMMENT '交易日期',
    `open`        DECIMAL(18, 2) NOT NULL COMMENT '开盘价',
    `high`        DECIMAL(18, 2) NOT NULL COMMENT '最高价',
    `low`         DECIMAL(18, 2) NOT NULL COMMENT '最低价',
    `close`       DECIMAL(18, 2) NOT NULL COMMENT '收盘价',
    `pre_close`   DECIMAL(18, 2) COMMENT '昨收价【除权价，前复权】',
    `change`      DECIMAL(18, 2) COMMENT '涨跌额',
    `pct_chg`     DECIMAL(18, 2) COMMENT '涨跌幅 【基于除权后的昨收计算的涨跌幅：（今收-除权昨收）/除权昨收 】',
    `vol`         DECIMAL(18, 2) COMMENT '成交量 （手）',
    `amount`      DECIMAL(18, 2) COMMENT '成交额 （千元）',
    `create_time` DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tsCode` (`ts_code`, `trade_date`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '股票日线行情表';

CREATE TABLE `stock_weekly_line`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT COMMENT '主键',
    `ts_code`     VARCHAR(16)    NOT NULL COMMENT '股票代码',
    `trade_date`  DATE           NOT NULL COMMENT '交易日期',
    `open`        DECIMAL(18, 2) NOT NULL COMMENT '开盘价',
    `high`        DECIMAL(18, 2) NOT NULL COMMENT '最高价',
    `low`         DECIMAL(18, 2) NOT NULL COMMENT '最低价',
    `close`       DECIMAL(18, 2) NOT NULL COMMENT '收盘价',
    `pre_close`   DECIMAL(18, 2) COMMENT '昨收价【除权价，前复权】',
    `change`      DECIMAL(18, 2) COMMENT '涨跌额',
    `pct_chg`     DECIMAL(18, 2) COMMENT '涨跌幅 【基于除权后的昨收计算的涨跌幅：（今收-除权昨收）/除权昨收 】',
    `vol`         DECIMAL(18, 2) COMMENT '成交量 （手）',
    `amount`      DECIMAL(18, 2) COMMENT '成交额 （千元）',
    `create_time` DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_ts_code` (`ts_code`),
    KEY `idx_trade_date` (`trade_date`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '股票周线行情表';


CREATE TABLE `stock_monthly_line`
(
    `id`          BIGINT UNSIGNED PRIMARY KEY COMMENT '主键',
    `ts_code`     VARCHAR(16)    NOT NULL COMMENT '股票代码',
    `trade_date`  DATE           NOT NULL COMMENT '交易日期',
    `open`        DECIMAL(18, 2) NOT NULL COMMENT '开盘价',
    `high`        DECIMAL(18, 2) NOT NULL COMMENT '最高价',
    `low`         DECIMAL(18, 2) NOT NULL COMMENT '最低价',
    `close`       DECIMAL(18, 2) NOT NULL COMMENT '收盘价',
    `pre_close`   DECIMAL(18, 2) COMMENT '昨收价【除权价，前复权】',
    `change`      DECIMAL(18, 2) COMMENT '涨跌额',
    `pct_chg`     DECIMAL(18, 2) COMMENT '涨跌幅 【基于除权后的昨收计算的涨跌幅：（今收-除权昨收）/除权昨收 】',
    `vol`         DECIMAL(18, 2) COMMENT '成交量 （手）',
    `amount`      DECIMAL(18, 2) COMMENT '成交额 （千元）',
    `create_time` DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_ts_code` (`ts_code`),
    KEY `idx_trade_date` (`trade_date`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '股票月线行情表';

CREATE TABLE `stock_daily_basic`
(
    `id`              BIGINT UNSIGNED AUTO_INCREMENT COMMENT '主键',
    `ts_code`         VARCHAR(16)    NOT NULL COMMENT '股票代码',
    `trade_date`      DATE           NOT NULL COMMENT '交易日期',
    `open`            DECIMAL(18, 2) NOT NULL COMMENT '开盘价',
    `high`            DECIMAL(18, 2) NOT NULL COMMENT '最高价',
    `low`             DECIMAL(18, 2) NOT NULL COMMENT '最低价',
    `close`           DECIMAL(18, 2) NOT NULL COMMENT '收盘价',
    `pre_close`       DECIMAL(18, 2) COMMENT '昨收价【除权价，前复权】',
    `change`          DECIMAL(18, 2) COMMENT '涨跌额',
    `pct_chg`         DECIMAL(18, 2) COMMENT '涨跌幅 【基于除权后的昨收计算的涨跌幅：（今收-除权昨收）/除权昨收 】',
    `vol`             DECIMAL(18, 2) COMMENT '成交量 （手）',
    `amount`          DECIMAL(18, 2) COMMENT '成交额 （千元）',
    `turnover_rate`   DECIMAL(18, 2) COMMENT '换手率（%）',
    `turnover_rate_f` DECIMAL(18, 2) COMMENT '换手率（自由流通股）',
    `volume_ratio`    DECIMAL(18, 2) COMMENT '量比',
    `pe`              DECIMAL(18, 2) COMMENT '市盈率（总市值/净利润， 亏损的PE为空）',
    `pe_ttm`          DECIMAL(18, 2) COMMENT '市盈率（TTM，亏损的PE为空）',
    `pb`              DECIMAL(18, 2) COMMENT '市净率（总市值/净资产）',
    `ps`              DECIMAL(18, 2) COMMENT '市销率',
    `ps_ttm`          DECIMAL(18, 2) COMMENT '市销率（TTM）',
    `dv_ratio`        DECIMAL(18, 2) COMMENT '股息率 （%）',
    `dv_ttm`          DECIMAL(18, 2) COMMENT '股息率（TTM）（%）',
    `total_share`     DECIMAL(18, 2) COMMENT '总股本 （万股）',
    `float_share`     DECIMAL(18, 2) COMMENT '流通股本 （万股）',
    `free_share`      DECIMAL(18, 2) COMMENT '自由流通股本 （万）',
    `total_mv`        DECIMAL(18, 2) COMMENT '总市值 （万元）',
    `circ_mv`         DECIMAL(18, 2) COMMENT '流通市值（万元）',
    `create_time`     DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_ts_code` (`ts_code`),
    KEY `idx_trade_date` (`trade_date`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '股票每日指标';