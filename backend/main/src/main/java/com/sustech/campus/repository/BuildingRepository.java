package com.sustech.campus.repository;

import com.sustech.campus.model.Building;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
    // 根据主键查询
    @Query("SELECT b FROM Building b WHERE b.buildingId = :buildingId")
    Building customFindById(@Param("buildingId") Integer buildingId);

    // 根据主键删除
    @Modifying
    @Query("DELETE FROM Building b WHERE b.buildingId = :buildingId")
    void customDeleteById(@Param("buildingId") Integer buildingId);

    // 添加 Building
    @Modifying
    @Query("INSERT INTO Building (name, openTime, closeTime, video, introduction, nearestStation) VALUES (:name, :openTime, :closeTime, :video, :introduction, :nearestStation)")
    void customAddBuilding(@Param("name") String name, @Param("openTime") Timestamp openTime, @Param("closeTime") Timestamp closeTime, @Param("video") byte[] video, @Param("introduction") String introduction, @Param("nearestStation") String nearestStation);

    // 修改建筑名称
    @Modifying
    @Query("UPDATE Building b SET b.name = :name WHERE b.buildingId = :buildingId")
    void customUpdateName(@Param("buildingId") Integer buildingId, @Param("name") String name);

    // 修改开放时间
    @Modifying
    @Query("UPDATE Building b SET b.openTime = :openTime WHERE b.buildingId = :buildingId")
    void customUpdateOpenTime(@Param("buildingId") Integer buildingId, @Param("openTime") Timestamp openTime);

    // 修改关闭时间
    @Modifying
    @Query("UPDATE Building b SET b.closeTime = :closeTime WHERE b.buildingId = :buildingId")
    void customUpdateCloseTime(@Param("buildingId") Integer buildingId, @Param("closeTime") Timestamp closeTime);

    // 修改视频
    @Modifying
    @Query("UPDATE Building b SET b.video = :video WHERE b.buildingId = :buildingId")
    void customUpdateVideo(@Param("buildingId") Integer buildingId, @Param("video") byte[] video);

    // 修改简介
    @Modifying
    @Query("UPDATE Building b SET b.introduction = :introduction WHERE b.buildingId = :buildingId")
    void customUpdateIntroduction(@Param("buildingId") Integer buildingId, @Param("introduction") String introduction);

    // 修改最近车站
    @Modifying
    @Query("UPDATE Building b SET b.nearestStation = :nearestStation WHERE b.buildingId = :buildingId")
    void customUpdateNearestStation(@Param("buildingId") Integer buildingId, @Param("nearestStation") String nearestStation);
}
