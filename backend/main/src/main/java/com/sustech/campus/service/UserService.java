package com.sustech.campus.service;

import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Comment;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.database.po.RoomType;
import com.sustech.campus.model.vo.AvailableReservationInfo;
import com.sustech.campus.model.vo.RoomInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface UserService{

    /**
     * 上传评论
     * @param userId 用户ID
     */
    Boolean uploadComment(Integer userId, Date time, String text, Integer buildingId, List<MultipartFile> files) throws IOException;

    /**
     * 根据建筑ID获取教室信息
     * @param buildingId 建筑ID
     * @return 教室信息
     */
    List<RoomInfo> getRoomByBuilding(Integer buildingId);


    /**
     * 根据教室类型ID获取教室类型
     */
    RoomType getRoomTypeById(Integer roomTypeId);

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
     * 上传预约
     * @param userId 用户ID
     * @param roomId 教室ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 是否上传成功
     */
    Boolean uploadReservation(Integer userId, Integer roomId, Date startTime, Date endTime);

    Boolean updateReservation(Long reservationId, Integer roomId, Date startTime, Date endTime, Integer userId);

    List<AvailableReservationInfo> getReservation(Integer buildingId);
}
