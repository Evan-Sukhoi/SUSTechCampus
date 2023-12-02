package com.sustech.campus.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.toolkit.Asserts;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.dao.*;
import com.sustech.campus.database.po.*;
import com.sustech.campus.database.utils.ImgHostUploader;
import com.sustech.campus.model.vo.BuildingInfo;
import com.sustech.campus.model.vo.CommentInfo;
import com.sustech.campus.model.vo.ReservationInfo;
import com.sustech.campus.model.vo.RoomInfo;
import com.sustech.campus.service.AdminService;

import com.sustech.campus.service.PublicService;
import jakarta.annotation.Resource;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.sustech.campus.web.utils.ExceptionUtils.asserts;

@Service
public class AdminServiceImpl implements AdminService {

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
    private UserDao usersDao;

    @Resource
    private ReservationDao reservationDao;

    @Resource
    private BlacklistDao blacklistDao;
    @Resource
    private PublicService publicService;

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ImgHostUploader.class);

    @Override
    public User getUserInfo(Integer userId) {
        return usersDao.selectById(userId);
    }

    @Override
    public Boolean updateUserInfo(Integer userId, String name, String phone, String email, String password) {
        User user = usersDao.selectById(userId);
        if (user == null) {
            return false;
        } else {
            user.setName(name);
            user.setPhone(phone);
            user.setEmail(email);
            user.setPassword(password);
            usersDao.updateById(user);
            return true;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return usersDao.selectList(null);
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        User user = usersDao.selectById(userId);
        if (user == null) {
            return false;
        } else {
            usersDao.deleteById(userId);
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
        User user = usersDao.selectById(userId);
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
                    .buildingName(building.getName())
                    .buildingType(building.getBuildingType())
                    .roomTypeImages(image_url)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<CommentInfo> getAllComments() {
        List<Comment> comments = commentDao.selectList(
                new MPJLambdaWrapper<Comment>()
                        .select(Comment::getCommentId, Comment::getUserId, Comment::getTime, Comment::getText, Comment::getBuildingId, Comment::getScore, Comment::getAdminId)
        );
        return comments.stream().map(comment -> {
            User user = usersDao.selectById(comment.getUserId());
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



}
