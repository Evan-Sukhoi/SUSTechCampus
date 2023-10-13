package com.sustech.campus.service.abandoned;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sustech.campus.model.Reservation;
import com.sustech.campus.repository.ReservationRepository;

import java.security.Timestamp;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // 添加预订
    public boolean addReservation(Integer roomID, Timestamp startTime, Timestamp endTime, Integer userID) {
        try {
            reservationRepository.addReservation(roomID,startTime,endTime,userID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 如果保存过程中出现异常，则返回 false
        }
    }

    // 根据预订ID查询预订
    public Reservation getReservationById(Integer reservationID) {
        return reservationRepository.customFindById(reservationID);
    }

    // 删除预订
    public void deleteReservationById(Integer reservationID) {
        reservationRepository.customDeleteById(reservationID);
    }

    // 查询所有预订
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // 自定义查询方法，根据用户ID查询预订
    public List<Reservation> getReservationsByUserId(Integer userID) {
        return reservationRepository.findByUserID(userID);
    }

    // 自定义查询方法，根据房间ID查询预订
    public List<Reservation> getReservationsByRoomId(Integer roomID) {
        return reservationRepository.findByRoomID(roomID);
    }
}
