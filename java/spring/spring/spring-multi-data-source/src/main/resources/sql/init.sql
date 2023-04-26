-- ----------------------------
-- Table structure for user_role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `user_role`
(
    `id`           varchar(128) NOT NULL COMMENT 'primary key id',
    `user_id`      varchar(128) NOT NULL COMMENT 'user primary key',
    `role_id`      varchar(128) NOT NULL COMMENT 'role primary key',
    `date_created` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
    `date_updated` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
    PRIMARY KEY (`id`)
);;
-- ----------------------------
-- Table structure for user_role
-- ----------------------------
CREATE TABLE IF NOT EXISTS `xm_shopping_order`
(
    `order_id`            varchar(128) NOT NULL COMMENT 'user order_id key',
    `consumer_id`         varchar(128) NOT NULL COMMENT 'consumer_id',
    `shipping_address_id` varchar(128) NOT NULL COMMENT 'shipping_address_id',
    `consumer_phone`      varchar(128) NOT NULL COMMENT 'consumer_phone',
    `address_snapshot`    varchar(128) NOT NULL COMMENT 'address_snapshot',
    `order_status`        varchar(128) NOT NULL COMMENT 'order_status',
    `create_time`         timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
    `update_time`         timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
    PRIMARY KEY (`order_id`)
);;
