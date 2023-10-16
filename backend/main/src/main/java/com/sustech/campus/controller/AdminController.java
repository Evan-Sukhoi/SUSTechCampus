package com.sustech.campus.controller;

import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.model.vo.BuildingInfo;
import com.sustech.campus.service.AdminService;
import com.sustech.campus.utils.ApiResponse;
import com.sustech.campus.web.annotation.MappingController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PostMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.util.List;

@MappingController("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

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

}
