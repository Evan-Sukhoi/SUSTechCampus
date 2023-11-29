package com.sustech.campus.controller;

import com.sustech.campus.database.po.Bus_line;
import com.sustech.campus.database.po.Comment;
import com.sustech.campus.model.param.LoginParam;
import com.sustech.campus.model.param.RegisterParam;
import com.sustech.campus.model.vo.BuildingInfo;
import com.sustech.campus.model.vo.BuildingInfoSimple;
import com.sustech.campus.service.PublicService;
import com.sustech.campus.utils.ApiResponse;
import com.sustech.campus.web.annotation.MappingController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@MappingController("/public")
public class PublicController {

    @Resource
    private PublicService publicService;

    @ApiOperation("获取所有简单建筑信息")
    @RequestMapping("/building/get/simple")
    public ApiResponse<List<BuildingInfoSimple>> getSimpleBuildingInfo() {
        return ApiResponse.success(
                publicService.getSimpleBuildingInfo()
        );
    }

    @ApiOperation("根据建筑ID获取详细建筑信息")
    @RequestMapping("/building/get/details")
    public ApiResponse<BuildingInfo> getBuildingDetails(@RequestParam Integer buildingId) {
        return ApiResponse.success(
                publicService.getBuildingDetails(buildingId)
        );
    }

    @ApiOperation("获取已审核通过的评论")
    @RequestMapping("/comment/get/approved")
    public ApiResponse<Comment> getApprovedComments(@ApiParam("id") @RequestParam Integer buildingId) {
        System.out.println("=-----------==================-");
        System.out.println(buildingId);
        return ApiResponse.success(
                publicService.getApprovedComments(buildingId)
        );
    }

    @ApiOperation("访客登录")
    @RequestMapping("/login")
    public ApiResponse<Boolean> login(@RequestBody @Validated LoginParam loginParam) {
        return ApiResponse.success(
                publicService.login(
                        loginParam.getUsername(),
                        loginParam.getPassword()
                )
        );
    }

    @ApiOperation("访客注册")
    @RequestMapping("/register")
    public ApiResponse<Boolean> register(@RequestBody @Validated RegisterParam registerParam) {
        return ApiResponse.success(
                publicService.register(
                        registerParam.getUsername(),
                        registerParam.getPassword(),
                        registerParam.getEmail(),
                        registerParam.getPhoneNumber(),
                        registerParam.getImage()
                )
        );
    }

    @ApiOperation("获取所有公交线路信息")
    @RequestMapping("/busline/all")
    public List<Bus_line> getAllBusLine() {
        return publicService.getAllBusLine();
    }

    @ApiOperation("获取所有评论信息")
    @RequestMapping("/comment/get/building")
    public List<Comment> getCommentByBuilding(@RequestParam Integer buildingId) {
        return publicService.getCommentByBuilding(buildingId);
    }
}
