package com.sustech.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingStatisticsInfo {
    Integer buildingId;
    String buildingName;
    Integer totalRoom;
    Integer totalLike;
    Integer totalReserve;
}
