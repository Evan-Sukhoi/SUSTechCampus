package com.sustech.campus.service;

import com.sustech.campus.database.po.Busline;
import com.sustech.campus.database.po.Comment;
import com.sustech.campus.model.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PublicService {

    /**
     * 获取所有建筑信息
     * @return 所有建筑信息
     */
    List<Busline> getAllBusLine();

    List<BuildingInfoSimple> getSimpleBuildingInfo();

    BuildingStation getBuildingStationThroughId(Integer buildingId);

    BuildingInfoSimple getBuildingInfoSimpleThroughId(Integer buildingId);

    BuildingInfo getBuildingDetails(Integer buildingId);

    List<CommentInfo> getApprovedComments(Integer buildingId);

    UserInfo login(String username, String password);

    Boolean register(String username, String password, String email, String phoneNumber, MultipartFile file) throws IOException;

    List<Comment> getCommentByBuilding(Integer buildingId);
}
