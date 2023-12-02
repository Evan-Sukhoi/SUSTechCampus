package com.sustech.campus.model.vo;

import com.sustech.campus.database.annotation.TimeField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailableReservationInfo {
    private Integer roomId;
    private Integer roomNumber;
    private Integer roomTypeId;
    private String roomType;
    @TimeField
    private List<Date> availableTimeBegin;
    @TimeField
    private List<Date> availableTimeEnd;
}
