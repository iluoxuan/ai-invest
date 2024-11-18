create table `biz_type`
(
    `id`          varchar(64)            not null comment '主键',
    `biz_type`    varchar(255)           not null comment '义务类型',
    `del_flag`    int         default 0  not null comment '删除标志 0：正常 99：删除',
    `create_by`   varchar(64) default '' not null comment '创建人',
    `update_by`   varchar(64) default '' not null comment '修改人',
    `create_time` datetime               not null comment '创建时间',
    `update_time` datetime    default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
    unique key `uk_bizType` (`biz_type`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='义务类型表';
