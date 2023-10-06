package com.sustech.campus.repository;

import com.sustech.campus.model.Room;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    // 根据主键查询
    @Query("SELECT r FROM Room r WHERE r.roomId = :roomId")
    Room customFindById(@Param("roomId") Integer roomId);

    // 根据主键删除
    @Modifying
    @Query("DELETE FROM Room r WHERE r.roomId = :roomId")
    void customDeleteById(@Param("roomId") Integer roomId);

    // 添加 Room
    @Modifying
    @Query("INSERT INTO Room (buildingId, number, roomTypeId) VALUES (:buildingId, :number, :roomTypeId)")
    void customAddRoom(@Param("buildingId") Integer buildingId, @Param("number") Integer number, @Param("roomTypeId") Integer roomTypeId);

    // 修改房间号
    @Modifying
    @Query("UPDATE Room r SET r.number = :number WHERE r.roomId = :roomId")
    void customUpdateNumber(@Param("roomId") Integer roomId, @Param("number") Integer number);

    // 修改房间类型
    @Modifying
    @Query("UPDATE Room r SET r.roomTypeId = :roomTypeId WHERE r.roomId = :roomId")
    void customUpdateRoomType(@Param("roomId") Integer roomId, @Param("roomTypeId") Integer roomTypeId);
}

