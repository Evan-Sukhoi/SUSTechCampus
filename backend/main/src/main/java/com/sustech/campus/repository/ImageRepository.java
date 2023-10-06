package com.sustech.campus.repository;

import com.sustech.campus.model.Image;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    // 根据主键查询
    @Query("SELECT i FROM Image i WHERE i.imageId = :imageId")
    Image customFindById(@Param("imageId") Integer imageId);

    // 根据主键删除
    @Modifying
    @Query("DELETE FROM Image i WHERE i.imageId = :imageId")
    void customDeleteById(@Param("imageId") Integer imageId);

    // 添加 Image
    @Modifying
    @Query("INSERT INTO Image (image) VALUES (:imageData)")
    void customAddImage(@Param("imageData") byte[] imageData);

    // 更新 Image 数据
    @Modifying
    @Query("UPDATE Image i SET i.image = :imageData WHERE i.imageId = :imageId")
    void customUpdateImageData(@Param("imageId") Integer imageId, @Param("imageData") byte[] imageData);
}

