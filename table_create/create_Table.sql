git /*
假设:
所有的varchar默认不会超过255个字符
长文本（比如introduction使用text）
所有的时间都是TIMESTAMP
所有的图片和视频都以BLOB类型存储
所有的分数都是小数，最多有两位小数
所有的表都没有外键
所有的表都有主键
*/




-- 用户表
CREATE TABLE User (
    user_ID INT AUTO_INCREMENT,
    name VARCHAR(255),
    phone VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    image BLOB,
    PRIMARY KEY (user_ID)
);

-- 管理员表
CREATE TABLE Admin (
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
    image BLOB
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
