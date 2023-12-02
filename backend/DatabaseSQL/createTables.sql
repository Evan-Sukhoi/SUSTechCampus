SELECT CONCAT('DROP TABLE IF EXISTS `', table_name, '`;') AS statement
FROM information_schema.tables
WHERE table_schema = 'ooad';

DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS `blacklist`;
DROP TABLE IF EXISTS `building`;
DROP TABLE IF EXISTS `bus_line`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `buildings_image`;
DROP TABLE IF EXISTS `room_type_image`;
DROP TABLE IF EXISTS `comment_id_image`;
DROP TABLE IF EXISTS `image`;
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

DROP TABLE IF EXISTS `Building`;
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
                          building_type VARCHAR(255),
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

DROP TABLE IF EXISTS 'Room_type';
-- 房间类型表
CREATE TABLE Room_type (
                           room_type_ID INT AUTO_INCREMENT,
                           type VARCHAR(255),
                           capacity INT,
                           description TEXT,
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


DROP TABLE IF EXISTS `Comment`;
-- 评价表
CREATE TABLE Comment (
                         comment_ID INT AUTO_INCREMENT,
                         user_ID INT,
                         time TIMESTAMP,
                         text TEXT,
                         building_ID INT,
                         score INT,
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


DROP TABLE IF EXISTS `Reservation`;
-- 预订表
CREATE TABLE Reservation (
                             Reservation_ID INT AUTO_INCREMENT,
                             room_ID INT,
                             start_time TIMESTAMP,
                             end_time TIMESTAMP,
                             user_ID INT,
                             description TEXT,
                             PRIMARY KEY (Reservation_ID)
);
DROP TABLE IF EXISTS `loginlog`;
CREATE TABLE login_log(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_ID INT,
    login_time TIMESTAMP,
    ip_address VARCHAR(255),
    port VARCHAR(255)
);


insert into User(phone, name, email, password, image_ID) values ('15138688888', 'evan', 'evan3li@outlook.com', '123456', 1);
insert into User(phone, name, email, password, image_ID) values ('16138688888', 'ln', 'example@gmail.com', '123456', 2);
insert into User(phone, name, email, password, image_ID) values ('17138688888', 'jimmy', '12112517@mail.sustech.edu.cn', '123456', 3);
insert into User(phone, name, email, password, image_ID) values ('18138688888', 'juan', '111@abc.com', '123456',4);


insert into Admin(name, phone, email, password) values ('admin', '15138688888', 'evan3li@outlook.com', '123456');

insert into comment(comment_ID, user_ID, time, text,  building_ID, score, admin_ID) VALUE (1, 1, '2021-1-1', 'hhh',  1, 2.00, 1);

insert into comment(comment_ID, user_ID, time, text,  building_ID, score, admin_ID) VALUE (2, 1, '2021-1-1', 'hhh',  1, 2.00, 1);

insert into comment(comment_ID, user_ID, time, text,  building_ID, score, admin_ID) VALUE (3, 1, '2021-1-1', 'hhh',  2, 2.00, 1);

insert into comment(comment_ID, user_ID, time, text,  building_ID, score, admin_ID) VALUE (4, 1, '2021-1-1', 'hhh',  2, 2.00, 0);


-- 第三教学楼
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID)
values ('第三教学楼', '06:00:00', '22:00:00', '第三教学楼', '第三教学楼是一个教学楼', '二号门', 'https://www.bilibili.com/video/BV1Y7411H7jZ', 1);
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/3rd_teaching_building/%E4%B8%89%E6%95%99.jpg');
insert into Buildings_Image(building_ID, image_ID) values (1, 1);

insert into Room_type(type, capacity, description)
values ('机房1', 47, "该房间为计算机实验室，配备了必要的计算设备和软件，适合进行计算机相关的实践性课程。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E6%9C%BA%E6%88%BF1.png');
insert into Room_type_Image(room_type_ID, image_ID) values (1, 2);

insert into Room(building_ID, number, room_type_ID) values (1, 501, 1);
insert into Room(building_ID, number, room_type_ID) values (1, 502, 1);
insert into Room(building_ID, number, room_type_ID) values (1, 503, 1);
insert into Room(building_ID, number, room_type_ID) values (1, 504, 1);
insert into Room(building_ID, number, room_type_ID) values (1, 505, 1);
insert into Room(building_ID, number, room_type_ID) values (1, 506, 1);
insert into Room(building_ID, number, room_type_ID) values (1, 507, 1);
insert into Room(building_ID, number, room_type_ID) values (1, 403, 1);

insert into Room_type(type, capacity, description)
values ('机房2', 54, "该房间为计算机实验室，配备了必要的计算设备和软件，适合进行计算机相关的实践性课程。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E6%9C%BA%E6%88%BF2.png');
insert into Room_type_Image(room_type_ID, image_ID) values (2, 3);

insert into Room(building_ID, number, room_type_ID) values (1, 509, 2);
insert into Room(building_ID, number, room_type_ID) values (1, 510, 2);
insert into Room(building_ID, number, room_type_ID) values (1, 511, 2);

insert into Room_type(type, capacity, description)
values ('机房3', 74, "机房3为大型计算机实验室，适用于需要较大空间和更多计算资源的实验和项目。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E6%9C%BA%E6%88%BF3.png');
insert into Room_type_Image(room_type_ID, image_ID) values (3, 4);

insert into Room(building_ID, number, room_type_ID) values (1, 508, 3);

insert into Room_type(type, capacity, description)
values ('合并教室', 60, "该房间设计用于合并多个班级或小组的教学，提供协作学习和集体讨论的场所。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E5%90%88%E5%B9%B6%E6%95%99%E5%AE%A4.png');
insert into Room_type_Image(room_type_ID, image_ID) values (4, 5);

insert into Room(building_ID, number, room_type_ID) values (1, 112, 4);

insert into Room_type(type, capacity, description)
values ('阶梯教室', 157, "阶梯教室采用阶梯状座位布局，适合举行大型课堂，大型讲座、演讲和学术活动。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E9%98%B6%E6%A2%AF%E6%95%99%E5%AE%A4.png');
insert into Room_type_Image(room_type_ID, image_ID) values (5, 6);

insert into Room(building_ID, number, room_type_ID) values (1, 106, 5);
insert into Room(building_ID, number, room_type_ID) values (1, 107, 5);
insert into Room(building_ID, number, room_type_ID) values (1, 108, 5);
insert into Room(building_ID, number, room_type_ID) values (1, 206, 5);
insert into Room(building_ID, number, room_type_ID) values (1, 207, 5);
insert into Room(building_ID, number, room_type_ID) values (1, 208, 5);

insert into Room_type(type, capacity, description)
values ('授课类小教室', 30, "小型教室适合进行紧凑的授课，提供舒适的学习环境，促进学生与教师之间的互动。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E6%8E%88%E8%AF%BE%E7%B1%BB%E5%B0%8F%E6%95%99%E5%AE%A4.png');
insert into Room_type_Image(room_type_ID, image_ID) values (6, 7);

insert into Room(building_ID, number, room_type_ID) values (1, 113, 6);
insert into Room(building_ID, number, room_type_ID) values (1, 114, 6);
insert into Room(building_ID, number, room_type_ID) values (1, 308, 6);
insert into Room(building_ID, number, room_type_ID) values (1, 309, 6);
insert into Room(building_ID, number, room_type_ID) values (1, 310, 6);

insert into Room_type(type, capacity, description)
values ('授课型教室', 82, "授课型教室为中等规模的教学场所，适用于中小规模的课程和学术讲座。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E6%8E%88%E8%AF%BE%E5%9E%8B%E6%95%99%E5%AE%A4.png');
insert into Room_type_Image(room_type_ID, image_ID) values (7, 8);

insert into Room(building_ID, number, room_type_ID) values (1, 102, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 103, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 104, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 105, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 202, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 203, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 204, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 205, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 301, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 302, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 303, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 304, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 305, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 306, 7);
insert into Room(building_ID, number, room_type_ID) values (1, 307, 7);

insert into Room_type(type, capacity, description)
values ('小研讨型教室', 36, "小研讨型教室适合进行小组研讨和互动式学习，提供良好的讨论氛围。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E5%B0%8F%E7%A0%94%E8%AE%A8%E5%9E%8B%E6%95%99%E5%AE%A4.png');
insert into Room_type_Image(room_type_ID, image_ID) values (8, 9);

insert into Room(building_ID, number, room_type_ID) values (1, 115, 8);
insert into Room(building_ID, number, room_type_ID) values (1, 116, 8);
insert into Room(building_ID, number, room_type_ID) values (1, 211, 8);
insert into Room(building_ID, number, room_type_ID) values (1, 212, 8);
insert into Room(building_ID, number, room_type_ID) values (1, 213, 8);
insert into Room(building_ID, number, room_type_ID) values (1, 408, 8);
insert into Room(building_ID, number, room_type_ID) values (1, 409, 8);
insert into Room(building_ID, number, room_type_ID) values (1, 410, 8);
insert into Room(building_ID, number, room_type_ID) values (1, 411, 8);

insert into Room_type(type, capacity, description)
values ('中研讨型教室', 42, "中研讨型教室为中等规模的研讨场所，适合进行中小规模的研究和深度讨论。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E4%B8%AD%E7%A0%94%E8%AE%A8%E5%9E%8B%E6%95%99%E5%AE%A4.png');
insert into Room_type_Image(room_type_ID, image_ID) values (9, 10);

insert into Room(building_ID, number, room_type_ID) values (1, 210, 9);
insert into Room(building_ID, number, room_type_ID) values (1, 201, 9);
insert into Room(building_ID, number, room_type_ID) values (1, 401, 9);
insert into Room(building_ID, number, room_type_ID) values (1, 402, 9);
insert into Room(building_ID, number, room_type_ID) values (1, 404, 9);
insert into Room(building_ID, number, room_type_ID) values (1, 405, 9);
insert into Room(building_ID, number, room_type_ID) values (1, 406, 9);
insert into Room(building_ID, number, room_type_ID) values (1, 407, 9);

-- 一丹图书馆
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID)
values ('一丹图书馆', '00:00:00', '24:00:00', '一丹图书馆', '一丹图书馆是一个图书馆', '科研楼', 'https://www.bilibili.com/video/BV1Y7411H7jZ', 1);
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/yidan_library/%E4%B8%80%E4%B8%B9.jpg');
insert into Buildings_Image(building_ID, image_ID) values (2, 11);

insert into Room_type(type, capacity, description)
values ('密集书库', 50, "位于一丹图书馆一楼，共有六联双面书架34列，目前收藏A-E类、T类图书复本，采取开架借阅管理方式。紧密排列的书架，手摇式移动展开，让读者体验不一样的找书乐趣。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E5%AF%86%E9%9B%86%E4%B9%A6%E5%BA%93.jpg');
insert into Room_type_Image(room_type_ID, image_ID) values (10, 12);
insert into Room(building_ID, number, room_type_ID) values (2, 102, 10);

insert into Room_type(type, capacity, description)
values ('放映厅', 32, "位于一丹图书馆一楼，共有可移动座位32个,配备多媒体放映设备。小巧舒适的封闭空间适合举办各种小型读者活动和会议。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E6%94%BE%E6%98%A0%E5%8E%85.jpg');
insert into Room_type_Image(room_type_ID, image_ID) values (11, 13);

insert into Room(building_ID, number, room_type_ID) values (2, 101, 11);

insert into Room_type(type, capacity, description)
values ('电脑区', 50, "位于一丹图书馆一楼和二楼，该区域配置数十台台电脑，其中部分电脑上安装了可提供Wind数据库使用的专业软件。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E7%94%B5%E8%84%91%E5%8C%BA.jpg');
insert into Room_type_Image(room_type_ID, image_ID) values (12, 14);

insert into Room(building_ID, number, room_type_ID) values (2, 103, 12);
insert into Room(building_ID, number, room_type_ID) values (2, 202, 12);

insert into Room_type(type, capacity, description)
values ('学习区', 100, "位于一丹图书馆，设有独立单人沙发座椅、长桌等不同类型的阅览座位，读者可根据喜好自由选择。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E5%AD%A6%E4%B9%A0%E5%8C%BA.jpg');
insert into Room_type_Image(room_type_ID, image_ID) values (13, 15);

insert into Room(building_ID, number, room_type_ID) values (2, 104, 13);
insert into Room(building_ID, number, room_type_ID) values (2, 204, 13);
insert into Room(building_ID, number, room_type_ID) values (2, 314, 13);
insert into Room(building_ID, number, room_type_ID) values (2, 401, 13);

insert into Room_type(type, capacity, description)
values ('展览区', 30, "位于一丹图书馆二楼主出入口右边，可举办小型书画展、课程作品展等不同类型的展览，校内单位和个人可预约使用。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E5%B1%95%E8%A7%88%E5%8C%BA.jpg');
insert into Room_type_Image(room_type_ID, image_ID) values (14, 16);

insert into Room(building_ID, number, room_type_ID) values (2, 201, 14);

insert into Room_type(type, capacity, description)
values ('教参书室', 20, "位于一丹图书馆二楼，单独的封闭式空间，藏有教师指定的课程参考图书。室内配备学习书桌、自助借还书机、自助文印设备，为读者提供借阅一体的舒适服务。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E6%95%99%E5%8F%82%E4%B9%A6%E5%AE%A4.jpg');
insert into Room_type_Image(room_type_ID, image_ID) values (15, 17);

insert into Room(building_ID, number, room_type_ID) values (2, 203, 15);

insert into Room_type(type, capacity, description)
values ('休闲阅览区', 30, "位于一丹图书馆三楼楼梯口处，是集学习与休闲为一体的活动空间。设有不同类型的休闲沙发，还可通往三楼露天平台，学习之余可到平台放松一下，俯瞰南科风光。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E4%BC%91%E9%97%B2%E9%98%85%E8%A7%88%E5%8C%BA.jpg');
insert into Room_type_Image(room_type_ID, image_ID) values (16, 18);

insert into Room(building_ID, number, room_type_ID) values (2, 303, 16);

insert into Room_type(type, capacit, descriptiony)
values ('协作学习区', 35, "位于一丹图书馆三楼楼梯转角处，用网纱和白板隔断出7个不同特色的协作学习空间，搭配高矮不一的各色家具、多功能一体机，使每个空间都有各自特色，读者可根据需求在网上预约适合的协作学习区来开展学习或活动。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E5%8D%8F%E4%BD%9C%E5%AD%A6%E4%B9%A0%E5%8C%BA.jpg');
insert into Room_type_Image(room_type_ID, image_ID) values (17, 19);

insert into Room(building_ID, number, room_type_ID) values (2, 313, 17);

insert into Room_type(type, capacity, description)
values ('讨论间', 6, "位于一丹图书馆三楼，共有9间封闭式讨论空间。全透明玻璃隔断，让室外路过的读者也能感受室内的讨论、学习氛围。彩色的墙壁和不同风格的桌椅，让每个空间都充满活力。每个空间都配备多功能一体机、白板，让讨论和学习更为便捷。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E8%AE%A8%E8%AE%BA%E9%97%B4.jpg');
insert into Room_type_Image(room_type_ID, image_ID) values (18, 20);

insert into Room(building_ID, number, room_type_ID) values (2, 304, 18);
insert into Room(building_ID, number, room_type_ID) values (2, 305, 18);
insert into Room(building_ID, number, room_type_ID) values (2, 306, 18);
insert into Room(building_ID, number, room_type_ID) values (2, 307, 18);
insert into Room(building_ID, number, room_type_ID) values (2, 308, 18);
insert into Room(building_ID, number, room_type_ID) values (2, 309, 18);
insert into Room(building_ID, number, room_type_ID) values (2, 310, 18);
insert into Room(building_ID, number, room_type_ID) values (2, 311, 18);
insert into Room(building_ID, number, room_type_ID) values (2, 312, 18);

insert into Room_type(type, capacity, description)
values ('报告厅', 76, "位于一丹图书馆三楼北面，弧形阶梯式设计风格独具特色，共有固定座位76个，可用于举办各类小型学术研讨会议，校内单位和个人可预约使用。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E6%8A%A5%E5%91%8A%E5%8E%85.jpg');
insert into Room_type_Image(room_type_ID, image_ID) values (19, 21);

insert into Room(building_ID, number, room_type_ID) values (2, 301, 17);

insert into Room_type(type, capacity, description)
values ('培训教室', 6, "位于一丹图书馆三楼报告厅对面，内设可移动桌椅，以及集放映书写于一体的高科技白板，可容纳读者30人左右，是图书馆信息素养培训的主要场地，也向全校师生开放，校内单位和个人可预约使用。");
insert into Image(image_url) values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E5%9F%B9%E8%AE%AD%E6%95%99%E5%AE%A4.jpg');
insert into Room_type_Image(room_type_ID, image_ID) values (20, 22);

insert into Room(building_ID, number, room_type_ID) values (2, 302, 18);

