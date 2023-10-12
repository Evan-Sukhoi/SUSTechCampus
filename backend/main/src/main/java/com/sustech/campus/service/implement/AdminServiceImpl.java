package com.sustech.campus.service.implement;

import com.sustech.campus.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public void uploadBuilding(String name, String description, String location) {

    }

    @Override
    public void uploadRoom(String name, String description, String location, Integer buildingId) {

    }

    @Override
    public void deleteBuilding(Integer buildingId) {

    }

    @Override
    public void deleteRoom(Integer roomId) {

    }

    @Override
    public String uploadRoomTypeCover(MultipartFile picture, Integer roomId) {
        return null;
    }

    @Override
    public String uploadRoomTypeMedia(MultipartFile media, Integer roomId) {
        return null;
    }

    @Override
    public void deleteRoomTypeMedia(String mediaId, Integer roomId) {

    }


}
