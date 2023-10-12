package com.sustech.campus.service;

<<<<<<< HEAD
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sustech.campus.model.Admin;
import com.sustech.campus.repository.AdminRepository;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // 添加管理员
    public boolean addAdmin(String name, String phone, String email, String password) {
        try {
            adminRepository.addAdmin(name, phone, email, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 如果保存过程中出现异常，则返回 false
        }
    }

    // 根据管理员ID查询管理员
    public Admin getAdminById(Integer adminId) {
        return adminRepository.customFindById(adminId);
    }

    // 删除管理员
    public void deleteAdminById(Integer adminId) {
        adminRepository.customDeleteById(adminId);
    }

    // 更新管理员姓名
    public void updateAdminName(Integer adminId, String name) {
        adminRepository.customUpdateName(adminId, name);
    }

    // 更新管理员电话号码
    public void updateAdminPhone(Integer adminId, String phone) {
        adminRepository.customUpdatePhone(adminId, phone);
    }

    // 更新管理员邮箱
    public void updateAdminEmail(Integer adminId, String email) {
        adminRepository.customUpdateEmail(adminId, email);
    }

    // 更新管理员密码
    public void updateAdminPassword(Integer adminId, String password) {
        adminRepository.customUpdatePassword(adminId, password);
    }

    // 查询所有管理员
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // 自定义查询方法，根据姓名查询管理员
    public List<Admin> getAdminsByName(String name) {
        return adminRepository.findByName(name);
    }
}

=======

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
>>>>>>> f00509e1af1526857db9ebf7ef657c404e023f60
