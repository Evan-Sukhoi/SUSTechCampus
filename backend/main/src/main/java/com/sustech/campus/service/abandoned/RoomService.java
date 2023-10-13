package com.sustech.campus.service.abandoned;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sustech.campus.model.Room;
import com.sustech.campus.repository.RoomRepository;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // 添加房间
    public boolean addRoom(Integer buildingId, Integer number, Integer roomTypeId) {
        try {
            roomRepository.customAddRoom(buildingId,number,roomTypeId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 如果保存过程中出现异常，则返回 false
        }
    }

    // 根据房间ID查询房间
    public Room getRoomById(Integer roomId) {
        return roomRepository.customFindById(roomId);
    }

    // 删除房间
    public void deleteRoomById(Integer roomId) {
        roomRepository.customDeleteById(roomId);
    }

    // 查询所有房间
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

}
