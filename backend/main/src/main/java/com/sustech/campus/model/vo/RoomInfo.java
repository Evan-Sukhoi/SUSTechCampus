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
    private Integer room_type_id;
    private String room_type_name;
    private Integer capacity;
    private String description;
    private String building_name;
}
