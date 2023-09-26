/*
假设:
所有的varchar默认不会超过255个字符
长文本（比如introduction使用text）
所有的时间都是以24小时制表示
所有的图片和视频都以BLOB类型存储
所有的分数都是小数，最多有两位小数
所有的表都没有外键
所有的表都有主键
*/

-- 用户表
CREATE TABLE Users (
    user_ID INT AUTO_INCREMENT,
    name VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    image BLOB,
    PRIMARY KEY (user_ID)
);

-- 管理员表
CREATE TABLE Admins (
    admin_ID INT AUTO_INCREMENT,
    name VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    image BLOB,
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
CREATE TABLE Buildings (
    building_ID INT AUTO_INCREMENT,
    name VARCHAR(255),
    open_time TIME,
    close_time TIME,
    image BLOB,
    location_name VARCHAR(255),
    video BLOB,
    introduction TEXT,
    nearest_station VARCHAR(255),
    PRIMARY KEY (building_ID)
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
    image BLOB,
    capacity INT,
    PRIMARY KEY (room_type_ID)
);

-- 巴士线路表
CREATE TABLE bus_line (
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
   image BLOB,
   building_ID INT,
   score DECIMAL(3,2),
   admin_ID INT,
   PRIMARY KEY (comment_ID)
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
