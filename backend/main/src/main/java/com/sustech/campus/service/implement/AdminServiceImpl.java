package com.sustech.campus.service.implement;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.dao.*;
import com.sustech.campus.database.po.*;
import com.sustech.campus.database.utils.ImgHostUploader;
import com.sustech.campus.database.utils.RedisUtil;
import com.sustech.campus.model.param.BuslineParam;
import com.sustech.campus.model.param.RegisterParam;
import com.sustech.campus.model.vo.*;
import com.sustech.campus.service.AdminService;

import com.sustech.campus.service.PublicService;
import com.sustech.campus.utils.RsaUtil;
import com.sustech.campus.web.utils.JwtUtil;
import jakarta.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.sustech.campus.web.utils.ExceptionUtils.asserts;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Resource
    private UserDao userDao;

    @Resource
    private BuildingDao buildingDao;

    @Resource
    private RoomDao roomDao;

    @Resource
    private RoomTypeDao roomTypeDao;

    @Resource
    private CommentDao commentDao;

    @Resource
    private CommentIdImageDao commentIdImageDao;

    @Resource
    private RoomTypeImageDao roomTypeImageDao;

    @Resource
    private ImageDao imageDao;

    @Resource
    private ReservationDao reservationDao;

    @Resource
    private BlacklistDao blacklistDao;

    @Resource
    private LoginLogDao loginLogDao;

    @Resource
    private IllegalOperationLogDao illegalOperationLogDao;
    @Resource
    private PublicService publicService;
//    @Resource
//    private UserService userService;
    @Resource
    private ImgHostUploader imgHostUploader;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RedisUtil redis;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ImgHostUploader.class);

    @Override
    public User getUserInfo(Integer userId) {
        return userDao.selectById(userId);
    }

    @Override
    public Boolean updateUserInfo(Integer userId, String name, String phone, String email, String password) {
        User user = userDao.selectById(userId);
        if (user == null) {
            return false;
        } else {
            user.setName(name);
            user.setPhone(phone);
            user.setEmail(email);
            user.setPassword(password);
            userDao.updateById(user);
            return true;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.selectList(null);
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        User user = userDao.selectById(userId);
        if (user == null) {
            return false;
        } else {
            userDao.deleteById(userId);
            return true;
        }
    }

    @Override
    public List<BuildingInfo> getAllBuilding() {
        List<Building> buildings = buildingDao.selectList(null);
        return buildings.stream().map(building -> {
            return publicService.getBuildingDetails(building.getBuildingId());
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean uploadBuilding(Building building) {
        buildingDao.insert(building);
        return true;
    }

    @Override
    public Boolean updateBuilding(Building building) {
        Building buildingChange = buildingDao.selectById(building.getBuildingId());
        if (buildingChange == null) {
            return false;
        } else {
            buildingChange.setName(building.getName());
            buildingChange.setLocationName(building.getLocationName());
            buildingChange.setOpenTime(building.getOpenTime());
            buildingChange.setCloseTime(building.getCloseTime());
            buildingChange.setIntroduction(building.getIntroduction());
            buildingChange.setNearestStation(building.getNearestStation());
            buildingChange.setVideoUrl(building.getVideoUrl());
            buildingChange.setBuildingType(building.getBuildingType());
            buildingChange.setIsReservable(building.getIsReservable());
            System.out.println(buildingChange);
            buildingDao.updateById(buildingChange);
            return true;
        }
    }

    @Override
    public Boolean uploadRoom(Room room) {
        roomDao.insert(room);
        return true;
    }

    @Override
    public List<RoomInfo> getAllRoom() {
        return roomDao.selectJoinList(
                RoomInfo.class,
                new MPJLambdaWrapper<Room>()
                        .selectAll(Room.class)
                        .select(Building::getName, Building::getBuildingId)
                        .leftJoin(Building.class, Building::getBuildingId, Room::getBuildingId)
                        .leftJoin(RoomType.class, RoomType::getRoomTypeId, Room::getRoomTypeId)
                        .selectAs(RoomType::getType, RoomInfo::getRoomTypeName)
                        .selectAs(RoomType::getCapacity, RoomInfo::getCapacity)
                        .selectAs(RoomType::getDescription, RoomInfo::getDescription)
                        .selectAs(Building::getName, RoomInfo::getBuildingName)
        );
    }

    @Override
    public Boolean deleteBuilding(Integer buildingId) {
        QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buildingID", buildingId);
        Building building = buildingDao.selectOne(queryWrapper);
        if (building == null) {
            return false;
        } else {
            buildingDao.deleteById(buildingId);
            return true;
        }
    }

    @Override
    public Boolean deleteRoom(Integer roomId) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roomID", roomId);
        Room room = roomDao.selectOne(queryWrapper);
        if (room == null) {
            return false;
        } else {
            roomDao.deleteById(roomId);
            return true;
        }
    }

    @Override
    public List<Reservation> getReservationRoomInfo(Integer roomId) {
        return reservationDao.selectJoinList(
                Reservation.class,
                new MPJLambdaWrapper<Reservation>()
                        .selectAll(Reservation.class)
                        .select(User::getName, User::getUserId)
                        .leftJoin(User.class, User::getUserId, Reservation::getUserId)
                        .eq(Reservation::getRoomId, roomId)
        );
    }

    @Override
    public List<ReservationInfo> getAllReservation(){
        List<Reservation> reservations = reservationDao.selectList(null);
        return reservations.stream().map(reservation -> {
            Room room = roomDao.selectById(reservation.getRoomId());
            Building building = buildingDao.selectById(room.getBuildingId());
            RoomType roomType = roomTypeDao.selectById(room.getRoomTypeId());
            List<RoomTypeImage> roomTypeImages = roomTypeImageDao.selectList(
                    new MPJLambdaWrapper<RoomTypeImage>()
                            .select(RoomTypeImage::getImageId)
                            .eq(RoomTypeImage::getRoomTypeId, roomType.getRoomTypeId())
            );
            List<String> image_url = roomTypeImages.stream().map(roomTypeImage -> {
                return imageDao.selectById(roomTypeImage.getImageId()).getImageUrl();
            }).toList();
            return ReservationInfo.builder()
                    .reservationId(reservation.getReservationId())
                    .roomId(reservation.getRoomId())
                    .userId(reservation.getUserId())
                    .startTime(reservation.getStartTime())
                    .endTime(reservation.getEndTime())
                    .description(reservation.getDescription())
                    .roomType(roomType.getType())
                    .buildingId(building.getBuildingId())
                    .buildingName(building.getName())
                    .buildingType(building.getBuildingType())
                    .roomNumber(room.getNumber())
                    .roomTypeImages(image_url)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public AdminInfo login(String username, String password) throws Exception {
        Admin admin = adminDao.selectOne(new MPJLambdaWrapper<>(Admin.class)
                .eq(Admin::getName, username)
        );
        asserts(admin != null, "用户名不存在");
        asserts(admin.getPassword().equals(password), "密码错误");
        String token = authenticate(admin);
        System.out.println("token: " + token);
        return AdminInfo.builder()
                .adminId(admin.getAdminId())
                .name(admin.getName())
                .email(admin.getEmail())
                .phone(admin.getPhone())
                .token(token)
                .build();
    }

    @Override
    public String authenticate(Admin admin) {
        String password = admin.getPassword();
        String id = String.valueOf(admin.getAdminId());
        Authentication authentication = new UsernamePasswordAuthenticationToken(id, password, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        redis.setObject("Admin login:" + id, admin, 60 * 10 * 2); //刷新ttl为20min
        return JwtUtil.createJwt("admin" + id);
    }

    @Override
    public List<ReservationInfo> getReservationUserInfo(Integer userId) {
        asserts(userId != null, "用户id不能为空");
        User user = userDao.selectById(userId);
        asserts(user != null, "用户不存在");
        asserts(blacklistDao.selectOne(new MPJLambdaWrapper<Blacklist>()
                .eq(Blacklist::getUserId, userId)
        ) == null, "用户已被拉黑");

        List<Reservation> reservations = reservationDao.selectList(
                new LambdaQueryWrapper<Reservation>()
                        .eq(Reservation::getUserId, userId)
        );

        return reservations.stream().map(reservation -> {
            Room room = roomDao.selectById(reservation.getRoomId());
            Building building = buildingDao.selectById(room.getBuildingId());
            RoomType roomType = roomTypeDao.selectById(room.getRoomTypeId());
            List<RoomTypeImage> roomTypeImages = roomTypeImageDao.selectList(
                    new MPJLambdaWrapper<RoomTypeImage>()
                            .select(RoomTypeImage::getImageId)
                            .eq(RoomTypeImage::getRoomTypeId, roomType.getRoomTypeId())
            );
            List<String> image_url = roomTypeImages.stream().map(roomTypeImage -> {
                return imageDao.selectById(roomTypeImage.getImageId()).getImageUrl();
            }).toList();
            return ReservationInfo.builder()
                    .reservationId(reservation.getReservationId())
                    .roomId(reservation.getRoomId())
                    .userId(reservation.getUserId())
                    .startTime(reservation.getStartTime())
                    .endTime(reservation.getEndTime())
                    .description(reservation.getDescription())
                    .userName(user.getName())
                    .roomType(roomType.getType())
                    .buildingId(building.getBuildingId())
                    .buildingName(building.getName())
                    .buildingType(building.getBuildingType())
                    .roomNumber(room.getNumber())
                    .roomTypeImages(image_url)
                    .build();
        }).collect(Collectors.toList());
    }



    @Override
    public Boolean register(String username, String password, String email, String phoneNumber, MultipartFile file) throws IOException {
        asserts(username != null && password != null && email != null && phoneNumber != null, "用户名、密码、邮箱、手机号不能为空");
        asserts(username.length() >= 3 && username.length() <= 20, "用户名长度应在3-20之间");
        asserts(password.length() >= 6 && password.length() <= 20, "密码长度应在6-20之间");
        asserts(email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"), "邮箱格式不正确");
        asserts(phoneNumber.matches("^\\d{11}$"), "手机号格式不正确");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, username)) == null, "该用户名已被注册");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, email)) == null, "该邮箱已被注册");
        asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phoneNumber)) == null, "该手机号已被注册");

        String url = imgHostUploader.upload(file);
        Image image = Image.builder()
                .imageUrl(url)
                .build();
        imageDao.insert(image);

        User user = User.builder()
                .name(username)
//                .password(passwordEncoder.encode(password))
                .password(password)
                .email(email)
                .phone(phoneNumber)
                .imageId(image.getImageId())
                .isBlocked(false)
                .build();
        userDao.insert(user);
        return true;
    }

    @Override
    public List<CommentInfo> getAllComments() {
        List<Comment> comments = commentDao.selectList(
                new MPJLambdaWrapper<Comment>()
                        .select(Comment::getCommentId, Comment::getUserId, Comment::getTime, Comment::getText, Comment::getBuildingId, Comment::getScore, Comment::getAdminId)
        );
        return comments.stream().map(comment -> {
            User user = userDao.selectById(comment.getUserId());
            String userImageUrl = null;
            if (imageDao.selectById(user.getImageId()) == null) {
                LOGGER.warn("imageDao.selectById(user.getImageId()) == null");
            } else {
                userImageUrl = imageDao.selectById(user.getImageId()).getImageUrl();
            }
            List<CommentIdImage> commentIdImages = commentIdImageDao.selectList(
                    new MPJLambdaWrapper<CommentIdImage>()
                            .select(CommentIdImage::getImageId)
                            .eq(CommentIdImage::getCommentId, comment.getCommentId())
            );
            List<String> image_url = commentIdImages.stream().map(commentIdImage -> {
                return imageDao.selectById(commentIdImage.getImageId()).getImageUrl();
            }).toList();

            return CommentInfo.builder()
                    .commentId(comment.getCommentId())
                    .userId(comment.getUserId())
                    .time(comment.getTime())
                    .text(comment.getText())
                    .buildingId(comment.getBuildingId())
                    .score(comment.getScore())
                    .username(user.getName())
                    .userImageUrl(userImageUrl)
                    .imageUrl(image_url)
                    .adminId((comment.getAdminId()))
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Boolean approveComment(Integer commentId, Integer adminId) {
        Comment comment = commentDao.selectById(commentId);
        if (comment == null) {
            return false;
        } else {
            comment.setAdminId(adminId);
            commentDao.updateById(comment);
            return true;
        }
    }

    @Override
    public Object getAllBusLine() throws IOException {
        return publicService.getAllBusLine();
    }

    @Override
    public Boolean updateAllBusLine(List<BuslineParam> buslines) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // 将对象转换为JSON字符串
        String json = objectMapper.writeValueAsString(buslines);
        JSONArray jsonArray = new JSONArray(json);
        File file = new File("backend/main/src/main/resources/static/busline/busline.json");
        objectMapper.writeValue(file, jsonArray);
        return true;
    }

    @Override
    public Boolean blockUser(Integer userId) {
        User user = userDao.selectById(userId);
        asserts(user != null, "用户不存在");
        user.setIsBlocked(true);
        userDao.updateById(user);
        return true;
    }

    @Override
    public void unblockUser(Integer userId) {
        User user = userDao.selectById(userId);
        asserts(user != null, "用户不存在");
        user.setIsBlocked(false);
        userDao.updateById(user);
    }

    @Override
    public Boolean batchRegister(List<RegisterParam> registerParams) {
        for (RegisterParam registerParam : registerParams) {
            asserts(registerParam.getUsername() != null && registerParam.getPassword() != null && registerParam.getEmail() != null, "用户名、密码、邮箱不能为空");
            asserts(registerParam.getUsername().length() >= 3 && registerParam.getUsername().length() <= 20, "用户名长度应在3-20之间");
            asserts(registerParam.getPassword().length() >= 6 && registerParam.getPassword().length() <= 20, "密码长度应在6-20之间");
            asserts(registerParam.getEmail().matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"), "邮箱格式不正确");
            asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getName, registerParam.getUsername())) == null, "该用户名已被注册");
            asserts(userDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, registerParam.getEmail())) == null, "该邮箱已被注册");

            User user = User.builder()
                    .name(registerParam.getUsername())
                    .password(passwordEncoder.encode(registerParam.getPassword()))
                    .email(registerParam.getEmail())
                    .imageId(1)
                    .isBlocked(false)
                    .build();
            userDao.insert(user);
        }
        return true;
    }

    @Override
    public List<IllegalLogInfo> getAllIllegal() {
        List<IllegalOperationLog> illegalOperationLogs = illegalOperationLogDao.selectList(null);
        return illegalOperationLogs.stream().map(illegalOperationLog -> {
            User user = userDao.selectById(illegalOperationLog.getUserId());
            return IllegalLogInfo.builder()
                    .username(user.getName())
                    .userId(user.getUserId())
                    .operationTime(illegalOperationLog.getOperationTime())
                    .operation(illegalOperationLog.getOperation())
                    .ipAddress(illegalOperationLog.getIpAddress())
                    .port(illegalOperationLog.getPort())
                    .blocked(user.getIsBlocked())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<LoginLogInfo> getAllLoginLog() {
        List<LoginLog> loginLogs = loginLogDao.selectList(null);
        return loginLogs.stream().map(loginLog -> {
            User user = userDao.selectById(loginLog.getUserId());
            return LoginLogInfo.builder()
                    .userId(user.getUserId())
                    .username(user.getName())
                    .email(user.getEmail())
                    .ipAddress(loginLog.getIpAddress())
                    .loginTime(loginLog.getLoginTime())
                    .port(loginLog.getPort())
                    .blocked(user.getIsBlocked())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<BuildingStatisticsInfo> getBuildingStatistics() {
        List<Building> buildings = buildingDao.selectList(null);
        return buildings.stream().map(building -> {
            List<Room> rooms = roomDao.selectList(
                    new LambdaQueryWrapper<Room>()
                            .eq(Room::getBuildingId, building.getBuildingId())
            );

            Integer totalLike = 0;
            Integer totalReserve = 0;

            for (Room room: rooms){
                RoomStatisticsInfo roomStatisticsInfo = getOneRoomStatistics(room.getRoomId());
                totalLike += roomStatisticsInfo.getTotalLike();
                totalReserve += roomStatisticsInfo.getTotalReserve();
            }

            return BuildingStatisticsInfo.builder()
                    .buildingId(building.getBuildingId())
                    .buildingName(building.getName())
                    .totalRoom(rooms.size())
                    .totalReserve(totalReserve)
                    .totalLike(totalLike)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<RoomStatisticsInfo> getRoomStatistics() {
        List<Room> rooms = roomDao.selectList(null);
        return rooms.stream().map(room -> {
            return getOneRoomStatistics(room.getRoomId());
        }).collect(Collectors.toList());
    }

    private RoomStatisticsInfo getOneRoomStatistics(Integer roomId) {
        Room room = roomDao.selectById(roomId);
        List<Reservation> reservations = reservationDao.selectList(
                new MPJLambdaWrapper<>(Reservation.class)
                        .eq(Reservation::getRoomId, roomId)
        );
        Integer totalReserve = reservations.size();
        Integer totalLike = 0;
        List<Comment> comments = commentDao.selectList(
                new MPJLambdaWrapper<>(Comment.class)
                        .eq(Comment::getBuildingId, room.getBuildingId())
        );
        for (Comment comment : comments) {
            totalLike += comment.getScore();
        }
        String buildingName = buildingDao.selectById(room.getBuildingId()).getName();
        return RoomStatisticsInfo.builder()
                .roomId(roomId)
                .roomName(buildingName + room.getNumber())
                .totalLike(totalLike)
                .totalReserve(totalReserve)
                .build();
    }
}
