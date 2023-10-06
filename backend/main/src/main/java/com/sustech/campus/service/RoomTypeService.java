package com.sustech.campus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sustech.campus.model.RoomType;
import com.sustech.campus.repository.RoomTypeRepository;

import java.util.List;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    // 添加房间类型
    public boolean addRoomType(String type, Integer capacity) {
        try {
            roomTypeRepository.addRoomType(type,capacity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 如果保存过程中出现异常，则返回 false
        }
    }

    // 根据房间类型ID查询房间类型
    public RoomType getRoomTypeById(Integer roomTypeId) {
        return roomTypeRepository.customFindById(roomTypeId);
    }

    // 删除房间类型
    public void deleteRoomTypeById(Integer roomTypeId) {
        roomTypeRepository.customDeleteById(roomTypeId);
    }

    // 查询所有房间类型
    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    // 自定义查询方法，根据容量查询房间类型
    public List<RoomType> getRoomTypesByCapacity(Integer capacity) {
        return roomTypeRepository.findByCapacity(capacity);
    }
}

