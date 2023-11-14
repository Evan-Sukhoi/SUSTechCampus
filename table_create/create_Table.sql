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

<<<<<<< HEAD


=======
-- drop all tables
# DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Admins;
DROP TABLE IF EXISTS Blacklist;
DROP TABLE IF EXISTS Building;
DROP TABLE IF EXISTS Room;
DROP TABLE IF EXISTS Room_type;
DROP TABLE IF EXISTS bus_line;
DROP TABLE IF EXISTS Comment;
DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Users;
>>>>>>> e95f2e15790f5139a38a1e7219c4da6a3393741f

-- 用户表
CREATE TABLE user
(
    user_ID INT AUTO_INCREMENT,
    phone VARCHAR(255),
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    image BLOB,
    PRIMARY KEY (user_ID)
);

-- 管理员表
CREATE TABLE admin (
                       admin_ID INT AUTO_INCREMENT,
                       name VARCHAR(255),
                       phone VARCHAR(255),
                       email VARCHAR(255),
                       password VARCHAR(255),
                       image BLOB,
                       PRIMARY KEY (admin_ID)
);

-- 黑名单表
CREATE TABLE blacklist (
                           list_ID INT AUTO_INCREMENT,
                           user_ID INT,
                           admin_ID INT,
                           PRIMARY KEY (list_ID)
);

-- 建筑表
CREATE TABLE building (
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

-- 房间表
CREATE TABLE room (
                      room_ID INT AUTO_INCREMENT,
                      building_ID INT,
                      number INT,
                      room_type_ID INT,
                      PRIMARY KEY (room_ID)
);

-- 房间类型表
CREATE TABLE room_type (
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
CREATE TABLE comment (
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
CREATE TABLE reservation (
                             Reservation_ID INT AUTO_INCREMENT,
                             room_ID INT,
                             start_time TIMESTAMP,
                             end_time TIMESTAMP,
                             user_ID INT,
                             PRIMARY KEY (Reservation_ID)
);


insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID) values ('琳恩图书馆', '08:00:00', '22:00:00', '琳恩图书馆', '琳恩图书馆是一个图书馆', '行政楼', 'https://www.bilibili.com/video/BV1Y7411H7jZ', 1);
insert into Room (building_ID, number, room_type_ID) values (1, 101, 1);
insert into Room (building_ID, number, room_type_ID) values (1, 102, 1);
insert into Room (building_ID, number, room_type_ID) values (1, 103, 1);


insert into User(phone, name, email, password) values ('15138688888', 'evan', 'evan3li@outlook.com', '123456');
insert into User(phone, name, email, password) values ('16138688888', 'ln', 'example@gmail.com', '123456');
insert into User(phone, name, email, password) values ('17138688888', 'jimmy', '12112517@mail.sustech.edu.cn', '123456');
insert into User(phone, name, email, password) values ('18138688888', 'juan', '111@abc.com', '123456');

insert into Admin(name, phone, email, password) values ('admin', '15138688888', 'evan3li@outlook.com', '123456');