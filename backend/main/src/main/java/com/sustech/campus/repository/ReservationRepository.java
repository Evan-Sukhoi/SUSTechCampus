package com.sustech.campus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sustech.campus.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sustech.campus.model.Reservation;

import java.security.Timestamp;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // 根据主键查询
    @Query("SELECT r FROM Reservation r WHERE r.reservationId = :reservationId")
    Reservation customFindById(@Param("reservationId") Integer reservationId);

    // 根据主键删除
    @Modifying
    @Query("DELETE FROM Reservation r WHERE r.reservationId = :reservationId")
    void customDeleteById(@Param("reservationId") Integer reservationId);

    // 添加 Reservation
    @Modifying
    @Query("INSERT INTO Reservation (room_ID, start_time, end_time, user_ID) VALUES (:roomId, :startTime, :endTime, :userId)")
    void customAddReservation(@Param("roomId") Integer roomId, @Param("startTime") Timestamp startTime, @Param("endTime") Timestamp endTime, @Param("userId") Integer userId);

    // 修改房间ID
    @Modifying
    @Query("UPDATE Reservation r SET r.roomId = :roomId WHERE r.reservationId = :reservationId")
    void customUpdateRoomId(@Param("reservationId") Integer reservationId, @Param("roomId") Integer roomId);

    // 修改开始时间
    @Modifying
    @Query("UPDATE Reservation r SET r.startTime = :startTime WHERE r.reservationId = :reservationId")
    void customUpdateStartTime(@Param("reservationId") Integer reservationId, @Param("startTime") Timestamp startTime);

    // 修改结束时间
    @Modifying
    @Query("UPDATE Reservation r SET r.endTime = :endTime WHERE r.reservationId = :reservationId")
    void customUpdateEndTime(@Param("reservationId") Integer reservationId, @Param("endTime") Timestamp endTime);

    // 修改用户ID
    @Modifying
    @Query("UPDATE Reservation r SET r.userId = :userId WHERE r.reservationId = :reservationId")
    void customUpdateUserId(@Param("reservationId") Integer reservationId, @Param("userId") Integer userId);
}

