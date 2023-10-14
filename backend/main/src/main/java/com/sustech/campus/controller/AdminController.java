package com.sustech.campus.controller;

import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.service.AdminService;
import com.sustech.campus.utils.ApiResponse;
import com.sustech.campus.web.annotation.MappingController;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.sql.Time;

@MappingController("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @ApiOperation("管理员新建一个建筑")
    @PostMapping("/building/add")
    public ApiResponse<Boolean> addBuilding(
                                            @ApiParam("建筑名称") String name,
                                            @ApiParam("建筑简介") String introduction,
                                            @ApiParam("开放时间") Time openTime,
                                            @ApiParam("关闭时间") Time closeTime,
                                            @ApiParam("位置") String location_name,
                                            @ApiParam("附近车站") String nearest_station
    ) {
        return ApiResponse.success(
                adminService.uploadBuilding(Building.builder()
                        .name(name)
                        .introduction(introduction)
                        .openTime(openTime)
                        .closeTime(closeTime)
                        .location_name(location_name)
                        .nearest_station(nearest_station)
                        .build()));
    }

    @ApiOperation("管理员删除一个建筑")
    @PostMapping("/building/delete")
    public ApiResponse<Boolean> deleteBuilding(@ApiParam("建筑id") Integer buildingId) {
        return ApiResponse.success(
                adminService.deleteBuilding(buildingId));
    }

    @ApiOperation("管理员新建一个教室")
    @PostMapping("/room/add")
    public ApiResponse<Boolean> addRoom(@ApiParam("所属建筑id") Integer buildingId,
                                        @ApiParam("教室号") Integer number,
                                        @ApiParam("教室类型id") Integer roomTypeId
    ) {
        return ApiResponse.success(
                adminService.uploadRoom(Room.builder()
                        .building_ID(buildingId)
                        .number(number)
                        .room_type_ID(roomTypeId)
                        .build()));
    }

    @ApiOperation("管理员删除一个教室")
    @PostMapping("/room/delete")
    public ApiResponse<Boolean> deleteRoom(@ApiParam("教室id") Integer roomId) {
        return ApiResponse.success(
                adminService.deleteRoom(roomId));
    }

}
