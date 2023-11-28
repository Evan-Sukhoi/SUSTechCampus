package com.sustech.campus.model.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationInfo {
    private Integer reservationId;
    private Integer roomId;
    private Integer userId;
    private Date startTime;
    private Date endTime;

    private String userName;
    private String roomType;
}
