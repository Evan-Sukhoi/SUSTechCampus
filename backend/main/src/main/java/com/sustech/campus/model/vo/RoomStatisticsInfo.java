package com.sustech.campus.model.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomStatisticsInfo {
    Integer roomId;
    String roomName;
    Integer totalLike;
    Integer totalReserve;
}
