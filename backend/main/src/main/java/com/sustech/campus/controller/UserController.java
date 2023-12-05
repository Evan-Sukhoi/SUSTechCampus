package com.sustech.campus.controller;


import com.sustech.campus.database.annotation.DateField;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.model.param.AvailableReserveParam;
import com.sustech.campus.model.param.CommentParam;
import com.sustech.campus.model.param.ReserveParam;
import com.sustech.campus.model.param.ReserveUpdateParam;
import com.sustech.campus.model.vo.RoomInfo;
import com.sustech.campus.service.UserService;
import com.sustech.campus.utils.ApiResponse;
import com.sustech.campus.web.annotation.MappingController;
import com.sustech.campus.web.handler.ApiException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@MappingController("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户获取可用预约时段")
    @RequestMapping("/reservation/get")
    public ResponseEntity<Object> getAvailableReservation(@RequestBody @Validated AvailableReserveParam availableReserveParam) {
        try{
            return ResponseEntity.ok().body(
                    userService.getAvailableReservation(
                            availableReserveParam.getBuildingId(),
                            availableReserveParam.getDate()
                    )
            );
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("用户发起预约请求")
    @RequestMapping("/reservation/post")
    public ResponseEntity<Object> uploadReservation(@RequestBody @Validated ReserveParam reserveParam) {
        try{
            userService.uploadReservation(
                    reserveParam.getUserId(),
                    reserveParam.getRoomId(),
                    reserveParam.getStartTime(),
                    reserveParam.getEndTime(),
                    reserveParam.getDescription()
            );
            return ResponseEntity.ok().build();
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("用户修改一个预约请求")
    @RequestMapping("/reservation/update")
    public ResponseEntity<Object> updateReservation(@RequestBody @Validated ReserveUpdateParam reserveUpdateParam) {
        try{
            userService.updateReservation(
                    reserveUpdateParam.getReservation_id(),
                    reserveUpdateParam.getRoom_id(),
                    reserveUpdateParam.getStartTime(),
                    reserveUpdateParam.getEndTime(),
                    reserveUpdateParam.getUserId(),
                    reserveUpdateParam.getDescription()
            );
            return ResponseEntity.ok().build();
        }catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("用户获取所有预约请求")
    @RequestMapping("/reservation/get/all")
    public ResponseEntity<Object> getAllReservation(@ApiParam("用户id") @RequestParam Integer userId) {
        try{
            return ResponseEntity.ok().body(userService.getAllReservation(userId));
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
    public ResponseEntity<Object> uploadComment(@RequestPart(required = false) List<MultipartFile> files, @RequestPart @Validated CommentParam commentParam) {
        try {
            userService.uploadComment(
                    commentParam.getUserId(),
                    commentParam.getTime(),
                    commentParam.getText(),
                    commentParam.getBuildingId(),
                    files
            );
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("上传失败");
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @ApiOperation("用户获取建筑物所有教室")
    @RequestMapping("/room/get/building")
    public ResponseEntity<Object> getBuildingRoom(@ApiParam("建筑id") @RequestParam @NotNull Integer buildingId) {
        try {
            return ResponseEntity.ok(userService.getBuildingRoom(buildingId));
        } catch (ApiException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
