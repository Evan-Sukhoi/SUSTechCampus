package com.sustech.campus.service.abandoned;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sustech.campus.model.Blacklist;
import com.sustech.campus.repository.BlacklistRepository;

import java.util.List;

@Service
public class BlacklistService {

    private final BlacklistRepository blacklistRepository;

    @Autowired
    public BlacklistService(BlacklistRepository blacklistRepository) {
        this.blacklistRepository = blacklistRepository;
    }

    // 添加黑名单
    public boolean addBlacklist(Integer userId, Integer adminId) {
        try {
            blacklistRepository.customAddBlacklist(userId, adminId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 如果保存过程中出现异常，则返回 false
        }
    }

    // 根据黑名单ID查询黑名单
    public Blacklist getBlacklistById(Integer listId) {
        return blacklistRepository.customFindById(listId);
    }

    // 删除黑名单
    public void deleteBlacklistById(Integer listId) {
        blacklistRepository.customDeleteById(listId);
    }

    // 查询所有黑名单
    public List<Blacklist> getAllBlacklists() {
        return blacklistRepository.findAll();
    }

    // 自定义查询方法，根据用户ID查询黑名单记录
    public List<Blacklist> getBlacklistsByUserId(Integer userId) {
        return blacklistRepository.customFindByUserId(userId);
    }

    // 自定义查询方法，根据管理员ID查询黑名单记录
    public List<Blacklist> getBlacklistsByAdminId(Integer adminId) {
        return blacklistRepository.customFindByAdminId(adminId);
    }
}

