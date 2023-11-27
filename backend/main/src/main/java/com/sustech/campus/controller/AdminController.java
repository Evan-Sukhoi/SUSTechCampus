package com.sustech.campus.controller;

import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Reservation;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.database.po.User;
import com.sustech.campus.model.param.ReserveRoomInfoParam;
import com.sustech.campus.model.param.ReserveUserInfoParam;
import com.sustech.campus.model.vo.BuildingInfo;
import com.sustech.campus.service.AdminService;
import com.sustech.campus.utils.ApiResponse;
import com.sustech.campus.web.annotation.MappingController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.util.List;

@MappingController("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @ApiOperation("管理员获取所有用户")
    @RequestMapping("/user/all")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
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
    public List<BuildingInfo> getAllBuilding() {
        return adminService.getAllBuilding();
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
                        .open_time(openTime)
                        .close_time(closeTime)
                        .location_name(location_name)
                        .nearest_station(nearest_station)
                        .build()));
    }

    @ApiOperation("管理员删除一个建筑")
    @PostMapping("/building/delete")
    public ApiResponse<Boolean> deleteBuilding(@ApiParam("建筑id") @RequestParam @NotNull Integer buildingId) {
        return ApiResponse.success(
                adminService.deleteBuilding(buildingId));
    }

    @ApiOperation("管理员新建一个教室")
    @PostMapping("/room/add")
    public ApiResponse<Boolean> addRoom(@ApiParam("所属建筑id") @RequestParam @NotNull Integer buildingId,
                                        @ApiParam("教室号") @RequestParam @NotNull Integer number,
                                        @ApiParam("教室类型id") @RequestParam @NotNull Integer roomTypeId
    ) {
        return ApiResponse.success(
                adminService.uploadRoom(Room.builder()
                        .building_id(buildingId)
                        .number(number)
                        .room_type_id(roomTypeId)
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
    public ApiResponse<List<Reservation>> getUserReservation(@RequestBody @Validated ReserveUserInfoParam reserveUserInfoParam) {
        return ApiResponse.success(
                adminService.getReservationUserInfo(
                        reserveUserInfoParam.getUserId()
                )
        );
    }

}
