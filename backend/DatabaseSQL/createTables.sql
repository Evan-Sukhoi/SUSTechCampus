SELECT CONCAT('DROP TABLE IF EXISTS `', table_name, '`;') AS statement
FROM information_schema.tables
WHERE table_schema = 'ooad';

DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS `blacklist`;
DROP TABLE IF EXISTS `building`;
DROP TABLE IF EXISTS `bus_line`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `reservation`;
DROP TABLE IF EXISTS `room`;
DROP TABLE IF EXISTS `room_type`;
DROP TABLE IF EXISTS `test`;
DROP TABLE IF EXISTS `user`;


-- 用户表
CREATE TABLE User (
                      user_ID INT AUTO_INCREMENT,
                      name VARCHAR(255),
                      phone VARCHAR(255),
                      email VARCHAR(255),
                      password VARCHAR(255),
                      image_ID INT,
                      PRIMARY KEY (user_ID)
);

-- 管理员表
CREATE TABLE Admin (
                       admin_ID INT AUTO_INCREMENT,
                       name VARCHAR(255),
                       phone VARCHAR(255),
                       email VARCHAR(255),
                       password VARCHAR(255),
                       image_ID INT,
                       PRIMARY KEY (admin_ID)
);

-- 黑名单表
CREATE TABLE Blacklist (
                           list_ID INT AUTO_INCREMENT,
                           user_ID INT,
                           admin_ID INT,
                           PRIMARY KEY (list_ID)
);

-- 建筑表
CREATE TABLE Building (
                          building_ID INT AUTO_INCREMENT,
                          name VARCHAR(255),
                          open_time TIME,
                          close_time TIME,
                          location_name VARCHAR(255),
                          introduction TEXT,
                          nearest_station VARCHAR(255),
                          video_url VARCHAR(255),
                          cover_ID INT,
                          PRIMARY KEY (building_ID)
);

-- 建筑对应图片 一对多需要新建表
CREATE TABLE Buildings_Image (
                                 building_ID INT,
                                 image_ID INT
);

-- 房间表
CREATE TABLE Room (
                      room_ID INT AUTO_INCREMENT,
                      building_ID INT,
                      number INT,
                      room_type_ID INT,
                      PRIMARY KEY (room_ID)
);

-- 房间类型表
CREATE TABLE Room_type (
                           room_type_ID INT AUTO_INCREMENT,
                           type VARCHAR(255),
                           capacity INT,
                           PRIMARY KEY (room_type_ID)
);
-- Room_type对应图片 一对多需要新建表
CREATE TABLE Room_type_Image (
                                 room_type_ID INT,
                                 image_ID INT
);


-- 巴士线路表
CREATE TABLE Bus_line (
                          bus_line_ID INT AUTO_INCREMENT,
                          line_ID INT,
                          station VARCHAR(255),
                          _index_ INT,
                          PRIMARY KEY (bus_line_ID)
);



-- 评价表
CREATE TABLE Comment (
                         comment_ID INT AUTO_INCREMENT,
                         user_ID INT,
                         time TIMESTAMP,
                         text TEXT,
                         building_ID INT,
                         score DECIMAL(3,2),
                         admin_ID INT,
                         PRIMARY KEY (comment_ID)
);
-- comment_ID对应图片 一对多需要新建表
CREATE TABLE Comment_ID_Image (
                                  comment_ID INT,
                                  image_ID INT
);

-- 创建图片表
CREATE TABLE Image (
                       image_ID INT AUTO_INCREMENT PRIMARY KEY,
                       image_url VARCHAR(255)
);

-- 预订表
CREATE TABLE Reservation (
                             Reservation_ID INT AUTO_INCREMENT,
                             room_ID INT,
                             start_time TIMESTAMP,
                             end_time TIMESTAMP,
                             user_ID INT,
                             PRIMARY KEY (Reservation_ID)
);


insert into User(phone, name, email, password, image_ID) values ('15138688888', 'evan', 'evan3li@outlook.com', '123456', 1);
insert into User(phone, name, email, password, image_ID) values ('16138688888', 'ln', 'example@gmail.com', '123456', 2);
insert into User(phone, name, email, password, image_ID) values ('17138688888', 'jimmy', '12112517@mail.sustech.edu.cn', '123456', 3);
insert into User(phone, name, email, password, image_ID) values ('18138688888', 'juan', '111@abc.com', '123456',4);