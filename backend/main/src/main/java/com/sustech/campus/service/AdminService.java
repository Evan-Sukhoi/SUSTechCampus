package com.sustech.campus.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface AdminService {

    /**
     * 上传建筑信息
     * @param name 建筑名称
     * @param description 建筑描述
     * @param location 建筑坐标
     */
    void uploadBuilding(String name, String description, String location);

    /**
     * 上传教室信息
     * @param name 教室名称
     * @param description 教室描述
     * @param location 教室位置（楼层、房间号）
     * @param buildingId 所属建筑id
     */
    void uploadRoom(String name, String description, String location, Integer buildingId);


    /**
     * 删除建筑信息
     * @param buildingId 建筑id
     */
    void deleteBuilding(Integer buildingId);

    /**
     * 删除教室信息
     * @param roomId 教室id
     */
    void deleteRoom(Integer roomId);

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


    /**
     * 删除教室封面
     * @param mediaId 封面id
     * @param roomId 教室id
     */
    void deleteRoomTypeMedia(String mediaId, Integer roomId);
}
