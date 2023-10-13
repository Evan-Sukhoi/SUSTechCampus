package com.sustech.campus.service;

import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.model.vo.BuildingInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface AdminService {

    /**
     * 获取所有建筑信息
     * @return 返回所有建筑信息
     */
    List<BuildingInfo> getAllBuilding();

    /**
     * 上传建筑信息
     * @param building 建筑信息
     */
    Boolean uploadBuilding(Building building);

    /**
     * 上传教室信息
     * @param room 教室信息
     */
    Boolean uploadRoom(Room room);


    /**
     * 删除建筑信息
     * @param buildingId 建筑id
     */
    Boolean deleteBuilding(Integer buildingId);

    /**
     * 删除教室信息
     * @param roomId 教室id
     */
    Boolean deleteRoom(Integer roomId);

    /**
     * 上传教室封面
     * @param picture 上传的图片
     * @param roomId 教室id
     * @return 返回上传房型封面之后的获取url
     */
    String uploadRoomTypeCover(MultipartFile picture, Integer roomId);

    /**
     * 上传教室图片/视频
     * @param media 上传的图片/视频
     * @param roomId 教室id
     * @return 返回上传房型图片之后的获取url
     */
    String uploadRoomTypeMedia(MultipartFile media, Integer roomId);

}
