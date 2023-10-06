package com.sustech.campus.repository;

import com.sustech.campus.model.RoomType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
    // 根据主键查询
    @Query("SELECT r FROM RoomType r WHERE r.roomTypeId = :roomTypeId")
    RoomType customFindById(@Param("roomTypeId") Integer roomTypeId);

    // 根据容量查询房间类型
    @Query("SELECT r FROM RoomType r WHERE r.capacity = :capacity")
    List<RoomType> findByCapacity(Integer capacity);

    // 根据主键删除
    @Modifying
    @Query("DELETE FROM RoomType r WHERE r.roomTypeId = :roomTypeId")
    void customDeleteById(@Param("roomTypeId") Integer roomTypeId);

    // 添加 RoomType
    @Modifying
    @Query("INSERT INTO RoomType (type, capacity) VALUES (:type, :capacity)")
    void addRoomType(@Param("type") String type, @Param("capacity") Integer capacity);

    // 修改类型
    @Modifying
    @Query("UPDATE RoomType r SET r.type = :type WHERE r.roomTypeId = :roomTypeId")
    void customUpdateType(@Param("roomTypeId") Integer roomTypeId, @Param("type") String type);

    // 修改容量
    @Modifying
    @Query("UPDATE RoomType r SET r.capacity = :capacity WHERE r.roomTypeId = :roomTypeId")
    void customUpdateCapacity(@Param("roomTypeId") Integer roomTypeId, @Param("capacity") Integer capacity);


}

