package com.sustech.campus.service;

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

