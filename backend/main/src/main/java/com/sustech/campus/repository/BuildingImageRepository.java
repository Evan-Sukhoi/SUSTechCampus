package com.sustech.campus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sustech.campus.model.BuildingImage;

import java.util.List;

@Repository
public interface BuildingImageRepository extends JpaRepository<BuildingImage, Integer> {

    // 根据 buildingId 和 imageId 删除记录
    @Modifying
    @Query("DELETE FROM BuildingImage bi WHERE bi.buildingId = :buildingId AND bi.imageId = :imageId")
    void customDeleteByBuildingIdAndImageId(@Param("buildingId") Integer buildingId, @Param("imageId") Integer imageId);

    // 添加 BuildingImage
    @Modifying
    @Query("INSERT INTO BuildingImage (buildingId, imageId) VALUES (:buildingId, :imageId)")
    void customAddBuildingImage(@Param("buildingId") Integer buildingId, @Param("imageId") Integer imageId);

    // 修改 buildingId 属性
    @Modifying
    @Query("UPDATE BuildingImage bi SET bi.buildingId = :buildingId WHERE bi.imageId = :imageId")
    void customUpdateBuildingId(@Param("imageId") Integer imageId, @Param("buildingId") Integer buildingId);

    // 自定义查询，根据 buildingId 查询所有相关记录
    @Query("SELECT bi FROM BuildingImage bi WHERE bi.buildingId = :buildingId")
    List<BuildingImage> customFindAllByBuildingId(@Param("buildingId") Integer buildingId);
}

