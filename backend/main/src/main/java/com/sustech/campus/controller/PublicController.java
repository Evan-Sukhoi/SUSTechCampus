package com.sustech.campus.controller;

import com.alipay.api.AlipayApiException;
import com.sun.mail.smtp.SMTPSendFailedException;
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
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
    public ResponseEntity<Object> getBuildingDetails(@RequestParam Integer buildingId) {
        try {
            return ResponseEntity.ok().body(
                    publicService.getBuildingDetails(buildingId)
            );
        } catch (ApiException e) {
            return ResponseEntity.accepted().body(e.getMessage());
        }
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

    @ApiOperation("发送验证码")
    @RequestMapping("/send-auth-code")
    public ResponseEntity<Object> sendAuthCode(@ApiParam(required = true)
                                               @Email @NotNull @RequestParam String email) {
        try {
            publicService.sendAuthCode(email);
            return ResponseEntity.ok().body("验证码已发送");
        } catch (Exception e) {
            return ResponseEntity.accepted().body(e.getMessage());
        }
    }

    @ApiOperation("访客登录")
    @RequestMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Validated LoginParam loginParam) {
        try{
            return new ResponseEntity<>(publicService.login(loginParam.getUsername(), loginParam.getPassword()), HttpStatus.OK);
        }catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.accepted().body(response);
        }
    }

//    @ApiOperation("获取所有公交线路信息")
//    @RequestMapping("/busline/all")
//    public List<Busline> getAllBusLine() {
//        return publicService.getAllBusLine();
//    }


    @ApiOperation("获取所有公交线路")
    @RequestMapping("/busline/all")
    public ResponseEntity<Object> getAllBusLine() {
        // 读取json文件
        try{
            return new ResponseEntity<>(publicService.getAllBusLine(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("读取json文件失败", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("获取所有评论信息")
    @RequestMapping("/comment/get/building")
    public List<Comment> getCommentByBuilding(@RequestParam Integer buildingId) {
        return publicService.getCommentByBuilding(buildingId);
    }

    /**
     * 这是新的注册方法
     *
     * @param file          img文件
     * @param registerParam json数据
     * @return
     */
    @ApiOperation("访客注册")
    @RequestMapping("/register")
    public ResponseEntity<Object> register(@RequestPart MultipartFile file, @RequestPart @Validated RegisterParam registerParam) {
        try {
            publicService.register(
                    registerParam.getUsername(),
                    registerParam.getPassword(),
                    registerParam.getEmail(),
                    registerParam.getPhoneNumber(),
                    registerParam.getAuthCode(),
                    file);
            System.out.println("ok");
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @ApiOperation("购买商品")
//    @RequestMapping("/buy")
//    public ResponseEntity<Object> buy(@ApiParam("返回时跳转的url") @RequestParam String url,
//                                      @ApiParam("商品ID") @RequestParam Integer productId) {
//        try {
//            return ResponseEntity.ok().body(
//                    publicService.buy(url, productId)
//            );
//        } catch (ApiException | AlipayApiException e) {
//            return ResponseEntity.accepted().body(e.getMessage());
//        }
//    }

    @ApiOperation("获取公钥")
    @RequestMapping("/get-key")
    public ResponseEntity<Object> getKey() {
        try {
            Map<String, String> response = new HashMap<>();
            response.put("publicKey", publicService.getKey());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.accepted().body(e.getMessage());
        }
    }

    @ApiOperation("获取所有商品")
    @RequestMapping("/product/all")
    public ResponseEntity<Object> getAllProduct() {
        try {
            return ResponseEntity.ok().body(
                    publicService.getAllProduct()
            );
        } catch (ApiException e) {
            return ResponseEntity.accepted().body(e.getMessage());
        }
    }

    @ApiOperation("获取CDKey")
    @RequestMapping("/get-cdkey")
    public ResponseEntity<Object> getCDKey(@ApiParam("订单ID") @RequestParam String orderId) {
        try {
            Map<String, String> response = new HashMap<>();
            response.put("cdkey", publicService.getCDKey(Long.valueOf(orderId)));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.accepted().body(e.getMessage());
        }
    }
}
