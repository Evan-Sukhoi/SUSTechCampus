package com.sustech.campus.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.annotation.TimeField;
import com.sustech.campus.database.dao.*;
import com.sustech.campus.database.po.*;
import com.sustech.campus.database.utils.ImgHostUploader;
import com.sustech.campus.model.vo.AvailableReservationInfo;
import com.sustech.campus.service.UserService;
import com.sustech.campus.utils.TimeUtil;
import com.sustech.campus.web.handler.ApiException;
import jakarta.annotation.Resource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sustech.campus.web.utils.ExceptionUtils.asserts;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private BuildingDao buildingDao;
    @Resource
    private RoomDao roomDao;
    @Resource
    private RoomTypeDao roomTypeDao;
    @Resource
    private CommentDao commentDao;
    @Resource
    private ReservationDao reservationDao;
    @Resource
    private ImageDao imageDao;
    @Resource
    private CommentIdImageDao commentIdImageDao;
    @Resource
    private BuslineDao buslineDao;
    @Autowired
    private ImgHostUploader imgHostUploader;
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
        if (commentDao.insert(comment) == 0) {
            return false;
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
    public List<Comment> getCommentByBuildingId(Integer buildingId) {
        return commentDao.selectJoinList(
                Comment.class,
                new MPJLambdaWrapper<Comment>()
                        .select(Comment::getCommentId, Comment::getUserId, Comment::getTime, Comment::getText, Comment::getBuildingId, Comment::getScore, Comment::getAdminId)
                        .eq(Building::getBuildingId, buildingId)
        );
    }


    @Override
    public List<Room> getRoomByBuilding(Integer buildingId) {
        return roomDao.selectJoinList(
                Room.class,
                new MPJLambdaWrapper<Room>()
                        .select(Room::getRoomId, Room::getBuildingId, Room::getNumber, Room::getRoomTypeId)
                        .eq(Room::getBuildingId, buildingId)
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
    public Boolean uploadReservation(Integer userId, Integer roomId, Date startTime, Date endTime) {
        asserts(startTime.before(endTime), "开始时间必须早于结束时间");
        asserts(startTime.after(new Date()), "开始时间必须晚于当前时间");


        Reservation reservation = Reservation.builder()
                .roomId(roomId)
                .startTime(startTime)
                .endTime(endTime)
                .userId(userId)
                .build();
        return reservationDao.insert(reservation) != 0;
    }

    @Override
    public Boolean updateReservation(Long reservationId, Integer roomId, Date startTime, Date endTime, Integer userId) {
        return null;
    }

    @Override
    public List<AvailableReservationInfo> getReservation(Integer buildingId) {
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
}
