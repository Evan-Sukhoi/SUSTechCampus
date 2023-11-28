package com.sustech.campus.service;

import com.sustech.campus.database.po.Bus_line;
import com.sustech.campus.database.po.Comment;
import com.sustech.campus.model.vo.BuildingInfo;
import com.sustech.campus.model.vo.BuildingInfoSimple;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PublicService {

    /**
     * 获取所有建筑信息
     * @return 所有建筑信息
     */
    List<Bus_line> getAllBusLine();

    List<BuildingInfoSimple> getSimpleBuildingInfo();

    BuildingInfo getBuildingDetails(Integer buildingId);

    Comment getApprovedComments(Integer buildingId);

    Boolean login(String username, String password);

    Boolean register(String username, String password, String email, String phoneNumber, MultipartFile image);

    List<Comment> getCommentByBuilding(Integer buildingId);
}
