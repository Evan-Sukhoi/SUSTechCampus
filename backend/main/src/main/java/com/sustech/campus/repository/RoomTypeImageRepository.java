package com.sustech.campus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sustech.campus.model.RoomTypeImage;

import java.util.List;

@Repository
public interface RoomTypeImageRepository extends JpaRepository<RoomTypeImage, Integer> {
    // 根据 roomTypeId 和 imageId 删除记录
    @Modifying
    @Query("DELETE FROM RoomTypeImage rti WHERE rti.roomTypeId = :roomTypeId AND rti.imageId = :imageId")
    void customDeleteByRoomTypeIdAndImageId(@Param("roomTypeId") Integer roomTypeId, @Param("imageId") Integer imageId);

    // 添加 RoomTypeImage
    @Modifying
    @Query("INSERT INTO RoomTypeImage (roomTypeId, imageId) VALUES (:roomTypeId, :imageId)")
    void customAddRoomTypeImage(@Param("roomTypeId") Integer roomTypeId, @Param("imageId") Integer imageId);

    // 修改 roomTypeId 属性
    @Modifying
    @Query("UPDATE RoomTypeImage rti SET rti.roomTypeId = :roomTypeId WHERE rti.imageId = :imageId")
    void customUpdateRoomTypeId(@Param("imageId") Integer imageId, @Param("roomTypeId") Integer roomTypeId);

    // 自定义查询，根据 roomTypeId 查询所有相关记录
    @Query("SELECT rti FROM RoomTypeImage rti WHERE rti.roomTypeId = :roomTypeId")
    List<RoomTypeImage> customFindAllByRoomTypeId(@Param("roomTypeId") Integer roomTypeId);
}

