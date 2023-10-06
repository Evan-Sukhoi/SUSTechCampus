package com.sustech.campus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sustech.campus.model.Building;
import com.sustech.campus.repository.BuildingRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    // 添加建筑
    public boolean addBuilding(String name, Timestamp openTime, Timestamp closeTime, byte[] video, String introduction, String nearestStation) {
        try {
            buildingRepository.addBuilding(name,openTime,closeTime,video,introduction,nearestStation);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 如果保存过程中出现异常，则返回 false
        }
    }

    // 根据建筑ID查询建筑
    public Building getBuildingById(Integer buildingId) {
        return buildingRepository.customFindById(buildingId);
    }

    // 删除建筑
    public void deleteBuildingById(Integer buildingId) {
        buildingRepository.customDeleteById(buildingId);
    }

    // 更新建筑名称
    public void updateBuildingName(Integer buildingId, String name) {
        buildingRepository.customUpdateName(buildingId, name);
    }

    // 更新建筑开放时间
    public void updateBuildingOpenTime(Integer buildingId, Timestamp openTime) {
        buildingRepository.customUpdateOpenTime(buildingId, openTime);
    }

    // 更新建筑关闭时间
    public void updateBuildingCloseTime(Integer buildingId, Timestamp closeTime) {
        buildingRepository.customUpdateCloseTime(buildingId, closeTime);
    }

    // 更新建筑介绍
    public void updateBuildingIntroduction(Integer buildingId, String introduction) {
        buildingRepository.customUpdateIntroduction(buildingId, introduction);
    }

    // 查询所有建筑
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

}

