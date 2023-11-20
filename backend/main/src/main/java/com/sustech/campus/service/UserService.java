package com.sustech.campus.service;

import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Comment;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.model.vo.BuildingInfo;

import java.sql.Time;
import java.util.List;

public interface UserService extends PublicService {

    /**
     * 上传评论
     * @param userId 用户ID
     */
    Boolean uploadComment(Integer commentId, Integer userId, Time time, String text, Integer buildingId);


    /**
     * 获取所有教室信息
     * @return 所有教室信息
     */
    List<Room> getAllRoom();

    /**
     * 根据建筑ID获取教室信息
     * @param buildingId 建筑ID
     * @return 教室信息
     */
    List<Room> getRoomByBuildingId(Integer buildingId);

    /**
     * 根据教室类型获取教室信息
     * @param type 教室类型
     * @return 教室信息
     */
    List<Room> getRoomByType(String type);

    /**
     * 根据教室ID获取教室信息
     * @param roomId 教室ID
     * @return 教室信息
     */
    Room getRoomByRoomId(Integer roomId);

    /**
     * 根据建筑ID获取建筑信息
     * @param buildingId 建筑ID
     * @return 建筑信息
     */
    List<Building> getBuildingById(Integer buildingId);

    /**
     *获得评论通过建筑id
     * @param buildingId 用户ID
     */
    List<Comment> getCommentByBuildingId(Integer buildingId);


    /**
     * 上传预约
     * @param userId 用户ID
     * @param roomId 教室ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 是否上传成功
     */
    Boolean uploadReservation(Integer userId, Integer roomId, Time startTime, Time endTime, Integer user_ID);
 }
