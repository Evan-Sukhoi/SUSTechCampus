package com.sustech.campus.model.vo;

import com.sustech.campus.database.annotation.TimeField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingInfo {
    private Integer buildingId;
    private String name;
    @TimeField
    private Date openTime;
    @TimeField
    private Date closeTime;
    private String locationName;
    private String introduction;
    private String nearestStation;
    private String buildingType;
    private String videoUrl;
    private String coverUrl;
    private List<String> imageUrl;
    private Boolean isReservable;
}
