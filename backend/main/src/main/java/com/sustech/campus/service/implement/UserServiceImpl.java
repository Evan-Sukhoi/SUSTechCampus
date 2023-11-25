package com.sustech.campus.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sustech.campus.database.dao.*;
import com.sustech.campus.database.po.*;
import com.sustech.campus.service.UserService;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Collections;
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
    private bus_lineDao bus_lineDao;

    @Override
    public Boolean uploadComment(Integer commentId, Integer userId, Date time, String text, Integer buildingId) {
        Comment comment = Comment.builder()
                .comment_id(commentId)
                .user_id(userId)
                .time(time)
                .text(text)
                .building_id(buildingId)
                .score(0.0)
                .admin_id(-1)
                .build();
        if (commentDao.insert(comment) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public List<Building> getAllBuilding() {
        return buildingDao.selectJoinList(
                Building.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuilding_id, Building::getName, Building::getOpen_time, Building::getClose_time, Building::getLocation_name, Building::getIntroduction, Building::getNearest_station, Building::getVideo_url, Building::getCover_id)
        );
    }
    @Override
    public List<Building> getBuildingById(Integer buildingId) {
        return buildingDao.selectJoinList(
                Building.class,
                new MPJLambdaWrapper<Building>()
                        .select(Building::getBuilding_id, Building::getName, Building::getOpen_time, Building::getClose_time, Building::getLocation_name, Building::getIntroduction, Building::getNearest_station, Building::getVideo_url, Building::getCover_id)
                        .eq(Building::getBuilding_id, buildingId)
        );
    }

    @Override
    public List<Comment> getCommentByBuildingId(Integer buildingId) {
        return commentDao.selectJoinList(
                Comment.class,
                new MPJLambdaWrapper<Comment>()
                        .select(Comment::getComment_id, Comment::getUser_id, Comment::getTime, Comment::getText, Comment::getBuilding_id, Comment::getScore, Comment::getAdmin_id)
                        .eq(Building::getBuilding_id, buildingId)
        );
    }

    @Override
    public List<Comment> getAllComment() {
        return commentDao.selectJoinList(
                Comment.class,
                new MPJLambdaWrapper<Comment>()
                        .select(Comment::getComment_id, Comment::getUser_id, Comment::getTime, Comment::getText, Comment::getBuilding_id, Comment::getScore, Comment::getAdmin_id)
        );
    }

    @Override
    public List<Bus_line> getAllBusLine() {
        return bus_lineDao.selectJoinList(
                Bus_line.class,
                new MPJLambdaWrapper<Bus_line>()
                        .select(Bus_line::getBus_line_ID, Bus_line::getLine_ID, Bus_line::getStation, Bus_line::get_index_)
        );
    }

    @Override
    public List<Room> getAllRoom() {
        return roomDao.selectJoinList(
                Room.class,
                new MPJLambdaWrapper<Room>()
                        .select(Room::getRoom_id, Room::getBuilding_id, Room::getNumber, Room::getRoom_type_id)
        );
    }

    @Override
    public List<Room> getRoomByBuildingId(Integer buildingId) {
        return roomDao.selectJoinList(
                Room.class,
                new MPJLambdaWrapper<Room>()
                        .select(Room::getRoom_id, Room::getBuilding_id, Room::getNumber, Room::getRoom_type_id)
                        .eq(Room::getBuilding_id, buildingId)
        );
    }

    @Override
    public List<Room> getRoomByType(String type) {
        // 首先，通过房间类型名称从 room_type 表中获取对应的 room_type_ID
        RoomType roomType = roomTypeDao.selectOne(
                new QueryWrapper<RoomType>().eq("type", type)
        );

        if (roomType == null) {
            // 如果找不到匹配的房间类型，返回空列表或者抛出异常，根据你的需求来处理
            return Collections.emptyList();
        }

        // 使用 MPJLambdaWrapper 连接两个表并筛选房间类型匹配的记录
        return roomDao.selectJoinList(
                Room.class,
                new MPJLambdaWrapper<Room>()
                        .select(Room::getRoom_id, Room::getBuilding_id, Room::getNumber, Room::getRoom_type_id)
                        .eq(Room::getRoom_type_id, roomType.getRoom_type_ID())
        );
    }

    @Override
    public Room getRoomByRoomId(Integer roomId) {
        return roomDao.selectJoinOne(
                Room.class,
                new MPJLambdaWrapper<Room>()
                        .select(Room::getRoom_id, Room::getBuilding_id, Room::getNumber, Room::getRoom_type_id)
                        .eq(Room::getRoom_id, roomId)
        );
    }

    @Override
    public Boolean uploadReservation(Integer userId, Integer roomId, Date startTime, Date endTime) {
        asserts(startTime.before(endTime), "开始时间必须早于结束时间");
        asserts(startTime.after(new Date()), "开始时间必须晚于当前时间");


        Reservation reservation = Reservation.builder()
                .room_id(roomId)
                .start_time(startTime)
                .end_time(endTime)
                .user_id(userId)
                .build();
        return reservationDao.insert(reservation) != 0;
    }
}
