SELECT CONCAT('DROP TABLE IF EXISTS `', table_name, '`;') AS statement
FROM information_schema.tables
WHERE table_schema = 'ooad';


DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS `building`;
DROP TABLE IF EXISTS `buildings_image`;
DROP TABLE IF EXISTS `bus_line`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `comment_id_image`;
DROP TABLE IF EXISTS `comment_like`;
DROP TABLE IF EXISTS `illegal_operation_log`;
DROP TABLE IF EXISTS `image`;
DROP TABLE IF EXISTS `login_log`;
DROP TABLE IF EXISTS `order_info`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `reservation`;
DROP TABLE IF EXISTS `room`;
DROP TABLE IF EXISTS `room_type`;
DROP TABLE IF EXISTS `room_type_image`;
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

DROP TABLE IF EXISTS `Comment_Like`;
CREATE TABLE Comment_Like
(
    like_ID    INT AUTO_INCREMENT PRIMARY KEY,
    comment_ID INT,
    user_ID    INT
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
    port       INT
);

DROP TABLE IF EXISTS `product`;
-- 商品表  构造器输入的都是字符串
CREATE TABLE Product
(
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    subject VARCHAR(255) NOT NULL,
    body    VARCHAR(1000),
    amount  FLOAT NOT NULL,
    shop    VARCHAR(255) NOT NULL,
    inventory INT NOT NULL,
    image_ID INT
);

DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `order_info`;
-- 订单表
CREATE TABLE  `Order_Info`
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_sn BIGINT NOT NULL UNIQUE,
    product_ID INT NOT NULL,
    amount FLOAT NOT NULL,
    time TIMESTAMP NOT NULL,
    cdkey VARCHAR(8) NOT NULL,
    status INT NOT NULL,
    buyer_id VARCHAR(16)
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

-- 第三教学楼

insert into Building (name, open_time, close_time, location_name, introduction, nearest_station, video_url, cover_ID, building_type, is_reservable)
values ('第三教学楼',
        '06:00:00',
        '22:00:00',
        '第三教学楼',
        '第三教学楼位于南科大中心与理学院和琳恩图书馆遥遥相望，毗邻商学院大楼。为5层建筑，其中一楼、二楼为阶梯教室以及混合教学教室，三楼为授课型教室，四楼为研讨型教室，五楼为教学机房，共56间教室，教室使用总面积为5800平。',
        '二号门',
        'https://1001b.cn/chronus/url/v1/getVideoUrl/a9d9145a75c14d648e427b767823bb10',
        1,
        '教学楼',
        1);


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/3rd_teaching_building/%E4%B8%89%E6%95%99.jpg');


insert into Buildings_Image(building_ID, image_ID)
values (1,
        1);

insert into Buildings_Image(building_ID, image_ID)
values (1,
        2);

insert into Buildings_Image(building_ID, image_ID)
values (1,
        5);

insert into Buildings_Image(building_ID, image_ID)
values (1,
        8);

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
        '一丹图书馆属校园二期建筑，面积9010㎡，四层结构，坐落于南科大校园核心位置，是学校的标志性建筑。图书馆内设计充分借鉴世界一流大学图书馆建设经验，坚持“服务导向、创新驱动、读者至上、协作共赢”的理念，突出便捷、人性化的特点, 为学校在教学、科研和创新方面的学术追求提供支持和服务，成为校园里一个便捷而又卓越的知识与学术活动中心。',
        '科研楼',
        'https://1001b.cn/chronus/url/v1/getVideoUrl/bff406c1263645b38733355a6c4c72e0',
        11,
        '图书馆',
        1);


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/yidan_library/%E4%B8%80%E4%B8%B9.jpg');


insert into Buildings_Image(building_ID, image_ID)
values (2,
        11);

insert into Buildings_Image(building_ID, image_ID)
values (2,
        12);

insert into Buildings_Image(building_ID, image_ID)
values (2,
        13);

insert into Buildings_Image(building_ID, image_ID)
values (2,
        14);


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
        '琳恩图书馆（英语：Lynn Library ）是目前南科大的三个标准图书馆之一，是一栋现代感强的银白色建筑，位于南科大一期工程的核心区域，与第一教学楼、行政楼、湖畔餐厅相邻。琳恩图书馆启用于2013年，共有三层，面积达10000平方米，可以提供约1000个阅读座位。馆内有阅览区、安静学习区、协作学习区、电子阅览区、讨论间等功能分区。截至2018年6月，琳恩图书馆共收藏纸质图书23万余册、纸质报刊105种、数据库118个。',
        '一号门',
        'https://1001b.cn/chronus/url/v1/getVideoUrl/a9d9145a75c14d648e427b767823bb10',
        23,
        '图书馆',
        1);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/lynn_library/%E7%90%B3%E6%81%A9%E5%9B%BE%E4%B9%A6%E9%A6%86.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (3,
        23);
insert into Buildings_Image(building_ID, image_ID)
values (3,
        24);
insert into Buildings_Image(building_ID, image_ID)
values (3,
        25);
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
        '南方科技大学理学院成立于2017年12月6日，是以从事现代基础科学理论及其相关应用科学技术为主的基础性、研究型学院。学院现有数学系、物理系、化学系、地球与空间科学系、统计与数据科学系5个系，以及深圳量子科学与工程研究院、深圳格拉布斯研究院、南科大杰曼诺夫数学中心、先进光源科学中心4个研究机构。理学院建筑已于2022年中完工并完成内部装修，现在从一号门进入南科大，看到的第一个建筑就是理学院。',
        '一号门',
        'https://1001b.cn/chronus/url/v1/getVideoUrl/a9d9145a75c14d648e427b767823bb10',
        26,
        '学院',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/college_of_science/%E7%90%86%E5%AD%A6%E9%99%A2.jpeg');
insert into Buildings_Image(building_ID, image_ID)
values (4,
        26);
insert into Buildings_Image(building_ID, image_ID)
values (4,
        28);

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
        '湖畔学生餐厅，餐厅位置：大榕树旁一层、二层，供应品种：特色风味，营业时间：早餐：06:30-09:30，午餐：10:30-13:30，晚餐：16:30-19:00，夜宵：19:00-22:30',
        '科研楼',
        'https://1001b.cn/chronus/url/v1/getVideoUrl/d864a5d1ec80418fbec4ebd5075513e1',
        31,
        '食堂',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/lake_canteen/%E6%B9%96%E7%95%94%E9%A3%9F%E5%A0%82.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (5,
        31);
insert into Buildings_Image(building_ID, image_ID)
values (5,
        32);
insert into Buildings_Image(building_ID, image_ID)
values (5,
        34);
insert into Buildings_Image(building_ID, image_ID)
values (5,
        36);

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
        '湖畔宿舍目前主要居住致仁书院，树德书院，致诚书院的同学。其中，湖畔1-3栋是四人间，4-6栋则是双人间。湖畔1栋楼下是学生事务中心，3栋的楼下有一间小超市，5栋楼下则是收发室。湖畔沿湖一侧有若干建筑，包括书院活动室，湖畔健身房和舞蹈房。这些建筑的上面是一个大阳台，可以从六栋旁边的楼梯上去。',
        '社康中心',
        'https://1001b.cn/chronus/url/v1/getVideoUrl/7c1fd75d3cb447469d7acbf8580483bd',
        39,
        '居住场所',
        0);
insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/building/lake_domitory/%E6%B9%96%E7%95%94%E5%AE%BF%E8%88%8D.jpg');
insert into Buildings_Image(building_ID, image_ID)
values (6,
        39);
insert into Buildings_Image(building_ID, image_ID)
values (6,
        40);

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
        '许多院系的实验室和办公区域设置于创园。其中，创园一栋由于进驻了学校的一些机构和校办公司，因此装修水平明显较其他栋高级。',
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
        '简称“一教”。在学校二期工程完成之前，本科生绝大部分基础课都会在第一教学楼上。一教分为南北两翼，南翼主要是教室：一楼的大部分教室都是可以容纳150人+的大型教室，平时主要用于上通识基础课和思政课；二楼主要用作了自习室；三楼和四楼有几间教室平时固定用于英语课程，其他的教室则是用于人数较少的课程。北翼则大部分为实验室：一楼为电子系实验室，二楼为化学系实验室，三楼四楼则是生物系实验室。北翼的外墙是一块LED大屏幕，平时用于播放讲座信息或是学校的宣传片，晚上会放新闻联播。一教的北侧有一个赛百味，一般在课间会排队。在一教的楼顶可以看到整个山下，和建设中的公共教学楼和理学院。',
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
        '于2019年8月落成的新宿舍区域，也是未来大部分同学（本科生，研究生，博士生）的居住区域。但与湖畔宿舍相比，二期宿舍缺少独立卫生间，宿舍面积也相对更小一些，但二期宿舍全部配备了电梯。关于11栋新生楼的特殊调度策略有个小技巧可以破解让电梯停到非调度楼层：如何“破解”11栋电梯。二期宿舍中部有一个两层的二期宿舍学生餐厅，西侧则有一个小型图书馆，学校对他的定位更像是“自习室”。',
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
        '风雨操场的南侧包括排球场，搏击馆和室内篮球场，风雨操场的二层有一个废弃的网球场（可能是因为怕网球打伤人）。室内设施没有空调，夏天会比较热。大一上的体育课大都会在此处进行。',
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
        '工学院设有电子与电气工程系、材料科学与工程系、 环境科学与工程学院、 海洋科学与工程系、力学与航空航天工程系、 机械与能源工程系、计算机科学与工程系、生物医学工程系、深港微电子学院（国家示范性微电子学院）、系统设计与智能制造学院等10个系（院）。同时下设斯发基斯可信自主研究院、复杂流体及软物质研究中心、创新材料研究院、深圳可持续发展研究院、创新智造研究院、碳中和能源研究院、纳米科学与应用研究院、精密光学工程中心8个研究机构。',
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
        '涵泳图书馆：“涵泳”出自朱熹《四书章句集注》，是“从容求索，深入体察”的意思，位于学生宿舍7栋，2层建筑，是距离新生宿舍最近的图书馆，可谓“住在宿舍，抬脚便进了涵泳。”主要收藏特藏文献，图书馆虽小，但却“小而精”',
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
        '南科大会议中心总建筑面积约1.79万平方米，以800人会议厅、600人多功能剧场和200人圆形会议厅为核心功能，同时也是联合国教科文组织高等教育创新中心（中国深圳）的所在地，建筑形态轻盈舒展，建筑界面与山水交错融合。',
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
        '八栋建筑中，有六栋为教师公寓，两栋为专家公寓。教师公寓主要供教职人员居住，一楼设有一间教工餐厅和传言只有faculty才能进的 Faculty Club。森林印务也设置于此。专家公寓对外开放，以宾馆的形式运营，一楼有一间西餐厅，一间中餐厅和一间报告厅。',
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
        '荔园的1，2，5栋主要是教室和实验室，余下部分则为宿舍区域。荔园5栋的1，2楼是餐厅，由外包商运营。6栋楼下有一个超市。',
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
        '南科大中心总建筑面积约3万平方米，是结合了餐饮中心、图书学习中心、活动中心的多功能综合体。橙红色的陶板系统，将不同功能的建筑体块统一在一起，绿色步行长廊穿插缝合，引导人们在各个空间中自由穿行，形成丰富流畅的场景感并创造出生动有趣的公共空间。',
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
        '人文社科学院总建筑面积约1.4万平方米，是校园内的文化核心。三层的建筑聚落轻巧通透，是极富文化韵味的新岭南建筑。',
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
        '体育馆由润杨集团冠名的，内含羽毛球场和健身中心。体育馆南侧目前是野战场地，学校购置了基于红外线的模拟枪，可以打真人CS。这里还有一面攀岩墙。',
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
        '商学院将围绕科技创新企业到产业价值升级再到社会财富传承的思路，培育支持创新创业的“科学家加企业家加金融家”共生融合的生态体系，实现商学在科技创新推动社会进步中的作用与价值。学院拥有完善的本硕博培养体系，旨在培养既有国际视野、又能深入分析和解决中国实际问题的高端商科人才。',
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
                                                                                                                                                            '松禾体育场是一个田径体育场，由松禾资本冠名。',
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
                                                                                                                                                            '学校一期建筑之一，包含各种实验室。内部是一个大天井，二楼有一个给机器人或者是智能机械的测试平台。',
                                                                                                                                                            '科研楼',
                                                                                                                                                            'https://www.bilibili.com/video/BV1Y7411H7jZ',
                                                                                                                                                            55,
                                                                                                                                                            '科研楼',
                                                                                                                                                            0);
insert into Image (image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/10%E5%91%A8%E5%B9%B4%E6%A0%A1%E5%BA%86%E9%9B%86%E5%AD%97%E4%B9%A6%E7%AD%BE%E5%A5%97%E8%A3%85.png');
insert into Buildings_Image (building_ID, image_ID)
values (21,
        55);


insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/10%E5%91%A8%E5%B9%B4%E6%A0%A1%E5%BA%86%E9%9B%86%E5%AD%97%E4%B9%A6%E7%AD%BE%E5%A5%97%E8%A3%85.png');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('10周年校庆集字书签套装',
        '10周年校庆集字书签套装*1',
        78,
        '南科大纪念品商店',
        100,
        56);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E8%8D%94%E5%8F%AF%E6%8A%B1%E6%9E%95%E8%A2%AB.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('荔可抱枕被',
        '荔可抱枕被*1',
        118,
        '南科大纪念品商店',
        100,
        57);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E8%8D%94%E5%8F%AF%E8%A7%A3%E5%8E%8B%E7%90%83.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('荔可解压球',
        '荔可解压球*1',
        18,
        '南科大纪念品商店',
        100,
        58);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E8%8D%94%E5%8F%AF%E5%8D%A1%E5%A5%97.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('荔可卡套',
        '荔可卡套*1',
        18,
        '南科大纪念品商店',
        100,
        59);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E8%8D%94%E5%8F%AF%E9%92%A5%E5%8C%99%E6%89%A3.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('荔可钥匙扣',
        '荔可钥匙扣*1',
        18,
        '南科大纪念品商店',
        100,
        60);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E8%8D%94%E5%8F%AF%E8%8D%94%E6%9E%9D%E5%A8%83%E5%A8%83%E8%8D%94%E5%8F%AF.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('荔枝娃娃荔可',
        '荔枝娃娃荔可*1',
        68,
        '南科大纪念品商店',
        100,
        61);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E7%90%B3%E6%81%A9%E5%9B%BE%E4%B9%A6%E9%A6%86%E8%93%9D%E7%89%99%E9%9F%B3%E7%AE%B1.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('琳恩图书馆蓝牙音箱',
        '琳恩图书馆蓝牙音箱*1',
        348,
        '南科大纪念品商店',
        100,
        62);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E5%8D%97%E7%A7%91%E5%A4%A7%E5%AE%9A%E5%88%B6%E6%97%85%E8%A1%8C%E6%94%B6%E7%BA%B3%E5%8C%85.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('南科大定制旅行收纳包',
        '南科大定制旅行收纳包*1',
        128,
        '南科大纪念品商店',
        100,
        63);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E5%8D%97%E7%A7%91%E5%A4%A7%E5%AE%9A%E5%88%B6%E5%8D%97%E7%A7%91%E9%87%91%E5%B1%9E%E4%B9%A6%E7%AD%BE.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('南科大定制旅行收纳包',
        '南科大定制旅行收纳包*1',
        128,
        '南科大纪念品商店',
        100,
        64);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E5%8D%97%E7%A7%91%E5%A4%A7%E5%AE%9A%E5%88%B6%E5%8D%97%E7%A7%91%E9%87%91%E5%B1%9E%E4%B9%A6%E7%AD%BE.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('南科大定制南科金属书签',
        '南科大定制南科金属书签*1',
        68,
        '南科大纪念品商店',
        100,
        65);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E5%8D%97%E7%A7%91%E5%A4%A7%E5%AE%9A%E5%88%B6%E4%B9%A6%E5%8C%85.png');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('南科大定制书包',
        '南科大定制书包*1',
        108,
        '南科大纪念品商店',
        100,
        66);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E5%8D%97%E7%A7%91%E5%A4%A7%E5%AE%9A%E5%88%B6%E9%9B%A8%E4%BC%9E.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('南科大定制雨伞',
        '南科大定制雨伞*1',
        38,
        '南科大纪念品商店',
        100,
        67);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E5%8D%97%E7%A7%91%E4%BA%BA%E4%B8%93%E5%B1%9E%E5%8F%A3%E7%BD%A9.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('南科人专属口罩',
        '南科人专属口罩*5',
        10,
        '南科大纪念品商店',
        100,
        68);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E5%8D%97%E7%A7%91%E4%BA%BA%E4%B8%93%E5%B1%9E%E6%89%8B%E8%B4%A6%E6%9C%AC.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('南科人专属手账本',
        '南科人专属手账本*1',
        28,
        '南科大纪念品商店',
        100,
        69);

insert into Image(image_url)
values ('https://cdn.jsdelivr.net/gh/Evan-Sukhoi/ImageHost@main/souvenir/%E5%8D%97%E7%A7%91%E4%BA%BA%E4%B8%93%E5%B1%9E%E9%9B%A8%E4%BC%9E.jpg');

insert into product (subject, body, amount, shop, inventory, image_ID)
values ('南科人专属雨伞',
        '南科人专属雨伞*1',
        38,
        '南科大纪念品商店',
        100,
        70);

-- Comment 1
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (1, '2023-12-01 08:30:45', '喜欢在第三教学楼学习，环境优雅，设施齐全。', 1, 4.00, 1);

-- Comment 2
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (2, '2023-12-02 12:15:20', '在一丹图书馆度过愉快的阅读时光，氛围很好。', 2, 5.00, 1);

-- Comment 3
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (3, '2023-12-03 19:45:10', '琳恩图书馆的设计很有特色，是一个安静学习的好地方。', 3, 4.00, 1);

-- Comment 4
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (4, '2023-12-04 14:55:30', '理学院的实验设备先进，老师也很热情，学习体验很好。', 4, 5.00, 1);

-- Comment 5
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (5, '2023-12-05 10:10:55', '湖畔食堂的菜品口味独特，价格适中，是我常去的用餐地点。', 5, 3.00, 1);

-- Comment 6
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (1, '2023-12-06 15:50:25', '湖畔宿舍环境舒适，生活设施完备，住得很满意。', 6, 4.00, 1);

-- Comment 7
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (2, '2023-12-01 21:20:40', '第三教学楼设施先进，尤其是实验室，让学习变得更有趣。', 1, 5.00, 1);

-- Comment 8
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (3, '2023-12-02 09:05:15', '一丹图书馆的藏书丰富，对于我的专业研究提供了很多参考资料。', 2, 4.00, 1);

-- Comment 9
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (4, '2023-12-03 16:30:22', '琳恩图书馆的窗外景色美丽，常常在这里找到灵感。', 3, 5.00, 1);

-- Comment 10
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (5, '2023-12-04 11:45:50', '理学院的学术氛围浓厚，对于科研工作提供了良好的平台。', 4, 4.00, 1);

-- Comment 11
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (1, '2023-12-05 18:10:35', '湖畔食堂的服务态度很好，让用餐变得更加愉快。', 5, 5.00, 1);

-- Comment 12
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (2, '2023-12-06 12:25:05', '湖畔宿舍的周围环境宜人，生活很方便，是一个理想的居住地。', 6, 4.00, 1);

-- Comment 13
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (3, '2023-12-01 14:55:30', '第三教学楼的自习室设备齐全，适合集中学习。', 1, 4.00, 1);

-- Comment 14
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (4, '2023-12-02 10:30:15', '一丹图书馆的座位舒适，是我学术写作的首选地点。', 2, 5.00, 1);

-- Comment 15
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (5, '2023-12-03 17:45:22', '琳恩图书馆的夜景很美，夜间学习的氛围很棒。', 3, 4.00, 1);

-- Comment 16
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (1, '2023-12-04 13:10:50', '理学院的课程设置合理，培养了我很多专业技能。', 4, 5.00, 1);

-- Comment 17
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (2, '2023-12-05 08:25:35', '湖畔食堂的菜品新颖，经常有新口味尝试，让人惊喜。', 5, 4.00, 1);

-- Comment 18
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (3, '2023-12-06 16:40:05', '湖畔宿舍的宿管服务态度好，让居住更加安心。', 6, 5.00, 1);

-- Negative Comment 1
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (1, '2023-12-01 11:20:45', '第三教学楼的座椅不舒服，影响了我的学习体验。', 1, 2.00, 1);

-- Negative Comment 2
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (2, '2023-12-02 14:15:20', '一丹图书馆的空调效果很差，夏天里很难集中注意力。', 2, 3.00, 1);

-- Negative Comment 3
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (3, '2023-12-03 21:45:10', '琳恩图书馆的电脑总是出故障，导致我无法顺利完成作业。', 3, 2.00, 1);

-- Negative Comment 4
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (4, '2023-12-04 17:55:30', '理学院的实验室设备陈旧，需要进行更新和维护。', 4, 3.00, 1);

-- Negative Comment 5
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (5, '2023-12-05 13:10:55', '湖畔食堂的卫生状况堪忧，经常发现不干净的餐具。', 5, 2.00, 1);

-- Negative Comment 6
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (1, '2023-12-06 19:50:25', '湖畔宿舍的楼道噪音太大，严重影响了我的休息时间。', 6, 3.00, 1);

-- Mixed Comment 1
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (2, '2023-12-01 08:30:45', '第三教学楼的设施一般，但教学氛围还算不错。', 1, 3.00, 1);

-- Mixed Comment 2
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (3, '2023-12-02 12:15:20', '一丹图书馆的空间设计独特，但馆内设备有待提升。', 2, 4.00, 1);

-- Mixed Comment 3
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (4, '2023-12-03 19:45:10', '琳恩图书馆的外观很漂亮，但座位相对较拥挤。', 3, 3.00, 1);

-- Mixed Comment 4
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (5, '2023-12-04 14:55:30', '理学院的课程设置很有深度，但教学设施需要改进。', 4, 4.00, 1);

-- Mixed Comment 5
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (1, '2023-12-05 10:10:55', '湖畔食堂的菜品种类不够多，但价格相对亲民。', 5, 3.00, 1);

-- Mixed Comment 6
insert into comment(user_ID, time, text, building_ID, score, admin_ID)
values (2, '2023-12-06 15:50:25', '湖畔宿舍的环境宜人，但有时候设施运行不够稳定。', 6, 4.00, 1);

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (69, '2023-12-01 08:30:45', '2023-12-01 10:30:45', 1, '离散数学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (70, '2023-12-02 12:15:20', '2023-12-02 14:15:20', 2, '计算机网络课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (71, '2023-12-03 19:45:10', '2023-12-03 21:45:10', 3, '计算机组成原理课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (72, '2023-12-04 14:55:30', '2023-12-04 16:55:30', 4, '计算机操作系统课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (73, '2023-12-05 10:10:55', '2023-12-05 12:10:55', 5, '计算机图形学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (74, '2023-12-06 15:50:25', '2023-12-06 17:50:25', 1, '计算机体系结构课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (75, '2023-12-01 21:20:40', '2023-12-01 23:20:40', 2, '概率论与数理统计课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (76, '2023-12-02 09:05:15', '2023-12-02 11:05:15', 3, '面向对象设计项目讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (77, '2023-12-03 16:30:22', '2023-12-03 18:30:22', 4, '软件工程课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (80, '2023-12-04 11:45:50', '2023-12-04 13:45:50', 5, '线性代数课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (81, '2023-12-05 18:10:35', '2023-12-05 20:10:35', 1, '离散数学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (82, '2023-12-06 12:25:05', '2023-12-06 14:25:05', 2, '计算机网络课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (83, '2023-12-01 14:55:30', '2023-12-01 16:55:30', 3, '计算机组成原理课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (84, '2023-12-02 10:30:15', '2023-12-02 12:30:15', 4, '计算机操作系统课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (85, '2023-12-03 17:45:22', '2023-12-03 19:45:22', 5, '计算机图形学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (86, '2023-12-04 13:10:50', '2023-12-04 15:10:50', 1, '计算机体系结构课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (87, '2023-12-05 08:25:35', '2023-12-05 10:25:35', 2, '概率论与数理统计课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (88, '2023-12-06 16:40:05', '2023-12-06 18:40:05', 3, '面向对象设计项目讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (89, '2023-12-01 11:20:45', '2023-12-01 13:20:45', 4, '软件工程课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (90, '2023-12-02 14:15:20', '2023-12-02 16:15:20', 5, '线性代数课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (69, '2023-12-03 21:45:10', '2023-12-03 23:45:10', 1, '离散数学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (70, '2023-12-04 16:55:30', '2023-12-04 18:55:30', 2, '计算机网络课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (71, '2023-12-05 12:10:55', '2023-12-05 14:10:55', 3, '计算机组成原理课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (72, '2023-12-06 17:50:25', '2023-12-06 19:50:25', 4, '计算机操作系统课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (73, '2023-12-01 23:20:40', '2023-12-02 01:20:40', 5, '计算机图形学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (74, '2023-12-02 11:05:15', '2023-12-02 13:05:15', 1, '计算机体系结构课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (75, '2023-12-03 18:30:22', '2023-12-03 20:30:22', 2, '概率论与数理统计课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (76, '2023-12-04 14:25:05', '2023-12-04 16:25:05', 3, '面向对象设计项目讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (77, '2023-12-05 20:10:35', '2023-12-05 22:10:35', 4, '软件工程课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (80, '2023-12-06 13:45:50', '2023-12-06 15:45:50', 5, '线性代数课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (81, '2023-12-01 13:20:45', '2023-12-01 15:20:45', 1, '离散数学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (82, '2023-12-02 16:15:20', '2023-12-02 18:15:20', 2, '计算机网络课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (83, '2023-12-03 19:45:10', '2023-12-03 21:45:10', 3, '计算机组成原理课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (84, '2023-12-04 12:30:15', '2023-12-04 14:30:15', 4, '计算机操作系统课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (85, '2023-12-05 19:45:22', '2023-12-05 21:45:22', 5, '计算机图形学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (86, '2023-12-06 15:10:50', '2023-12-06 17:10:50', 1, '计算机体系结构课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (87, '2023-12-01 10:25:35', '2023-12-01 12:25:35', 2, '概率论与数理统计课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (88, '2023-12-02 18:40:05', '2023-12-02 20:40:05', 3, '面向对象设计项目讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (89, '2023-12-03 13:20:45', '2023-12-03 15:20:45', 4, '软件工程课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (90, '2023-12-04 16:15:20', '2023-12-04 18:15:20', 5, '线性代数课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (69, '2023-12-05 14:30:15', '2023-12-05 16:30:15', 1, '离散数学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (70, '2023-12-06 18:55:30', '2023-12-06 20:55:30', 2, '计算机网络课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (71, '2023-12-01 01:20:40', '2023-12-01 03:20:40', 3, '计算机组成原理课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (72, '2023-12-02 01:20:40', '2023-12-02 03:20:40', 4, '计算机操作系统课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (73, '2023-12-03 01:20:40', '2023-12-03 03:20:40', 5, '计算机图形学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (74, '2023-12-04 20:30:22', '2023-12-04 22:30:22', 1, '计算机体系结构课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (75, '2023-12-05 22:10:35', '2023-12-06 00:10:35', 2, '概率论与数理统计课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (76, '2023-12-06 16:25:05', '2023-12-06 18:25:05', 3, '面向对象设计项目讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (77, '2023-12-01 15:45:50', '2023-12-01 17:45:50', 4, '软件工程课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (80, '2023-12-02 20:40:05', '2023-12-02 22:40:05', 5, '线性代数课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (81, '2023-12-03 15:20:45', '2023-12-03 17:20:45', 1, '离散数学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (82, '2023-12-04 18:15:20', '2023-12-04 20:15:20', 2, '计算机网络课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (83, '2023-12-05 21:45:10', '2023-12-05 23:45:10', 3, '计算机组成原理课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (84, '2023-12-06 14:30:15', '2023-12-06 16:30:15', 4, '计算机操作系统课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (85, '2023-12-01 17:10:50', '2023-12-01 19:10:50', 5, '计算机图形学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (86, '2023-12-02 12:25:35', '2023-12-02 14:25:35', 1, '计算机体系结构课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (87, '2023-12-03 12:25:35', '2023-12-03 14:25:35', 2, '概率论与数理统计课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (88, '2023-12-04 22:40:05', '2023-12-05 00:40:05', 3, '面向对象设计项目讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (89, '2023-12-05 15:20:45', '2023-12-05 17:20:45', 4, '软件工程课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (90, '2023-12-06 18:15:20', '2023-12-06 20:15:20', 5, '线性代数课程讨论');

-- Additional Reservation Records

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (69, '2023-12-03 09:30:00', '2023-12-03 11:30:00', 2, '数据库管理课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (70, '2023-12-04 14:00:00', '2023-12-04 16:00:00', 3, '人工智能课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (71, '2023-12-05 17:30:00', '2023-12-05 19:30:00', 4, '数据结构课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (72, '2023-12-06 12:00:00', '2023-12-06 14:00:00', 5, '操作研究课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (73, '2023-12-01 08:00:00', '2023-12-01 10:00:00', 1, '计算机安全课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (74, '2023-12-02 15:30:00', '2023-12-02 17:30:00', 2, '软件测试课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (75, '2023-12-03 20:00:00', '2023-12-03 22:00:00', 3, '算法设计课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (76, '2023-12-04 10:30:00', '2023-12-04 12:30:00', 4, '计算机伦理学讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (77, '2023-12-05 16:00:00', '2023-12-05 18:00:00', 5, '信息检索课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (80, '2023-12-06 11:30:00', '2023-12-06 13:30:00', 1, '网络安全课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (81, '2023-12-01 14:00:00', '2023-12-01 16:00:00', 2, '计算机语言学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (82, '2023-12-02 17:30:00', '2023-12-02 19:30:00', 3, '人机交互课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (83, '2023-12-03 21:00:00', '2023-12-03 23:00:00', 4, '分布式系统课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (84, '2023-12-04 13:30:00', '2023-12-04 15:30:00', 5, '计算理论课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (85, '2023-12-05 18:00:00', '2023-12-05 20:00:00', 1, '机器学习课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (86, '2023-12-06 14:30:00', '2023-12-06 16:30:00', 2, '信息安全管理课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (87, '2023-12-01 12:30:00', '2023-12-01 14:30:00', 3, '软件体系结构课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (88, '2023-12-02 20:00:00', '2023-12-02 22:00:00', 4, '软件项目管理课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (89, '2023-12-03 15:30:00', '2023-12-03 17:30:00', 5, '数据库设计课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (90, '2023-12-04 18:00:00', '2023-12-04 20:00:00', 1, '大数据分析课程讨论');

-- Additional Reservation Records

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (69, '2023-12-03 14:30:00', '2023-12-03 16:30:00', 2, '人工智能课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (70, '2023-12-04 10:00:00', '2023-12-04 12:00:00', 3, '数据结构课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (71, '2023-12-05 19:30:00', '2023-12-05 21:30:00', 4, '操作研究课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (72, '2023-12-06 14:00:00', '2023-12-06 16:00:00', 5, '计算机伦理学讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (73, '2023-12-01 09:30:00', '2023-12-01 11:30:00', 1, '计算机安全课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (74, '2023-12-02 16:30:00', '2023-12-02 18:30:00', 2, '软件测试课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (75, '2023-12-03 21:00:00', '2023-12-03 23:00:00', 3, '算法设计课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (76, '2023-12-04 11:30:00', '2023-12-04 13:30:00', 4, '计算机语言学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (77, '2023-12-05 16:30:00', '2023-12-05 18:30:00', 5, '信息检索课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (80, '2023-12-06 09:00:00', '2023-12-06 11:00:00', 1, '网络安全课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (81, '2023-12-01 12:00:00', '2023-12-01 14:00:00', 2, '计算机语言学课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (82, '2023-12-02 18:30:00', '2023-12-02 20:30:00', 3, '人机交互课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (83, '2023-12-03 22:00:00', '2023-12-04 00:00:00', 4, '分布式系统课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (84, '2023-12-04 14:30:00', '2023-12-04 16:30:00', 5, '计算理论课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (85, '2023-12-05 19:00:00', '2023-12-05 21:00:00', 1, '机器学习课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (86, '2023-12-06 15:30:00', '2023-12-06 17:30:00', 2, '信息安全管理课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (87, '2023-12-01 13:00:00', '2023-12-01 15:00:00', 3, '软件体系结构课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (88, '2023-12-02 21:30:00', '2023-12-02 23:30:00', 4, '软件项目管理课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (89, '2023-12-03 16:30:00', '2023-12-03 18:30:00', 5, '数据库设计课程讨论');

insert into Reservation(room_ID, start_time, end_time, user_ID, description)
values (90, '2023-12-04 19:00:00', '2023-12-04 21:00:00', 1, '大数据分析课程讨论');



