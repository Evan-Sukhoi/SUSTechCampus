package com.sustech.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingInfo {
    private Integer buildingId;
    private String name;
    private String openTime;
    private String closeTime;
    private String locationName;
    private String introduction;
    private String nearestStation;
    private String videoUrl;
    private String coverId;
}
