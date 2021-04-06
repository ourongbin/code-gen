# 注意
# 表注释写法：  comment ='图片模板';
CREATE TABLE `goods_comments`
(
    `uuid`             char(32)         NOT NULL COMMENT 'uuid',
    `timeStamp`        datetime                  DEFAULT NULL COMMENT '时间戳',
    `creator`          varchar(60)               DEFAULT NULL COMMENT '创建人（对应custID）',
    `createTime`       datetime                  DEFAULT NULL COMMENT '创建时间',
    `commentsID`       int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论ID',
    `bill_no`          varchar(40)               DEFAULT NULL COMMENT '订单号',
    `code`             tinyint(4)                DEFAULT NULL COMMENT '订单排序号',
    `product_code`     varchar(60)      NOT NULL COMMENT '商品编号（二级）',
    `postID`           bigint(20)                DEFAULT NULL COMMENT '商品postID（三级）',
    `goodsName`        varchar(200)              DEFAULT NULL COMMENT '商品名称',
    `pcustID`          int(11)                   DEFAULT NULL COMMENT '会员编号',
    `comments_content` varchar(300)              DEFAULT NULL COMMENT '评价内容',
    `comments_point`   tinyint(2)                DEFAULT NULL COMMENT '商品评分',
    `comments_tag`     varchar(20)               DEFAULT NULL COMMENT '评论标签',
    `comments_status`  tinyint(1)       NOT NULL DEFAULT '0' COMMENT '审核状态（0未审核,1已审核,2已拒绝）',
    `replay_content`   varchar(200)              DEFAULT NULL COMMENT '回复内容',
    `replay_status`    tinyint(1)                DEFAULT NULL COMMENT '回复状态 0：未回复 1：已回复',
    `display_flg`      tinyint(1)                DEFAULT NULL COMMENT '显示标志 0:针对此用户显示 1:对所有用户显示',
    `recommendFlg`     tinyint(1)                DEFAULT NULL COMMENT '首页推荐标志 0:不推荐 1:推荐',
    `goods_spec`       varchar(100)              DEFAULT NULL COMMENT '商品规格',
    `add_status`       tinyint(1)                DEFAULT '0' COMMENT '是否有追评：0没追评，1有追评',
    `pic_status`       tinyint(1)                DEFAULT NULL COMMENT '是否有图片：0无图片，1有图片',
    `product_mainID`   varchar(60)               DEFAULT NULL COMMENT '商品主编码(一级)',
    `addPFlg`          tinyint(1)                DEFAULT '0' COMMENT '是否已加积分（0导入或系统自动评论；1未送积分；2已送积分；3不符合送分（已超过每月100分））',
    `replay_time`      datetime                  DEFAULT NULL COMMENT '回复时间',
    `rateNum`          smallint(6) unsigned      DEFAULT '0' COMMENT '二次评论数量',
    `zanNum`           smallint(6) unsigned      DEFAULT '0' COMMENT '评论点击数量',
    PRIMARY KEY (`commentsID`),
    UNIQUE KEY `ix_1` (`uuid`),
    KEY `ix_2` (`product_code`),
    KEY `ix_3` (`bill_no`),
    KEY `ix_4` (`postID`),
    KEY `ix_5` (`code`),
    KEY `ix_6` (`createTime`),
    KEY `ix_7` (`pcustID`, `bill_no`, `code`, `postID`),
    KEY `ix_8` (`product_mainID`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4691931
  DEFAULT CHARSET = utf8 COMMENT ='商品评论'