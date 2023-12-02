package com.sustech.campus.model.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomInfo {
    private Integer roomId;
    private Integer buildingId;
    private Integer number;
    private Integer roomTypeId;

    private String roomTypeName;
    private Integer capacity;
    private String description;
    private String buildingName;
}
