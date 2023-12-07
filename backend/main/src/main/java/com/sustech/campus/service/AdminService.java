package com.sustech.campus.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sustech.campus.database.po.Building;
import com.sustech.campus.database.po.Reservation;
import com.sustech.campus.database.po.Room;
import com.sustech.campus.database.po.User;
import com.sustech.campus.model.param.BuslineParam;
import com.sustech.campus.model.param.RegisterParam;
import com.sustech.campus.model.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface AdminService {

    /**
     * 获取某个用户的所有信息
     */
    User getUserInfo(Integer userId);

    /**
     * 更新某个用户的信息
     */
    Boolean updateUserInfo(Integer userId, String name, String phone, String email, String password);

    /**
     * 删除某个用户
     */
    Boolean deleteUser(Integer userId);

    /**
     * 获取所有用户信息
     * @return 返回所有用户信息
     */
    List<User> getAllUsers();

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
     * 更新建筑信息
     * @param building
     * @return
     */
    Boolean updateBuilding(Building building);

    /**
     * 上传教室信息
     * @param room 教室信息
     */
    Boolean uploadRoom(Room room);


    List<RoomInfo> getAllRoom();

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

    List<Reservation> getReservationRoomInfo(Integer roomId);

    List<ReservationInfo> getReservationUserInfo(Integer userId);

    Boolean register(String username, String password, String email, String phoneNumber, MultipartFile file) throws IOException;

    List<CommentInfo> getAllComments();

    Boolean approveComment(Integer commentId, Integer adminId);

    Object getAllBusLine() throws IOException;

    Boolean updateAllBusLine(List<BuslineParam> buslines) throws IOException;


    Boolean blockUser(Integer userId);

    void unblockUser(Integer userId);

    Boolean batchRegister(List<RegisterParam> registerParams);

    List<IllegalLogInfo> getAllIllegal();

    List<ReservationInfo> getAllReservation();
}
