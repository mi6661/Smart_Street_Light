#1.创建用户表
CREATE TABLE IF NOT EXISTS users(
  _id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(20) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  role INT NOT NULL CHECK(role IN (0,1)),
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(_id)
);

#插入用户测试数据
INSERT INTO users (username,`password`,role) VALUES('admin','admin12345',0);
INSERT INTO users (username,`password`,role) VALUES('lelecha','asd12345',1);


#2.创建路灯状态表
CREATE TABLE IF NOT EXISTS street_lights(
  _id INT NOT NULL AUTO_INCREMENT,
  location VARCHAR(255) NOT NULL,
  `status` VARCHAR(10) NOT NULL CHECK(`status` IN ('on', 'off')),
  brightness INT NOT NULL CHECK(brightness>=0 AND brightness <=100 ),
	auto VARCHAR(10) NOT NULL CHECK( auto IN ('on','off')),
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (_id)
);

#插入路灯状态测试数据
INSERT INTO street_lights (location,`status`,brightness,auto) VALUES('辽宁省沈阳市浑南区沈阳理工大学东门','on',50,'off');
INSERT INTO street_lights (location,`status`,brightness,auto) VALUES('辽宁省沈阳市浑南区沈阳理工大学综合楼A','off',99,'on');


#3.创建传感器数据
CREATE TABLE sensor_data(
  _id INT NOT NULL AUTO_INCREMENT,
  light_id INT NOT NULL,
  temperature FLOAT NOT NULL,
  humidity FLOAT NOT NULL,
  pm24 FLOAT NOT NULL,
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(_id)
);
#插入测试数据
INSERT INTO sensor_data(light_id,temperature,humidity,pm24) VALUES(1,23.4,56.0,12.1);
INSERT INTO sensor_data(light_id,temperature,humidity,pm24) VALUES(2,22.1,45.2,23.3);
 
#4.创建控制记录表
CREATE TABLE control_logs(
  _id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  light_id INT NOT NULL,
  action INT NOT NULL CHECK(action IN(1,2,0)),
  `value` INT NOT NULL CHECK(`value`>=0 AND `value`<=100),
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(_id)
);
#插入测试数据
INSERT INTO control_logs(user_id,light_id,action,`value`) VALUES(0,1,1,55);
INSERT INTO control_logs(user_id,light_id,action,`value`) VALUES(1,12,0,100);