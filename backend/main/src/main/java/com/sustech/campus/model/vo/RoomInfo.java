package com.sustech.campus.model.vo;


import lombok.Data;

@Data
public class RoomInfo {
    private Integer roomId;
    private Integer buildingId;
    private Integer number;
    private Integer room_type_id;
    private String building_name;
}
