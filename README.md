# 南科大导航 - Sustech Campus

## 项目简介 / Project Overview  
南科大导航是一个在线网站，旨在为游客和注册用户提供便捷的校园导航服务。游客可以查看南科大的建筑信息和公交线路，注册用户可以访问更详细的校园资源，还可以进行资源预订和评价学校设施。系统管理员可以高效管理网站内容和用户交互。  
**Sustech Campus** is an online platform designed to provide convenient campus navigation services for visitors and registered users. Visitors can view information about SUSTech's buildings and bus routes, while registered users can access more detailed campus resources, make reservations, and evaluate campus facilities. System administrators can efficiently manage site content and user interactions.

---

## 功能特点 / Features  

### 页面美观 / User-Friendly Interface  
- 动漫角色导游（看板娘+语音）  
  Animated character guides (mascot + voice).  
- 兼具用户界面和管理员界面  
  Supports both user and administrator interfaces.  
- 用户和管理员界面分离  
  Separate user and admin interfaces.  
- 前端支持中英双语切换  
  Frontend supports bilingual (Chinese and English) switching.  

### 交通导航 / Transportation Navigation  
- 根据两点经纬度实现步行路线和校内公交线路计算  
  Calculates walking routes and on-campus bus routes based on two geographic coordinates.  
- 提供动态路径演示（基于用户位置实现导航）  
  Offers dynamic path demonstrations with navigation based on user location.  
- 使用 HTTPS 协议提升数据安全性  
  Ensures data security with HTTPS protocol.  

### 支付功能与文创购买 / Payment System & Cultural Product Sales  
- 支持完整的支付宝支付系统  
  Fully supports the Alipay payment system.  
- 提供文创产品购买服务平台  
  Provides a platform for purchasing cultural products.  

### 移动端与社交功能 / Mobile-Responsive Design & Social Features  
- 响应式页面设计  
  Mobile-responsive page design.  
- 支持多图评价  
  Supports multi-image reviews.  
- 评论点赞功能  
  Allows users to like comments.  

### 优质数据 / High-Quality Data  
- 提供自制建筑介绍视频  
  Includes self-produced building introduction videos.  
- 使用真实南科大数据，包括建筑、房间、文创信息  
  Utilizes real SUSTech data, including buildings, rooms, and cultural product information.  

### 安全设计 / Secure Design  
- 使用 token 进行鉴权，防止恶意访问  
  Authenticates users with tokens to prevent unauthorized access.  
- 数据加密传输，邮件验证码验证  
  Encrypts data transmission and uses email verification codes.  
- 日志记录访问信息  
  Logs access information.  
- 前端设置路由拦截  
  Implements route interception on the frontend.  

### 微服务架构 / Microservice Architecture  
- 前后端分离的微服务架构  
  Microservice architecture with frontend-backend separation.  
- 使用 Nginx 作为反向代理  
  Utilizes Nginx as a reverse proxy.  

### 预约高并发 / High-Concurrency Reservation System  
- 采用 Redis 实现高并发处理  
  Handles high concurrency using Redis.  

---

## 主要模块及技术实现 / Key Modules and Technical Implementation  

1. **权限管理 / User Roles**  
   - 游客、注册用户和系统管理员三种权限  
     Three roles: visitors, registered users, and system administrators.  

2. **校园平面图 / Campus Map**  
   - 显示关键建筑和道路信息，支持建筑介绍查询  
     Displays key buildings and roads, supports building information queries.  

3. **评价功能 / Review System**  
   - 注册用户可以对建筑进行文字和图片评价，需审批通过后展示  
     Registered users can submit text and image reviews, which are displayed after approval.  

4. **预定功能 / Reservation System**  
   - 支持教室预定、食堂订餐、体育馆场地预定等  
     Supports classroom reservations, canteen orders, and sports venue bookings.  

5. **系统管理员功能 / Admin Features**  
   - 审核评论、批量注册账号、数据分析和预约量统计  
     Approves reviews, registers accounts in batches, analyzes data, and tracks reservation statistics.  

---

## 技术栈 / Tech Stack  

- **后端 / Backend**: Spring Boot, Redis, MySQL  
- **前端 / Frontend**: Vue.js  
- **部署 / Deployment**: Nginx, Microservice Architecture  
- **安全 / Security**: RSA+AES encryption, Spring Security  
- **支付 / Payment**: Integrated Alipay  

---

## 如何运行 / How to Run  

1. 克隆本项目 / Clone this repository:  
   ```bash
   git clone https://github.com/your-repo/Sustech-Campus.git
   ```  

2. 配置数据库 / Configure the database:  
   - 在 `application.yml` 中配置 MySQL 和 Redis 信息。  
     Update MySQL and Redis information in `application.yml`.  

3. 启动后端 / Start the backend:  
   ```bash
   mvn spring-boot:run
   ```  

4. 启动前端 / Start the frontend:  
   ```bash
   npm install
   npm run serve
   ```  

5. 使用浏览器访问 `http://localhost:8080` / Open `http://localhost:8080` in your browser.  

---

欢迎大家对本项目提出意见与建议！🎉  
Feel free to share your feedback and suggestions for this project! 🎉  
