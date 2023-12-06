package com.sustech.campus.service;

import com.alipay.api.AlipayApiException;
import com.sustech.campus.database.po.Busline;
import com.sustech.campus.database.po.Comment;
import com.sustech.campus.database.po.User;
import com.sustech.campus.model.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
public interface PublicService {

    /**
     * 获取所有建筑信息
     * @return 所有建筑信息
     */
    Object getAllBusLine() throws IOException;

    List<BuildingInfoSimple> getSimpleBuildingInfo();

    BuildingStation getBuildingStationThroughId(Integer buildingId);

    BuildingInfoSimple getBuildingInfoSimpleThroughId(Integer buildingId);

    BuildingInfo getBuildingDetails(Integer buildingId);

    List<CommentInfo> getApprovedComments(Integer buildingId);

    UserInfo login(String username, String password);

    Boolean register(String username, String password, String email, String phoneNumber, Integer authCode, MultipartFile file) throws IOException;

    List<Comment> getCommentByBuilding(Integer buildingId);

    String authenticate(User user);

    Boolean sendAuthCode(String email);

    String buy(String url, Integer productId) throws AlipayApiException;
}
