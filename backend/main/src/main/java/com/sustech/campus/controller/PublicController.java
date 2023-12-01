package com.sustech.campus.controller;

import com.sustech.campus.database.po.Busline;
import com.sustech.campus.database.po.Comment;
import com.sustech.campus.model.param.LoginParam;
import com.sustech.campus.model.param.RegisterParam;
import com.sustech.campus.model.vo.*;
import com.sustech.campus.service.PublicService;
import com.sustech.campus.utils.ApiResponse;
import com.sustech.campus.web.annotation.MappingController;
import com.sustech.campus.web.handler.ApiException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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

    @ApiOperation("根据建筑ID获取简单建筑信息")
    @RequestMapping("/building/get/simpleThroughId")
    public ApiResponse<BuildingInfoSimple> getBuildingInfoSimpleThroughId(@RequestParam Integer buildingId) {
        return ApiResponse.success(
                publicService.getBuildingInfoSimpleThroughId(buildingId)
        );
    }


    @ApiOperation("根据建筑ID获取详细建筑信息")
    @RequestMapping("/building/get/details")
    public ApiResponse<BuildingInfo> getBuildingDetails(@RequestParam Integer buildingId) {
        return ApiResponse.success(
                publicService.getBuildingDetails(buildingId)
        );
    }

    @ApiOperation("根据建筑ID获取公交站信息")
    @RequestMapping("/building/get/station")
    public ApiResponse<BuildingStation> get(@RequestParam Integer buildingId) {
        return ApiResponse.success(
                publicService.getBuildingStationThroughId(buildingId)
        );
    }



    @ApiOperation("获取已审核通过的评论")
    @RequestMapping("/comment/get/approved")
    public ApiResponse<List<CommentInfo>> getApprovedComments(@ApiParam("id") @RequestParam Integer buildingId) {
        return ApiResponse.success(
                publicService.getApprovedComments(buildingId)
        );
    }

    @ApiOperation("访客登录")
    @RequestMapping("/login")
    public ApiResponse<UserInfo> login(@RequestBody @Validated LoginParam loginParam) {
        return ApiResponse.success(
                publicService.login(
                        loginParam.getUsername(),
                        loginParam.getPassword()
                )
        );
    }

//    @ApiOperation("访客注册")
//    @RequestMapping("/register")
//    public ApiResponse<Boolean> register(@RequestBody @Validated RegisterParam registerParam) {
//        return ApiResponse.success(
//                publicService.register(
//                        registerParam.getUsername(),
//                        registerParam.getPassword(),
//                        registerParam.getEmail(),
//                        registerParam.getPhoneNumber(),
//                        registerParam.getImage()
//                )
//        );
//    }

    @ApiOperation("获取所有公交线路信息")
    @RequestMapping("/busline/all")
    public List<Busline> getAllBusLine() {
        return publicService.getAllBusLine();
    }

    @ApiOperation("获取所有评论信息")
    @RequestMapping("/comment/get/building")
    public List<Comment> getCommentByBuilding(@RequestParam Integer buildingId) {
        return publicService.getCommentByBuilding(buildingId);
    }

    /**
     * 这是新的注册方法
     * @param file img文件
     * @param registerParam json数据
     * @return
     */
    @ApiOperation("访客注册")
    @RequestMapping("/register")
    public ResponseEntity<Object> register(@RequestPart MultipartFile file, @RequestPart @Validated RegisterParam registerParam){
        try {
            publicService.register(
                registerParam.getUsername(),
                registerParam.getPassword(),
                registerParam.getEmail(),
                registerParam.getPhoneNumber(),
                file);
            System.out.println("ok");
            return new ResponseEntity<>("", HttpStatus.OK);
        }catch (ApiException e){
            System.out.println(e);
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity<>("图片保存失败，请稍后再试", HttpStatus.BAD_REQUEST);
        }
    }
}
