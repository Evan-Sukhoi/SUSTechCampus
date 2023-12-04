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

CREATE TABLE User
(
    user_ID  INT AUTO_INCREMENT,
    name     VARCHAR(255),
    phone    VARCHAR(255),
    email    VARCHAR(255),
    password VARCHAR(255),
    is_blocked BOOLEAN,
    image_ID INT,
    PRIMARY KEY (user_ID)
);

-- 管理员表

CREATE TABLE Admin
(
    admin_ID INT AUTO_INCREMENT,
    name     VARCHAR(255),
    phone    VARCHAR(255),
    email    VARCHAR(255),
    password VARCHAR(255),
    image_ID INT,
    PRIMARY KEY (admin_ID)
);

DROP TABLE IF EXISTS `Building`;

-- 建筑表

CREATE TABLE Building
(
    building_ID     INT AUTO_INCREMENT,
    name            VARCHAR(255),
    open_time       TIME,
    close_time      TIME,
    location_name   VARCHAR(255),
    introduction    TEXT,
    nearest_station VARCHAR(255),
    video_url       VARCHAR(255),
    cover_ID        INT,
    building_type   VARCHAR(255),
    is_reservable   BOOLEAN,
    PRIMARY KEY (building_ID)
);

-- 建筑对应图片 一对多需要新建表

CREATE TABLE Buildings_Image
(
    building_ID INT,
    image_ID    INT
);

-- 房间表

CREATE TABLE Room
(
    room_ID      INT AUTO_INCREMENT,
    building_ID  INT,
    number       INT,
    room_type_ID INT,
    PRIMARY KEY (room_ID)
);


DROP TABLE IF EXISTS `Room_type`;

-- 房间类型表

CREATE TABLE Room_type
(
    room_type_ID INT AUTO_INCREMENT,
    type         VARCHAR(255),
    capacity     INT,
    description  TEXT,
    PRIMARY KEY (room_type_ID)
);

-- Room_type对应图片 一对多需要新建表

CREATE TABLE Room_type_Image
(
    room_type_ID INT,
    image_ID     INT
);

-- 巴士线路表

CREATE TABLE Bus_line
(
    bus_line_ID INT AUTO_INCREMENT,
    line_ID     INT,
    station     VARCHAR(255),
    _index_     INT,
    PRIMARY KEY (bus_line_ID)
);


DROP TABLE IF EXISTS `Comment`;

-- 评价表

CREATE TABLE Comment
(
    comment_ID  INT AUTO_INCREMENT,
    user_ID     INT,
    time        TIMESTAMP,
    text        TEXT,
    building_ID INT,
    score       INT,
    admin_ID    INT,
    PRIMARY KEY (comment_ID)
);

-- comment_ID对应图片 一对多需要新建表

CREATE TABLE Comment_ID_Image
(
    comment_ID INT,
    image_ID   INT
);

-- 创建图片表

CREATE TABLE Image
(
    image_ID  INT AUTO_INCREMENT PRIMARY KEY,
    image_url VARCHAR(255)
);


DROP TABLE IF EXISTS `Reservation`;

-- 预订表

CREATE TABLE Reservation
(
    Reservation_ID INT AUTO_INCREMENT,
    room_ID        INT,
    start_time     TIMESTAMP,
    end_time       TIMESTAMP,
    user_ID        INT,
    description    TEXT,
    PRIMARY KEY (Reservation_ID)
);


DROP TABLE IF EXISTS `loginlog`;


CREATE TABLE login_log
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_ID    INT,
    login_time TIMESTAMP,
    ip_address VARCHAR(255),
    port       VARCHAR(255)
);

DROP TABLE IF EXISTS `illegal_operation_log`;
CREATE TABLE illegal_operation_log
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_ID    INT,
    operation VARCHAR(255),
    operation_time TIMESTAMP,
    ip_address VARCHAR(255),
    port       VARCHAR(255)
);

-- 商品表  构造器输入的都是字符串

CREATE TABLE Product
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    subject VARCHAR(255) NOT NULL,
    body    VARCHAR(1000),
    amount  VARCHAR(20)  NOT NULL,
    CDKEK   VARCHAR(255) NOT NULL,
    shop    VARCHAR(255) NOT NULL,
    inventory INT NOT NULL
);

insert into User(phone, name, email, password, image_ID, is_blocked)
values ('15138688888',
        'aaa',
        'evan3li@outlook.com',
        'aaaaaaaa',
        1,
        false);

insert into User(phone, name, email, password, image_ID, is_blocked)
values ('15138688888',
        'evan',
        'evan3li@outlook.com',
        '123456',
        1,
        false);


insert into User(phone, name, email, password, image_ID, is_blocked)
values ('16138688888',
        'ln',
        'example@gmail.com',
        '123456',
        2,
        false);


insert into User(phone, name, email, password, image_ID, is_blocked)
values ('17138688888',
        'jimmy',
        '12112517@mail.sustech.edu.cn',
        '123456',
        3,
        false);


insert into User(phone, name, email, password, image_ID, is_blocked)
values ('18138688888',
        'juan',
        '111@abc.com',
        '123456',
        4,
        false);


insert into Admin(name, phone, email, password)
values ('admin',
        '15138688888',
        'evan3li@outlook.com',
        '123456');


insert into comment(comment_ID, user_ID, time, text, building_ID, score, admin_ID) VALUE (1, 1, '2021-1-1', 'hhh', 1, 2.00, 1);


insert into comment(comment_ID, user_ID, time, text, building_ID, score, admin_ID) VALUE (2, 1, '2021-1-1', 'hhh', 1, 2.00, 1);


insert into comment(comment_ID, user_ID, time, text, building_ID, score, admin_ID) VALUE (3, 1, '2021-1-1', 'hhh', 2, 2.00, 1);


insert into comment(comment_ID, user_ID, time, text, building_ID, score, admin_ID) VALUE (4, 1, '2021-1-1', 'hhh', 2, 2.00, 0);

-- 第三教学楼

insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('第三教学楼',
        '06:00:00',
        '22:00:00',
        '第三教学楼',
        '第三教学楼是一个教学楼',
        '二号门',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        1,
        '教学楼',
        1);


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/3rd_teaching_building/%E4%B8%89%E6%95%99.jpg');


insert into Buildings_Image(building_ID, image_ID)
values (1,
        1);


insert into Room_type(type, capacity, description)
values ('机房1',
        47,
        "该房间为计算机实验室，配备了必要的计算设备和软件，适合进行计算机相关的实践性课程。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E6%9C%BA%E6%88%BF1.png');


insert into Room_type_Image(room_type_ID, image_ID)
values (1,
        2);


insert into Room(building_ID, number, room_type_ID)
values (1,
        501,
        1);


insert into Room(building_ID, number, room_type_ID)
values (1,
        502,
        1);


insert into Room(building_ID, number, room_type_ID)
values (1,
        503,
        1);


insert into Room(building_ID, number, room_type_ID)
values (1,
        504,
        1);


insert into Room(building_ID, number, room_type_ID)
values (1,
        505,
        1);


insert into Room(building_ID, number, room_type_ID)
values (1,
        506,
        1);


insert into Room(building_ID, number, room_type_ID)
values (1,
        507,
        1);


insert into Room(building_ID, number, room_type_ID)
values (1,
        403,
        1);


insert into Room_type(type, capacity, description)
values ('机房2',
        54,
        "该房间为计算机实验室，配备了必要的计算设备和软件，适合进行计算机相关的实践性课程。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E6%9C%BA%E6%88%BF2.png');


insert into Room_type_Image(room_type_ID, image_ID)
values (2,
        3);


insert into Room(building_ID, number, room_type_ID)
values (1,
        509,
        2);


insert into Room(building_ID, number, room_type_ID)
values (1,
        510,
        2);


insert into Room(building_ID, number, room_type_ID)
values (1,
        511,
        2);


insert into Room_type(type, capacity, description)
values ('机房3',
        74,
        "机房3为大型计算机实验室，适用于需要较大空间和更多计算资源的实验和项目。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E6%9C%BA%E6%88%BF3.png');


insert into Room_type_Image(room_type_ID, image_ID)
values (3,
        4);


insert into Room(building_ID, number, room_type_ID)
values (1,
        508,
        3);


insert into Room_type(type, capacity, description)
values ('合并教室',
        60,
        "该房间设计用于合并多个班级或小组的教学，提供协作学习和集体讨论的场所。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E5%90%88%E5%B9%B6%E6%95%99%E5%AE%A4.png');


insert into Room_type_Image(room_type_ID, image_ID)
values (4,
        5);


insert into Room(building_ID, number, room_type_ID)
values (1,
        112,
        4);


insert into Room_type(type, capacity, description)
values ('阶梯教室',
        157,
        "阶梯教室采用阶梯状座位布局，适合举行大型课堂，大型讲座、演讲和学术活动。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E9%98%B6%E6%A2%AF%E6%95%99%E5%AE%A4.png');


insert into Room_type_Image(room_type_ID, image_ID)
values (5,
        6);


insert into Room(building_ID, number, room_type_ID)
values (1,
        106,
        5);


insert into Room(building_ID, number, room_type_ID)
values (1,
        107,
        5);


insert into Room(building_ID, number, room_type_ID)
values (1,
        108,
        5);


insert into Room(building_ID, number, room_type_ID)
values (1,
        206,
        5);


insert into Room(building_ID, number, room_type_ID)
values (1,
        207,
        5);


insert into Room(building_ID, number, room_type_ID)
values (1,
        208,
        5);


insert into Room_type(type, capacity, description)
values ('授课类小教室',
        30,
        "小型教室适合进行紧凑的授课，提供舒适的学习环境，促进学生与教师之间的互动。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E6%8E%88%E8%AF%BE%E7%B1%BB%E5%B0%8F%E6%95%99%E5%AE%A4.png');


insert into Room_type_Image(room_type_ID, image_ID)
values (6,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        113,
        6);


insert into Room(building_ID, number, room_type_ID)
values (1,
        114,
        6);


insert into Room(building_ID, number, room_type_ID)
values (1,
        308,
        6);


insert into Room(building_ID, number, room_type_ID)
values (1,
        309,
        6);


insert into Room(building_ID, number, room_type_ID)
values (1,
        310,
        6);


insert into Room_type(type, capacity, description)
values ('授课型教室',
        82,
        "授课型教室为中等规模的教学场所，适用于中小规模的课程和学术讲座。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E6%8E%88%E8%AF%BE%E5%9E%8B%E6%95%99%E5%AE%A4.png');


insert into Room_type_Image(room_type_ID, image_ID)
values (7,
        8);


insert into Room(building_ID, number, room_type_ID)
values (1,
        102,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        103,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        104,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        105,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        202,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        203,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        204,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        205,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        301,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        302,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        303,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        304,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        305,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        306,
        7);


insert into Room(building_ID, number, room_type_ID)
values (1,
        307,
        7);


insert into Room_type(type, capacity, description)
values ('小研讨型教室',
        36,
        "小研讨型教室适合进行小组研讨和互动式学习，提供良好的讨论氛围。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E5%B0%8F%E7%A0%94%E8%AE%A8%E5%9E%8B%E6%95%99%E5%AE%A4.png');


insert into Room_type_Image(room_type_ID, image_ID)
values (8,
        9);


insert into Room(building_ID, number, room_type_ID)
values (1,
        115,
        8);


insert into Room(building_ID, number, room_type_ID)
values (1,
        116,
        8);


insert into Room(building_ID, number, room_type_ID)
values (1,
        211,
        8);


insert into Room(building_ID, number, room_type_ID)
values (1,
        212,
        8);


insert into Room(building_ID, number, room_type_ID)
values (1,
        213,
        8);


insert into Room(building_ID, number, room_type_ID)
values (1,
        408,
        8);


insert into Room(building_ID, number, room_type_ID)
values (1,
        409,
        8);


insert into Room(building_ID, number, room_type_ID)
values (1,
        410,
        8);


insert into Room(building_ID, number, room_type_ID)
values (1,
        411,
        8);


insert into Room_type(type, capacity, description)
values ('中研讨型教室',
        42,
        "中研讨型教室为中等规模的研讨场所，适合进行中小规模的研究和深度讨论。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/3rd_teaching_building/%E4%B8%AD%E7%A0%94%E8%AE%A8%E5%9E%8B%E6%95%99%E5%AE%A4.png');


insert into Room_type_Image(room_type_ID, image_ID)
values (9,
        10);


insert into Room(building_ID, number, room_type_ID)
values (1,
        210,
        9);


insert into Room(building_ID, number, room_type_ID)
values (1,
        201,
        9);


insert into Room(building_ID, number, room_type_ID)
values (1,
        401,
        9);


insert into Room(building_ID, number, room_type_ID)
values (1,
        402,
        9);


insert into Room(building_ID, number, room_type_ID)
values (1,
        404,
        9);


insert into Room(building_ID, number, room_type_ID)
values (1,
        405,
        9);


insert into Room(building_ID, number, room_type_ID)
values (1,
        406,
        9);


insert into Room(building_ID, number, room_type_ID)
values (1,
        407,
        9);

-- 一丹图书馆

insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('一丹图书馆',
        '00:00:01',
        '23:59:59',
        '一丹图书馆',
        '一丹图书馆是一个图书馆',
        '科研楼',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        11,
        '图书馆',
        1);


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/yidan_library/%E4%B8%80%E4%B8%B9.jpg');


insert into Buildings_Image(building_ID, image_ID)
values (2,
        11);


insert into Room_type(type, capacity, description)
values ('密集书库',
        50,
        "位于一丹图书馆一楼，共有六联双面书架34列，目前收藏A-E类、T类图书复本，采取开架借阅管理方式。紧密排列的书架，手摇式移动展开，让读者体验不一样的找书乐趣。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E5%AF%86%E9%9B%86%E4%B9%A6%E5%BA%93.jpg');


insert into Room_type_Image(room_type_ID, image_ID)
values (10,
        12);


insert into Room(building_ID, number, room_type_ID)
values (2,
        102,
        10);


insert into Room_type(type, capacity, description)
values ('放映厅',
        32,
        "位于一丹图书馆一楼，共有可移动座位32个,配备多媒体放映设备。小巧舒适的封闭空间适合举办各种小型读者活动和会议。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E6%94%BE%E6%98%A0%E5%8E%85.jpg');


insert into Room_type_Image(room_type_ID, image_ID)
values (11,
        13);


insert into Room(building_ID, number, room_type_ID)
values (2,
        101,
        11);


insert into Room_type(type, capacity, description)
values ('电脑区',
        50,
        "位于一丹图书馆一楼和二楼，该区域配置数十台台电脑，其中部分电脑上安装了可提供Wind数据库使用的专业软件。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E7%94%B5%E8%84%91%E5%8C%BA.jpg');


insert into Room_type_Image(room_type_ID, image_ID)
values (12,
        14);


insert into Room(building_ID, number, room_type_ID)
values (2,
        103,
        12);


insert into Room(building_ID, number, room_type_ID)
values (2,
        202,
        12);


insert into Room_type(type, capacity, description)
values ('学习区',
        100,
        "位于一丹图书馆，设有独立单人沙发座椅、长桌等不同类型的阅览座位，读者可根据喜好自由选择。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E5%AD%A6%E4%B9%A0%E5%8C%BA.jpg');


insert into Room_type_Image(room_type_ID, image_ID)
values (13,
        15);


insert into Room(building_ID, number, room_type_ID)
values (2,
        104,
        13);


insert into Room(building_ID, number, room_type_ID)
values (2,
        204,
        13);


insert into Room(building_ID, number, room_type_ID)
values (2,
        314,
        13);


insert into Room(building_ID, number, room_type_ID)
values (2,
        401,
        13);


insert into Room_type(type, capacity, description)
values ('展览区',
        30,
        "位于一丹图书馆二楼主出入口右边，可举办小型书画展、课程作品展等不同类型的展览，校内单位和个人可预约使用。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E5%B1%95%E8%A7%88%E5%8C%BA.jpg');


insert into Room_type_Image(room_type_ID, image_ID)
values (14,
        16);


insert into Room(building_ID, number, room_type_ID)
values (2,
        201,
        14);


insert into Room_type(type, capacity, description)
values ('教参书室',
        20,
        "位于一丹图书馆二楼，单独的封闭式空间，藏有教师指定的课程参考图书。室内配备学习书桌、自助借还书机、自助文印设备，为读者提供借阅一体的舒适服务。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E6%95%99%E5%8F%82%E4%B9%A6%E5%AE%A4.jpg');


insert into Room_type_Image(room_type_ID, image_ID)
values (15,
        17);


insert into Room(building_ID, number, room_type_ID)
values (2,
        203,
        15);


insert into Room_type(type, capacity, description)
values ('休闲阅览区',
        30,
        "位于一丹图书馆三楼楼梯口处，是集学习与休闲为一体的活动空间。设有不同类型的休闲沙发，还可通往三楼露天平台，学习之余可到平台放松一下，俯瞰南科风光。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E4%BC%91%E9%97%B2%E9%98%85%E8%A7%88%E5%8C%BA.jpg');


insert into Room_type_Image(room_type_ID, image_ID)
values (16,
        18);


insert into Room(building_ID, number, room_type_ID)
values (2,
        303,
        16);


insert into Room_type(type, capacity, description)
values ('协作学习区',
        35,
        "位于一丹图书馆三楼楼梯转角处，用网纱和白板隔断出7个不同特色的协作学习空间，搭配高矮不一的各色家具、多功能一体机，使每个空间都有各自特色，读者可根据需求在网上预约适合的协作学习区来开展学习或活动。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E5%8D%8F%E4%BD%9C%E5%AD%A6%E4%B9%A0%E5%8C%BA.jpg');


insert into Room_type_Image(room_type_ID, image_ID)
values (17,
        19);


insert into Room(building_ID, number, room_type_ID)
values (2,
        313,
        17);


insert into Room_type(type, capacity, description)
values ('讨论间',
        6,
        "位于一丹图书馆三楼，共有9间封闭式讨论空间。全透明玻璃隔断，让室外路过的读者也能感受室内的讨论、学习氛围。彩色的墙壁和不同风格的桌椅，让每个空间都充满活力。每个空间都配备多功能一体机、白板，让讨论和学习更为便捷。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E8%AE%A8%E8%AE%BA%E9%97%B4.jpg');


insert into Room_type_Image(room_type_ID, image_ID)
values (18,
        20);


insert into Room(building_ID, number, room_type_ID)
values (2,
        304,
        18);


insert into Room(building_ID, number, room_type_ID)
values (2,
        305,
        18);


insert into Room(building_ID, number, room_type_ID)
values (2,
        306,
        18);


insert into Room(building_ID, number, room_type_ID)
values (2,
        307,
        18);


insert into Room(building_ID, number, room_type_ID)
values (2,
        308,
        18);


insert into Room(building_ID, number, room_type_ID)
values (2,
        309,
        18);


insert into Room(building_ID, number, room_type_ID)
values (2,
        310,
        18);


insert into Room(building_ID, number, room_type_ID)
values (2,
        311,
        18);


insert into Room(building_ID, number, room_type_ID)
values (2,
        312,
        18);


insert into Room_type(type, capacity, description)
values ('报告厅',
        76,
        "位于一丹图书馆三楼北面，弧形阶梯式设计风格独具特色，共有固定座位76个，可用于举办各类小型学术研讨会议，校内单位和个人可预约使用。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E6%8A%A5%E5%91%8A%E5%8E%85.jpg');


insert into Room_type_Image(room_type_ID, image_ID)
values (19,
        21);


insert into Room(building_ID, number, room_type_ID)
values (2,
        301,
        17);


insert into Room_type(type, capacity, description)
values ('培训教室',
        6,
        "位于一丹图书馆三楼报告厅对面，内设可移动桌椅，以及集放映书写于一体的高科技白板，可容纳读者30人左右，是图书馆信息素养培训的主要场地，也向全校师生开放，校内单位和个人可预约使用。");


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/yidan_library/%E5%9F%B9%E8%AE%AD%E6%95%99%E5%AE%A4.jpg');


insert into Room_type_Image(room_type_ID, image_ID)
values (20,
        22);


insert into Room(building_ID, number, room_type_ID)
values (2,
        302,
        18);

--琳恩图书馆
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('琳恩图书馆',
        '06:00:00',
        '22:00:00',
        '琳恩图书馆',
        '琳恩图书馆是一个图书馆',
        '一号门',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        23,
        '图书馆',
        1);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/lynn_library/%E7%90%B3%E6%81%A9%E5%9B%BE%E4%B9%A6%E9%A6%86.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (3,
        23);
-- 讨论间 18
insert into Room(building_ID, number, room_type_ID)
values (3,
        101,
        18);

insert into Room(building_ID, number, room_type_ID)
values (3,
        102,
        18);

insert into Room(building_ID, number, room_type_ID)
values (3,
        103,
        18);

insert into Room(building_ID, number, room_type_ID)
values (3,
        104,
        18);

insert into Room(building_ID, number, room_type_ID)
values (3,
        105,
        18);

insert into Room(building_ID, number, room_type_ID)
values (3,
        301,
        18);

insert into Room(building_ID, number, room_type_ID)
values (3,
        302,
        18);

insert into Room(building_ID, number, room_type_ID)
values (3,
        303,
        18);

insert into Room(building_ID, number, room_type_ID)
values (3,
        304,
        18);

insert into Room(building_ID, number, room_type_ID)
values (3,
        305,
        18);

insert into Room(building_ID, number, room_type_ID)
values (3,
        306,
        18);
--电脑区 12
insert into Room(building_ID, number, room_type_ID)
values (3,
        201,
        12);

insert into Room(building_ID, number, room_type_ID)
values (3,
        307,
        12);
--学习区 13
insert into Room(building_ID, number, room_type_ID)
values (3,
        106,
        13);
insert into Room(building_ID, number, room_type_ID)
values (3,
        202,
        13);

insert into Room(building_ID, number, room_type_ID)
values (3,
        308,
        13);
-- 协作学习区 17
insert into Room(building_ID, number, room_type_ID)
values (3,
        107,
        17);
-- 培训教室 20
insert into Room(building_ID, number, room_type_ID)
values (3,
        309,
        20);

insert into Room_type(type, capacity, description)
values ('报刊区',
        30,
        "位于琳恩图书馆一楼，设有报刊阅览区和报刊借阅区，读者可在此阅读最新的报纸和期刊，也可借阅部分报刊。");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/lynn_library/%E6%8A%A5%E5%88%8A%E5%8C%BA.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (21,
        24);

insert into Room(building_ID, number, room_type_ID)
values (3,
        108,
        21);

insert into Room_type(type, capacity, description)
values ('书架',
        30,
        "位于琳恩图书馆三楼，设有书架区和自助借还书机，读者可在此借阅图书。");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/lynn_library/%E4%B9%A6%E6%9E%B6.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (22,
        25);

insert into Room(building_ID, number, room_type_ID)
values (3,
        310,
        22);

--理学院
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('理学院',
        '00:00:01',
        '23:59:59',
        '理学院',
        '理学院是一个学院',
        '一号门',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        26,
        '学院',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/college_of_science/%E7%90%86%E5%AD%A6%E9%99%A2.jpeg');
insert into Buildings_Image(building_ID, image_ID)
values (4,
        26);

insert into Room_type(type, capacity, description)
values ('实验室',
        30,
        "实验室是一个实验室");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/college_of_science/%E5%AE%9E%E9%AA%8C%E5%AE%A4.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (23,
        27);

insert into Room(building_ID, number, room_type_ID)
values (4,
        101,
        23);

insert into Room_type(type, capacity, description)
values ('教室',
        30,
        "教室是一个教室");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/college_of_science/%E6%95%99%E5%AE%A4.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (24,
        28);

insert into Room(building_ID, number, room_type_ID)
values (4,
        201,
        24);

insert into Room_type(type, capacity, description)
values ('办公室',
        30,
        "办公室是一个办公室");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/college_of_science/%E5%8A%9E%E5%85%AC%E5%AE%A4.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (25,
        29);

insert into Room(building_ID, number, room_type_ID)
values (4,
        301,
        25);

insert into Room_type(type, capacity, description)
values ('报告厅',
        30,
        "报告厅是一个报告厅");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/college_of_science/%E6%8A%A5%E5%91%8A%E5%8E%85.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (26,
        30);

insert into Room(building_ID, number, room_type_ID)
values (4,
        401,
        26);

--湖畔食堂
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('湖畔食堂',
        '07:00:00',
        '20:00:00',
        '湖畔食堂',
        '湖畔食堂是一个食堂',
        '科研楼',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        31,
        '食堂',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/lake_canteen/%E6%B9%96%E7%95%94%E9%A3%9F%E5%A0%82.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (5,
        31);

insert into Room_type(type, capacity, description)
values ('档口1',
        30,
        "档口1是一个档口");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/lake_canteen/%E6%A1%A3%E5%8F%A3.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (27,
        32);

insert into Room(building_ID, number, room_type_ID)
values (5,
        101,
        27);

insert into Room(building_ID, number, room_type_ID)
values (5,
        102,
        27);

insert into Room(building_ID, number, room_type_ID)
values (5,
        103,
        27);

insert into Room(building_ID, number, room_type_ID)
values (5,
        104,
        27);

insert into Room(building_ID, number, room_type_ID)
values (5,
        105,
        27);

insert into Room(building_ID, number, room_type_ID)
values (5,
        106,
        27);

insert into Room_type(type, capacity, description)
values ('档口2',
        30,
        "档口2是一个档口");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/lake_canteen/%E6%A1%A3%E5%8F%A32.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (28,
        33);

insert into Room(building_ID, number, room_type_ID)
values (5,
        201,
        28);

insert into Room(building_ID, number, room_type_ID)
values (5,
        202,
        28);

insert into Room(building_ID, number, room_type_ID)
values (5,
        203,
        28);

insert into Room(building_ID, number, room_type_ID)
values (5,
        204,
        28);

insert into Room(building_ID, number, room_type_ID)
values (5,
        205,
        28);

insert into Room(building_ID, number, room_type_ID)
values (5,
        206,
        28);

insert into Room_type(type, capacity, description)
values ('档口3',
        30,
        "档口3是一个档口");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/lake_canteen/%E6%A1%A3%E5%8F%A33.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (29,
        34);

insert into Room(building_ID, number, room_type_ID)
values (5,
        107,
        29);

insert into Room(building_ID, number, room_type_ID)
values (5,
        207,
        29);

insert into Room_type(type, capacity, description)
values ('包间',
        30,
        "包间是一个包间");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/lake_canteen/%E5%8C%85%E9%97%B4.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (30,
        35);

insert into Room(building_ID, number, room_type_ID)
values (5,
        208,
        30);

insert into Room_type(type, capacity, description)
values ('自选快餐',
        30,
        "自选快餐是一个自选快餐");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/lake_canteen/%E8%87%AA%E9%80%89%E5%BF%AB%E9%A4%90.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (31,
        36);

insert into Room(building_ID, number, room_type_ID)
values (5,
        108,
        31);

insert into Room_type(type, capacity, description)
values ('饮料店',
        30,
        "饮料店是一个饮料店");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/lake_canteen/%E9%A5%AE%E6%96%99%E5%BA%97.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (32,
        37);

insert into Room(building_ID, number, room_type_ID)
values (5,
        109,
        32);

insert into Room_type(type, capacity, description)
values ('外驻商家',
        30,
        "外驻商家是一个外驻商家");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/lake_canteen/%E5%A4%96%E9%A9%BB%E5%95%86%E5%AE%B6.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (33,
        38);

insert into Room(building_ID, number, room_type_ID)
values (5,
        110,
        33);


--湖畔宿舍
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('湖畔宿舍',
        '00:00:01',
        '23:59:59',
        '湖畔宿舍',
        '湖畔宿舍是一个宿舍',
        '社康中心',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        39,
        '居住场所',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/lake_domitory/%E6%B9%96%E7%95%94%E5%AE%BF%E8%88%8D.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (6,
        39);

insert into Room_type(type, capacity, description)
values ('宿舍1',
        30,
        "宿舍1是一个宿舍");
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/room/lake_domitory/%E5%AE%BF%E8%88%8D1.jpg');
insert into Room_type_Image(room_type_ID, image_ID)
values (34,
        40);

insert into Room(building_ID, number, room_type_ID)
values (6,
        201,
        34);

insert into Room(building_ID, number, room_type_ID)
values (6,
        301,
        34);

insert into Room(building_ID, number, room_type_ID)
values (6,
        401,
        34);

insert into Room(building_ID, number, room_type_ID)
values (6,
        501,
        34);

insert into Room(building_ID, number, room_type_ID)
values (6,
        601,
        34);

--创园
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('创园',
        '00:00:01',
        '23:59:59',
        '创园',
        '创园是一个创业园',
        '创园',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        41,
        '园区',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/%E5%88%9B%E5%9B%AD.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (7,
        41);
--第一教学楼
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('第一教学楼',
        '00:00:01',
        '23:59:59',
        '第一教学楼',
        '第一教学楼是一个教学楼',
        '科研楼',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        42,
        '教学楼',
        1);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/%E7%AC%AC%E4%B8%80%E6%95%99%E5%AD%A6%E6%A5%BC.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (8,
        42);
--二期宿舍
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('二期宿舍',
        '00:00:01',
        '23:59:59',
        '二期宿舍',
        '二期宿舍是一个宿舍',
        '学生宿舍',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        43,
        '居住场所',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/%E4%BA%8C%E6%9C%9F%E5%AE%BF%E8%88%8D.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (9,
        43);
--风雨操场
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('风雨操场',
        '00:00:01',
        '23:59:59',
        '风雨操场',
        '风雨操场是一个操场',
        '社康中心',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        44,
        '体育场地',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/%E9%A3%8E%E9%9B%A8%E6%93%8D%E5%9C%BA.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (10,
        44);
--工学院
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('工学院',
        '00:00:01',
        '23:59:59',
        '工学院',
        '工学院是一个学院',
        '工学院',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        45,
        '学院',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/%E5%B7%A5%E5%AD%A6%E9%99%A2.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (11,
        45);
--涵泳图书馆
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('涵泳图书馆',
        '00:00:01',
        '23:59:59',
        '涵泳图书馆',
        '涵泳图书馆是一个图书馆',
        '工学院',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        46,
        '图书馆',
        1);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/%E6%B6%B5%E6%B3%B3%E5%9B%BE%E4%B9%A6%E9%A6%86.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (12,
        46);
--会议中心
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('会议中心',
        '00:00:01',
        '23:59:59',
        '会议中心',
        '会议中心是一个会议中心',
        '七号门',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        47,
        '会议中心',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/%E4%BC%9A%E8%AE%AE%E4%B8%AD%E5%BF%83.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (13,
        47);
--教师公寓
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('教师公寓',
        '00:00:01',
        '23:59:59',
        '教师公寓',
        '教师公寓是一个公寓',
        '教师公寓',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        48,
        '居住场所',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/%E6%95%99%E5%B8%88%E5%85%AC%E5%AF%93.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (14,
        48);
--荔园
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('荔园',
        '00:00:01',
        '23:59:59',
        '荔园',
        '荔园是一个园区',
        '荔园',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        49,
        '园区',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/%E8%8D%94%E5%9B%AD.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (15,
        49);
--南科大中心
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('南科大中心',
        '00:00:01',
        '23:59:59',
        '南科大中心',
        '南科大中心是一个中心',
        '科研楼',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        50,
        '行政办公场所',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost/building/%E5%8D%97%E7%A7%91%E5%A4%A7%E4%B8%AD%E5%BF%83.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (16,
        50);
--人文社科学院
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('人文社科学院',
        '00:00:01',
        '23:59:59',
        '人文社科学院',
        '人文社科学院是一个学院',
        '教师公寓',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        51,
        '学院',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost/building/%E4%BA%BA%E6%96%87%E7%A4%BE%E7%A7%91%E5%AD%A6%E9%99%A2.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (17,
        51);
--润扬体育馆
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('润扬体育馆',
        '00:00:01',
        '23:59:59',
        '润扬体育馆',
        '润扬体育馆是一个体育馆',
        '教工餐厅',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        52,
        '体育场地',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/%E6%B6%A6%E6%9D%A8%E4%BD%93%E8%82%B2%E9%A6%86.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (18,
        52);
--商学院
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('商学院',
        '00:00:01',
        '23:59:59',
        '商学院',
        '商学院是一个学院',
        '二号门',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        53,
        '学院',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost/building/%E5%95%86%E5%AD%A6%E9%99%A2.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (19,
        53);
--松禾体育场
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable) values ('松禾体育场',
        '00:00:01',
        '23:59:59',
        '松禾体育场',
        '松禾体育场是一个体育场',
        '教工餐厅',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        54,
        '体育场地',
        0);
insert into Image (image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost/building/%E6%9D%BE%E7%A6%BE%E4%BD%93%E8%82%B2%E5%9C%BA.jpg');
insert into Buildings_Image (building_ID, image_ID)
values (20,
        54);
--第一科研楼
insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable) values ('第一科研楼',
        '00:00:01',
        '23:59:59',
        '第一科研楼',
        '第一科研楼是一个科研楼',
        '科研楼',
        'https://www.bilibili.com/video/BV1Y7411H7jZ',
        55,
        '科研楼',
        0);
insert into Image (image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/%E4%B8%80%E7%A7%91.jpg');
insert into Buildings_Image (building_ID, image_ID)
values (21,
        55);

