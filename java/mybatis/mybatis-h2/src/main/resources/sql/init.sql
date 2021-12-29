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
