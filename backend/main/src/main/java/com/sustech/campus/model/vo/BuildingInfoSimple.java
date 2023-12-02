package com.sustech.campus.model.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingInfoSimple {
    private Integer buildingId;
    private String name;
    private String coverUrl;
    private String introduction;
    private String buildingType;
}
