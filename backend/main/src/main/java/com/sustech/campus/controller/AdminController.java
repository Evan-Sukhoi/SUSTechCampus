package com.sustech.campus.controller;

import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Reservation;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.database.po.User;
import com.sustech.campus.model.param.*;
import com.sustech.campus.model.vo.BuildingInfo;
import com.sustech.campus.model.vo.CommentInfo;
import com.sustech.campus.model.vo.ReservationInfo;
import com.sustech.campus.model.vo.RoomInfo;
import com.sustech.campus.service.AdminService;
import com.sustech.campus.utils.ApiResponse;
import com.sustech.campus.web.annotation.MappingController;

import com.sustech.campus.web.handler.ApiException;
import jakarta.annotation.Resource;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@MappingController("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @ApiOperation("管理员登录")
    @RequestMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Validated AdminLoginParam adminLoginParam) {
        try {
            return ResponseEntity.ok(adminService.login(
                    adminLoginParam.getUsername(),
                    adminLoginParam.getPassword()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("管理员获取所有用户")
    @RequestMapping("/user/all")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @ApiOperation("管理员批量注册用户")
    @PostMapping("/user/batchRegister")
    public ResponseEntity<Object> batchRegister(@RequestBody @Validated List<RegisterParam> registerParams) {
        try {
            return ResponseEntity.ok(adminService.batchRegister(registerParams));
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("管理员获取某个用户的所有信息")
    @RequestMapping("/user/info")
    public User getUserInfo(@ApiParam("用户id") @RequestParam @NotNull Integer userId) {
        return adminService.getUserInfo(userId);
    }

    @ApiOperation("管理员更新某个用户的信息")
    @PostMapping("/user/update")
    public ApiResponse<Boolean> updateUserInfo(HttpServletRequest request) {
        System.out.println("updating User Info");
        System.out.println(request.getParameter("userId"));
        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("phone"));
        System.out.println(request.getParameter("email"));
        System.out.println(request.getParameter("password"));
        return ApiResponse.success(
                adminService.updateUserInfo(
                        Integer.parseInt(request.getParameter("userId")),
                        request.getParameter("name"),
                        request.getParameter("phone"),
                        request.getParameter("email"),
                        request.getParameter("password")));
    }

    @ApiOperation("管理员拉黑某个用户")
    @PostMapping("/user/block")
    public ResponseEntity<Object> blockUser(@ApiParam("用户id") @RequestParam @NotNull Integer userId) {
        System.out.println("blocking User: " + userId);
        try {
            adminService.blockUser(userId);
            return ResponseEntity.ok().build();
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("管理员解除拉黑某个用户")
    @PostMapping("/user/unblock")
    public ResponseEntity<Object> unblockUser(@ApiParam("用户id") @RequestParam @NotNull Integer userId) {
        System.out.println("unblocking User: " + userId);
        try {
            adminService.unblockUser(userId);
            return ResponseEntity.ok().build();
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("管理员删除一个用户")
    @PostMapping("/user/delete")
    public ApiResponse<Boolean> deleteUser(HttpServletRequest request) {
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        System.out.println("deleting User: " + userId);
        return ApiResponse.success(
                adminService.deleteUser(userId));
    }

    @ApiOperation("管理员获取所有建筑")
    @RequestMapping("/building/all")
    public ResponseEntity<Object> getAllBuilding() {
        try {
            return ResponseEntity.ok(adminService.getAllBuilding());
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("管理员新建一个建筑")
    @PostMapping("/building/add")
    public ApiResponse<Boolean> addBuilding(
            @ApiParam("建筑名称") @RequestParam @NotEmpty String name,
            @ApiParam("建筑简介") @RequestParam @NotEmpty String introduction,
            @ApiParam("开放时间") @RequestParam @NotNull Time openTime,
            @ApiParam("关闭时间") @RequestParam @NotNull Time closeTime,
            @ApiParam("位置") @RequestParam @NotEmpty String location_name,
            @ApiParam("附近车站") @RequestParam @NotEmpty String nearest_station
    ) {
        return ApiResponse.success(
                adminService.uploadBuilding(Building.builder()
                        .name(name)
                        .introduction(introduction)
                        .openTime(openTime)
                        .closeTime(closeTime)
                        .locationName(location_name)
                        .nearestStation(nearest_station)
                        .build()));
    }

    @ApiOperation("管理员修改一个建筑")
    @PostMapping("/building/change")
    public ApiResponse<Boolean> addBuilding(
            @ApiParam("建筑id") @RequestParam @NotEmpty Integer buildingId,
            @ApiParam("建筑名称") @RequestParam @NotEmpty String name,
            @ApiParam("建筑简介") @RequestParam @NotEmpty String introduction,
            @ApiParam("开放时间") @RequestParam @NotNull Time openTime,
            @ApiParam("关闭时间") @RequestParam @NotNull Time closeTime,
            @ApiParam("位置") @RequestParam @NotEmpty String location_name,
            @ApiParam("附近车站") @RequestParam @NotEmpty String nearest_station,
            @ApiParam("视频链接") @RequestParam @NotEmpty String videoUrl
    ) {
        return ApiResponse.success(
                adminService.updateBuilding(Building.builder()
                        .buildingId(buildingId)
                        .name(name)
                        .introduction(introduction)
                        .openTime(openTime)
                        .closeTime(closeTime)
                        .locationName(location_name)
                        .nearestStation(nearest_station)
                        .videoUrl(videoUrl)
                        .build()));
    }



    @ApiOperation("管理员删除一个建筑")
    @PostMapping("/building/delete")
    public ApiResponse<Boolean> deleteBuilding(@ApiParam("建筑id") @RequestParam @NotNull Integer buildingId) {
        return ApiResponse.success(
                adminService.deleteBuilding(buildingId));
    }

    @ApiOperation("管理员获取所有教室")
    @RequestMapping("/room/get/all")
    public List<RoomInfo> getAllRoom() {
        return adminService.getAllRoom();
    }

    @ApiOperation("管理员新建一个教室")
    @PostMapping("/room/add")
    public ApiResponse<Boolean> addRoom(@ApiParam("所属建筑id") @RequestParam @NotNull Integer buildingId,
                                        @ApiParam("教室号") @RequestParam @NotNull Integer number,
                                        @ApiParam("教室类型id") @RequestParam @NotNull Integer roomTypeId
    ) {
        return ApiResponse.success(
                adminService.uploadRoom(Room.builder()
                        .buildingId(buildingId)
                        .number(number)
                        .roomTypeId(roomTypeId)
                        .build()));
    }

    @ApiOperation("管理员删除一个教室")
    @PostMapping("/room/delete")
    public ApiResponse<Boolean> deleteRoom(@ApiParam("教室id") @RequestParam @NotNull Integer roomId) {
        return ApiResponse.success(
                adminService.deleteRoom(roomId));
    }

    @ApiOperation("管理员查看一个房间的预约请求")
    @RequestMapping("/reservation/room")
    public ApiResponse<List<Reservation>> getRoomReservation(@RequestBody @Validated ReserveRoomInfoParam reserveRoomInfoParam) {
        return ApiResponse.success(
                adminService.getReservationRoomInfo(
                        reserveRoomInfoParam.getRoomId()
                )
        );
    }

    @ApiOperation("管理员查看一个用户的预约请求")
    @RequestMapping("/reservation/user")
    public ResponseEntity<Object> getUserReservation(@RequestBody @Validated ReserveUserInfoParam reserveUserInfoParam) {
        try {
            List<ReservationInfo> reservations = adminService.getReservationUserInfo(
                    reserveUserInfoParam.getUserId()
            );
            return ResponseEntity.ok(reservations);
        }catch (ApiException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("管理员获取所有预约请求")
    @RequestMapping("/reservation/all")
    public ResponseEntity<Object> getAllReservation() {
        try {
            return ResponseEntity.ok(adminService.getAllReservation());
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("管理员获取所有评论")
    @RequestMapping("/comment/all")
    public List<CommentInfo> getAllComments() {
        return adminService.getAllComments();
    }

    @ApiOperation("管理员审核一个评论")
    @PostMapping("/comment/approve")
    public ApiResponse<Boolean> approveComment(@ApiParam("评论id") @RequestParam @NotNull Integer commentId,
                                        @ApiParam("管理员id") @RequestParam @NotNull Integer adminId
    ) {
        return ApiResponse.success(
                adminService.approveComment(commentId, adminId));
    }

    @ApiOperation("管理员获取所有非法操作记录")
    @RequestMapping("/illegal/all")
    public ResponseEntity<Object> getAllIllegal() {
        return ResponseEntity.ok(adminService.getAllIllegal());
    }

    @ApiOperation("管理员获取所有用户登录记录")
    @RequestMapping("/loginlog/all")
    public ResponseEntity<Object> getAllLoginLog() {
        return ResponseEntity.ok(adminService.getAllLoginLog());
    }


    @ApiOperation("管理员获取所有公交线路")
    @RequestMapping("/busline/all")
    public ResponseEntity<Object> getAllBusLine() {
        // 读取json文件
        try{
            return ResponseEntity.ok(adminService.getAllBusLine());
        } catch (ApiException | IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("管理员修改所有公交线路")
    @PostMapping("/busline/update")
    public ResponseEntity<Object> updateAllBusLine(@RequestBody @NotNull List<BuslineParam> buslines) {
        // 读取json文件
        try{
            return ResponseEntity.ok(adminService.updateAllBusLine(buslines));
        } catch (ApiException | IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("管理员获取所有建筑总预约量和点赞量")
    @RequestMapping("/building/statistics")
    public ResponseEntity<Object> getBuildingStatistics() {
        try {
            return ResponseEntity.ok(adminService.getBuildingStatistics());
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("管理员获取所有房间总预约量和点赞量")
    @RequestMapping("/room/statistics")
    public ResponseEntity<Object> getRoomStatistics() {
        try {
            return ResponseEntity.ok(adminService.getRoomStatistics());
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
