package com.sustech.campus.controller;

import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Comment;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.database.po.Bus_line;
import com.sustech.campus.model.param.*;
import com.sustech.campus.service.UserService;
import com.sustech.campus.utils.ApiResponse;
import com.sustech.campus.web.annotation.MappingController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@MappingController("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户发起预约请求")
    @RequestMapping("/reservation/apply")
    public ApiResponse<Boolean> uploadReservation(@RequestBody @Validated ReserveParam reserveParam) {
        return ApiResponse.success(
                userService.uploadReservation(
                        reserveParam.getUserId(),
                        reserveParam.getRoomId(),
                        reserveParam.getStartTime(),
                        reserveParam.getEndTime()
                )
        );
    }

    @ApiOperation("用户查看一个房间的预约请求")
    @RequestMapping("/reservation/roominfo")
    public ApiResponse<Boolean> getRoomReservation(@RequestBody @Validated ReserveRoomInfoParam reserveRoomInfoParam) {
        return ApiResponse.success(
                userService.getReservationRoomInfo(
                        reserveRoomInfoParam.getBuildingType(),
                        reserveRoomInfoParam.getBuildingName(),
                        reserveRoomInfoParam.getRoomId()
                )
        );
    }

    @ApiOperation("用户查看一个用户的预约请求")
    @RequestMapping("/reservation/userinfo")
    public ApiResponse<Boolean> getUserReservation(@RequestBody @Validated ReserveUserInfoParam reserveUserInfoParam) {
        return ApiResponse.success(
                userService.getReservationUserInfo(
                        reserveUserInfoParam.getUserID()
                )
        );
    }

    @ApiOperation("用户修改一个预约请求")
    @RequestMapping("/reservation/update")
    public ApiResponse<Boolean> updateReservation(@RequestBody @Validated ReserveUpdateParam reserveUpdateParam) {
        return ApiResponse.success(
                userService.updateReservation(
                        reserveUpdateParam.getReservationID(),
                        reserveUpdateParam.getRoomId(),
                        reserveUpdateParam.getDepartment(),
                        reserveUpdateParam.getStartTime(),
                        reserveUpdateParam.getEndTime(),
                        reserveUpdateParam.getDate(),
                        reserveUpdateParam.getBuildingType(),
                        reserveUpdateParam.getBuildingName()
                )
        );
    }

    @ApiOperation("用户根据地点获取巴士线路信息")
    @RequestMapping("/busline/getbylocation")
    public ApiResponse<Boolean> getBusLine(@RequestBody @Validated BusLineLocationParam busLineLocationParam) {
        return ApiResponse.success(
                userService.getBusLineByLocation(
                        busLineLocationParam.getLocation()
                )
        );
    }


//    -------------------------新旧分割线-------------------------

    @ApiOperation("用户上传一条评论")
    @RequestMapping("/comment/upload")
    public ApiResponse<Boolean> uploadComment(@ApiParam("评论id") Integer commentId,
                                              @ApiParam("用户id") Integer userId,
                                              @ApiParam("时间") Date time,
                                              @ApiParam("评论内容") String text,
                                              @ApiParam("建筑id") Integer buildingId) {
        return ApiResponse.success(
                userService.uploadComment(commentId, userId, time, text, buildingId));
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

    @ApiOperation("获取指定id的建筑信息")
    @RequestMapping("/building/getbybuildingid")
    public List<Building> getBuildingByBuilding(@ApiParam("建筑id") Integer buildingId) {
        return userService.getBuildingById(buildingId);
    }

    @ApiOperation("获取所有评论信息")
    @RequestMapping("/comment/all")
    public List<Comment> getAllComment() {
        return userService.getAllComment();
    }

    @ApiOperation("通过建筑id获取评论信息")
    @RequestMapping("/comment/getbybuildingid")
    public List<Comment> getCommentByBuildingId(@ApiParam("建筑id") Integer buildingId) {
        return userService.getCommentByBuildingId(buildingId);
    }

    @ApiOperation("获取所有公交线路信息")
    @RequestMapping("/bus_line/all")
    public List<Bus_line> getAllBusLine() {
        return userService.getAllBusLine();
    }
}
