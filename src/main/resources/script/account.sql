CREATE TABLE `account` (
  `id` bigint unsigned NOT NULL,
  `age` int unsigned DEFAULT '0' COMMENT '年龄  0',
  `gender` int unsigned NOT NULL COMMENT '性别，默认 Default 0；, 男 Male 1；女 Female 2；未知 Unknown 99")',
  `nickname` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `real_name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实姓名',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户头像的 Url 地址',
  `description` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_status` int DEFAULT '1' COMMENT '1 正常，-1 注销',
  `operator_id` bigint NOT NULL COMMENT '操作人id',
  `create_time` bigint NOT NULL COMMENT '创建时间 13位时间戳',
  `update_time` bigint NOT NULL COMMENT '更新时间 13位时间戳',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_nickname` (`nickname`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;