package com.sustech.campus.model.vo;

import org.springframework.data.util.Pair;

import java.util.Date;
import java.util.List;

public class AvailableReservationInfo {
    private Integer roomId;
    private Integer roomNumber;
    private Integer roomTypeId;
    private String roomType;
    private List<Pair<Date, Date>> availableTime;
}
