# 注意
# 表注释写法：  comment ='图片模板';
create table t_image_template
(
    `id`            bigint unsigned not null auto_increment comment 'id',
    `template_name` varchar(200)    not null default '' comment '模板名称',
    `bg_image_url`  varchar(1024)   not null default '' comment '背景图片url',
    `text_cfg`      varchar(1024)   not null default '' comment '文字配置',
    `state`         tinyint(1)      not null default 0 comment '状态, 0 启用, 1 为禁用',
    `is_deleted`    tinyint(1)      not null default 0 comment '软删除, 0 为正常, 1 为已删除',
    `create_time`   datetime        not null default current_timestamp comment '创建时间',
    `update_time`   datetime        not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='图片模板';

