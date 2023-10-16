package com.sustech.campus.controller;

import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Comment;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.database.po.bus_line;
import com.sustech.campus.service.UserService;
import com.sustech.campus.utils.ApiResponse;
import com.sustech.campus.web.annotation.MappingController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Time;
import java.util.List;

@MappingController("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户上传一条评论")
    @RequestMapping("/comment/upload")
    public ApiResponse<Boolean> uploadComment(@ApiParam("评论id") Integer commentId,
                                 @ApiParam("用户id") Integer userId,
                                 @ApiParam("时间") Time time,
                                 @ApiParam("评论内容") String text,
                                 @ApiParam("建筑id") Integer buildingId) {
        return ApiResponse.success(
                userService.uploadComment(commentId, userId, time, text, buildingId));
    }

    @ApiOperation("用户上传一条预约")
    @RequestMapping("/reservation/upload")
    public ApiResponse<Boolean> uploadReservation(@ApiParam("预约id") Integer reservationId,
                                 @ApiParam("房间id") Integer roomId,
                                 @ApiParam("开始时间") Time startTime,
                                 @ApiParam("结束时间") Time endTime,
                                 @ApiParam("用户id") Integer userId) {
        return ApiResponse.success(
                userService.uploadReservation(reservationId, roomId, startTime, endTime, userId));
    }

    @ApiOperation("通过建筑id获取房间信息")
    @RequestMapping("/room/getbybuildingid")
    public List<Room> getRoomByBuildingId(@ApiParam("建筑id") Integer buildingId) {
        return userService.getRoomByBuildingId(buildingId);
    }

    @ApiOperation("通过房间类型获取房间信息")
    @RequestMapping("/room/getbyroomtype")
    public List<Room> getRoomByRoomType(@ApiParam("房间类型") String roomType) {
        return userService.getRoomByType(roomType);
    }

    @ApiOperation("通过房间id获取房间信息")
    @RequestMapping("/room/getbyroomid")
    public Room getRoomByRoomId(@ApiParam("房间id") Integer roomId) {
        return userService.getRoomByRoomId(roomId);
    }

    @ApiOperation("获取所有房间信息")
    @RequestMapping("/room/all")
    public List<Room> getAllRoom() {
        return userService.getAllRoom();
    }

    @ApiOperation("获取所有建筑信息")
    @RequestMapping("/building/all")
    public List<Building> getAllBuilding() {
        return userService.getAllBuilding();
    }

    @ApiOperation("获取所有评论信息")
    @RequestMapping("/comment/all")
    public List<Comment> getAllComment() {
        return userService.getAllComment();
    }

    @ApiOperation("获取所有公交线路信息")
    @RequestMapping("/bus_line/all")
    public List<bus_line> getAllBusLine() {
        return userService.getAllBusLine();
    }
}
