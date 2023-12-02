package com.sustech.campus.controller;


import com.sustech.campus.database.po.Room;
import com.sustech.campus.model.param.CommentParam;
import com.sustech.campus.model.param.ReserveParam;
import com.sustech.campus.model.param.ReserveUpdateParam;
import com.sustech.campus.service.UserService;
import com.sustech.campus.utils.ApiResponse;
import com.sustech.campus.web.annotation.MappingController;
import com.sustech.campus.web.handler.ApiException;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@MappingController("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户发起预约请求")
    @RequestMapping("/reservation/post")
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

    @ApiOperation("用户修改一个预约请求")
    @RequestMapping("/reservation/update")
    public ApiResponse<Boolean> updateReservation(@RequestBody @Validated ReserveUpdateParam reserveUpdateParam) {
        return ApiResponse.success(
                userService.updateReservation(
                        reserveUpdateParam.getReservation_id(),
                        reserveUpdateParam.getRoom_id(),
                        reserveUpdateParam.getStartTime(),
                        reserveUpdateParam.getEndTime(),
                        reserveUpdateParam.getUserId()
                )
        );
    }

//    @ApiOperation("用户根据地点获取巴士线路信息")
//    @RequestMapping("/busline/get/location")
//    public ApiResponse<Boolean> getBusLine(@RequestBody @Validated BusLineLocationParam busLineLocationParam) {
//        return ApiResponse.success(
//                userService.getBusLineByLocation(
//                        busLineLocationParam.getLocation()
//                )
//        );
//    }

    @ApiOperation("上传评论")
    @RequestMapping("/comment/upload")
    public ResponseEntity<Object> uploadComment(@RequestBody @Validated CommentParam commentParam) {
        try {
            userService.uploadComment(
                    commentParam.getUserId(),
                    commentParam.getTime(),
                    commentParam.getText(),
                    commentParam.getBuildingId(),
                    commentParam.getCommentPhotos()
            );
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("上传失败");
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @ApiOperation("根据建筑ID获取建筑内所有房间信息")
    @RequestMapping("/room/get/building")
    public ApiResponse<List<Room>> getRoomsInBuilding(@RequestParam Integer buildingId) {
        return ApiResponse.success(
                userService.getRoomByBuilding(buildingId)
        );
    }

}
