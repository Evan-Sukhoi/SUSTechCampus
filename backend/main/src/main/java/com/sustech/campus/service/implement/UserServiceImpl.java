package com.sustech.campus.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.annotation.TimeField;
import com.sustech.campus.database.dao.*;
import com.sustech.campus.database.po.*;
import com.sustech.campus.database.utils.ImgHostUploader;
import com.sustech.campus.database.utils.RedisUtil;
import com.sustech.campus.model.vo.AvailableReservationInfo;
import com.sustech.campus.model.vo.ReservationInfo;
import com.sustech.campus.model.vo.RoomInfo;
import com.sustech.campus.model.vo.RoomsInfo;
import com.sustech.campus.service.UserService;
import com.sustech.campus.utils.TimeUtil;
import com.sustech.campus.web.handler.ApiException;
import com.sustech.campus.web.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.sustech.campus.web.utils.ExceptionUtils.*;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private BuildingDao buildingDao;
    @Resource
    private RoomDao roomDao;
    @Resource
    private RoomTypeDao roomTypeDao;
    @Resource
    private RoomTypeImageDao roomTypeImageDao;
    @Resource
    private CommentDao commentDao;
    @Resource
    private ReservationDao reservationDao;
    @Resource
    private ImageDao imageDao;
    @Resource
    private CommentIdImageDao commentIdImageDao;
    @Resource
    private IllegalOperationLogDao illegalOperationLogDao;
    @Resource
    private BuslineDao buslineDao;
    @Autowired
    private ImgHostUploader imgHostUploader;
    @Resource
    private RedisUtil redis;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ImgHostUploader.class);


    @Override
    public Boolean uploadComment(Integer userId, Date time, String text, Integer buildingId, List<MultipartFile> commentPhotos) throws IOException {
        Comment comment = Comment.builder()
                .userId(userId)
                .time(time)
                .text(text)
                .buildingId(buildingId)
                .score(0)
                .adminId(0)
                .build();
        System.out.println(comment);
        if (commentDao.insert(comment) == 0) {
            return false;
        }
        if (commentPhotos == null) {
            return true;
        }
        for (MultipartFile file : commentPhotos) {
            String url = imgHostUploader.upload(file);
            Image image = Image.builder()
                    .imageUrl(url)
                    .build();
            imageDao.insert(image);
            CommentIdImage commentIdImage = CommentIdImage.builder()
                    .commentId(comment.getCommentId())
                    .imageId(image.getImageId())
                    .build();
            commentIdImageDao.insert(commentIdImage);
        }
        return true;
    }

    @Override
    public List<Building> getBuildingById(Integer buildingId) {
        return buildingDao.selectJoinList(
                Building.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuildingId, Building::getName, Building::getOpenTime, Building::getCloseTime, Building::getLocationName, Building::getIntroduction, Building::getNearestStation, Building::getVideoUrl, Building::getCoverId)
                        .eq(Building::getBuildingId, buildingId)
        );
    }

    @Override
    public List<RoomInfo> getRoomByBuilding(Integer buildingId) {
        return roomDao.selectJoinList(
                RoomInfo.class,
                new MPJLambdaWrapper<Room>()
                        .selectAll(Room.class)
                        .eq(Room::getBuildingId, buildingId)
                        .select(Building::getName, Building::getBuildingId)
                        .leftJoin(RoomType.class, RoomType::getRoomTypeId, Room::getRoomTypeId)
                        .leftJoin(Building.class, Building::getBuildingId, Room::getBuildingId)
                        .selectAs(RoomType::getType, RoomInfo::getRoomTypeName)
                        .selectAs(RoomType::getCapacity, RoomInfo::getCapacity)
                        .selectAs(RoomType::getDescription, RoomInfo::getDescription)
                        .selectAs(Building::getName, RoomInfo::getBuildingName)
        );
    }

    @Override
    public RoomType getRoomTypeById(Integer roomTypeId) {
        return roomTypeDao.selectOne(
                new QueryWrapper<RoomType>().eq("roomTypeID", roomTypeId)
        );
    }

    @Override
    public Room getRoomByRoomId(Integer roomId) {
        return roomDao.selectJoinOne(
                Room.class,
                new MPJLambdaWrapper<Room>()
                        .select(Room::getRoomId, Room::getBuildingId, Room::getNumber, Room::getRoomTypeId)
                        .eq(Room::getRoomId, roomId)
        );
    }

    @Override
    public List<RoomsInfo> getBuildingRoom(Integer buildingId){
        List<Room> rooms = roomDao.selectList(
                new LambdaQueryWrapper<Room>()
                        .eq(Room::getBuildingId, buildingId)
        );
        List<RoomType> roomTypes = new ArrayList<>();
        for (Room room : rooms) {
            RoomType roomType = roomTypeDao.selectById(room.getRoomTypeId());
            if (!roomTypes.contains(roomType)) {
                roomTypes.add(roomType);
            }
        }
        return roomTypes.stream().map(roomType -> {
            List<String> imageUrls = roomTypeImageDao.selectList(
                    new MPJLambdaWrapper<RoomTypeImage>()
                            .select(RoomTypeImage::getImageId)
                            .eq(RoomTypeImage::getRoomTypeId, roomType.getRoomTypeId())
            ).stream().map(roomTypeImage -> {
                return imageDao.selectById(roomTypeImage.getImageId()).getImageUrl();
            }).toList();
            return RoomsInfo.builder()
                    .roomTypeId(roomType.getRoomTypeId())
                    .roomTypeName(roomType.getType())
                    .capacity(roomType.getCapacity())
                    .description(roomType.getDescription())
                    .roomImageUrls(imageUrls)
                    .roomNumbers(rooms.stream().filter(room -> room.getRoomTypeId().equals(roomType.getRoomTypeId())).map(Room::getNumber).toList())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<ReservationInfo> getAllReservation(Integer userId) {
        Integer currentUserId = getCurrentUserId();
        User user = redis.getObject("login:" + currentUserId);
        warns(userId.equals(currentUserId),
                "非法操作：查询的用户与你的信息不符！你的用户ID为：" + currentUserId +
                "，要查询的用户ID为：" + userId +
                "你的行为已被记录，请立即停止非法操作并联系管理员说明情况，否则可能会被封禁账号。",
                "非法查询：ID为" + userId + "的预约信息",
                user, illegalOperationLogDao);

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
            List<String> image_url = roomTypeImages.stream().map(roomTypeImage -> imageDao.selectById(roomTypeImage.getImageId()).getImageUrl()).toList();
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
    public Boolean uploadReservation(Integer userId, Integer roomId, Date startTime, Date endTime, String description) {
        asserts(startTime.before(endTime), "开始时间必须早于结束时间");
        asserts(startTime.after(new Date()), "开始时间必须晚于当前时间");
        asserts(description != null, "预约描述不能为空");

        Reservation reservation = Reservation.builder()
                .roomId(roomId)
                .startTime(startTime)
                .endTime(endTime)
                .userId(userId)
                .description(description)
                .build();
        return reservationDao.insert(reservation) != 0;
    }

    @Override
    public Boolean updateReservation(Long reservationId, Integer roomId, Date startTime, Date endTime, Integer userId, String description) {
        asserts(startTime.before(endTime), "开始时间必须早于结束时间");
        asserts(startTime.after(new Date()), "开始时间必须晚于当前时间");
        asserts(description != null, "预约描述不能为空");

        Reservation reservation = reservationDao.selectById(reservationId);

        asserts(reservation != null, "预约不存在");

        Integer currentUserId = getCurrentUserId();
        User user = redis.getObject("login:" + currentUserId);

        warns(reservation.getUserId().equals(user.getUserId()),
                "非法操作：该预约不属于该用户！你的用户ID为：" + user.getUserId() +
                "，该预约的用户ID为：" + reservation.getUserId() +
                        "你的行为已被记录，请立即停止非法操作并联系管理员说明情况，否则可能会被封禁账号。",
                "非法修改：ID为" + reservation.getReservationId() + "的预约信息",
                user, illegalOperationLogDao);
        reservation.setRoomId(roomId);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setDescription(description);
        return reservationDao.updateById(reservation) != 0;
    }

    @Override
    public List<AvailableReservationInfo> getAvailableReservation(Integer buildingId, Date date) {

        Date startDate = DateUtils.truncate(date, Calendar.DATE); // 获取参数date的当天日期，清除时分秒部分
        Date endDate = DateUtils.addDays(startDate, 1);

        Building building = buildingDao.selectById(buildingId);
        Date begin_time = building.getOpenTime();
        Date end_time = building.getCloseTime();
        List<Room> rooms = roomDao.selectList(
                new MPJLambdaWrapper<>(Room.class)
                        .eq(Room::getBuildingId, buildingId)
        );
        if (rooms.isEmpty()) {
            LOGGER.warn("该建筑没有教室");
            throw new ApiException(401, "该建筑没有教室");
        }
        return rooms.stream().map(room -> {
            List<Reservation> reservations = reservationDao.selectList(
                    new MPJLambdaWrapper<>(Reservation.class)
                            .eq(Reservation::getRoomId, room.getRoomId())
                            .between(Reservation::getStartTime, startDate, endDate)
            );
            List<Date> occupiedTimeBeginImmutable = reservations.stream().map(Reservation::getStartTime).toList();
            List<Date> occupiedTimeEndImmutable = reservations.stream().map(Reservation::getEndTime).toList();
            ArrayList<Date> occupiedTimeBegin = new ArrayList<>(occupiedTimeBeginImmutable);
            ArrayList<Date> occupiedTimeEnd = new ArrayList<>(occupiedTimeEndImmutable);
            occupiedTimeBegin.sort(Date::compareTo);
            occupiedTimeEnd.sort(Date::compareTo);

            List<Date> availableTimeBegin = new ArrayList<>();
            availableTimeBegin.add(begin_time);
            List<Date> availableTimeEnd = new ArrayList<>();
            for (int i = 0; i < occupiedTimeBegin.size() - 1; i++) {
                availableTimeEnd.add(occupiedTimeBegin.get(i));
                availableTimeBegin.add(occupiedTimeEnd.get(i));
            }
            availableTimeEnd.add(end_time);

            return AvailableReservationInfo.builder()
                    .roomId(room.getRoomId())
                    .roomNumber(room.getNumber())
                    .roomTypeId(room.getRoomTypeId())
                    .roomType(roomTypeDao.selectById(room.getRoomTypeId()).getType())
                    .availableTimeBegin(availableTimeBegin)
                    .availableTimeEnd(availableTimeEnd)
                    .build();
        }).toList();
    }

    private Integer getCurrentUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        System.out.println(request.getRequestURL() + "token: " + token);
        String id = null;
        if (StringUtils.hasText(token)) {
            Claims claims = JwtUtil.parseJwt(token);
            id = claims.getSubject();
        }
        asserts(id != null, "认证信息无效或已过期，请重新登录");
        return Integer.parseInt(id);
    }
}
