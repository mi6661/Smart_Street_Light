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

