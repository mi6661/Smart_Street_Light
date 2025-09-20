-- 创建 `light` 表
CREATE TABLE IF NOT EXISTS `light` (
                                       `id` INT NOT NULL AUTO_INCREMENT COMMENT '路灯id',
                                       `location` VARCHAR(255) COMMENT '路灯位置',
    `status` VARCHAR(50) COMMENT '路灯状态',
    `brightness` INT COMMENT '路灯亮度（0-100）',
    `auto` VARCHAR(50) COMMENT '路灯自动状态',
    `create_time` DATETIME COMMENT '创建时间',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='路灯信息表';

-- 创建 `sensor` 表
CREATE TABLE IF NOT EXISTS `sensor` (
                                        `id` INT NOT NULL AUTO_INCREMENT COMMENT '传感器数据id',
                                        `light_id` INT NOT NULL COMMENT '路灯id',
                                        `temperature` FLOAT COMMENT '温度',
                                        `humidity` FLOAT COMMENT '湿度',
                                        `pm25` FLOAT COMMENT 'pm2.5浓度',
                                        `create_time` TIMESTAMP COMMENT '数据插入时间',
                                        PRIMARY KEY (`id`),
    FOREIGN KEY (`light_id`) REFERENCES `light`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='传感器数据表';


-- 为 `light` 表的 `location` 字段创建索引，便于快速查询
CREATE INDEX idx_light_location ON `light` (`location`);

-- 为 `sensor` 表的 `light_id` 字段创建索引，便于快速查询对应路灯的传感器数据
CREATE INDEX idx_sensor_light_id ON `sensor` (`light_id`);



--
-- 插入 `light` 表的测试数据
--

INSERT INTO `light` (`id`, `location`, `status`, `brightness`, `auto`, `create_time`) VALUES
(1, '南京西路', 'on', 80, 'on', '2025-09-19 10:00:00'),
(2, '人民广场', 'off', 0, 'off', '2025-09-19 10:05:00'),
(3, '外滩', 'on', 100, 'on', '2025-09-19 10:10:00'),
(4, '陆家嘴', 'err', 0, 'on', '2025-09-19 10:15:00'),
(5, '衡山路', 'on', 50, 'off', '2025-09-19 10:20:00');

--
-- 插入 `sensor` 表的测试数据
--

INSERT INTO `sensor` (`id`, `light_id`, `temperature`, `humidity`, `pm25`, `create_time`) VALUES
(1, 1, 25.5, 60.2, 35.1, '2025-09-19 10:00:05'),
(2, 1, 25.3, 60.5, 36.5, '2025-09-19 10:00:10'),
(3, 2, 26.1, 58.0, 42.3, '2025-09-19 10:05:05'),
(4, 3, 24.8, 65.1, 28.9, '2025-09-19 10:10:05'),
(5, 4, 25.9, 59.5, 50.1, '2025-09-19 10:15:05'),
(6, 4, 25.8, 59.3, 51.0, '2025-09-19 10:15:10'),
(7, 5, 26.5, 57.0, 31.5, '2025-09-19 10:20:05');