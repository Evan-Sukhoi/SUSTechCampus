package com.sustech.campus.service.implement;

import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.dao.*;
import com.sustech.campus.database.po.*;
import com.sustech.campus.database.utils.ImgHostUploader;
import com.sustech.campus.model.param.BuslineParam;
import com.sustech.campus.model.param.RegisterParam;
import com.sustech.campus.model.vo.*;
import com.sustech.campus.service.AdminService;

import com.sustech.campus.service.PublicService;
import com.sustech.campus.service.UserService;
import jakarta.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sustech.campus.web.utils.ExceptionUtils.asserts;

@Service
public class AdminServiceImpl implements AdminService {

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
    private IllegalOperationLogDao illegalOperationLogDao;
    @Resource
    private PublicService publicService;
//    @Resource
//    private UserService userService;
    @Resource
    private ImgHostUploader imgHostUploader;

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

    // TODO: 图片处理逻辑
    @Override
    public String uploadRoomTypeCover(MultipartFile picture, Integer roomId) {
        return null;
    }

    @Override
    public String uploadRoomTypeMedia(MultipartFile media, Integer roomId) {
        return null;
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
                    .password(registerParam.getPassword())
                    .email(registerParam.getEmail())
                    .imageId(1)
                    .build();
            userDao.insert(user);
        }
        return true;
    }

    @Override
    public List<IllegalOperationLog> getAllIllegal() {
        return illegalOperationLogDao.selectList(null);
    }
}
