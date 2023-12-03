package com.sustech.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomsInfo {
    private Integer roomTypeId;
    private String roomTypeName;
    private List<String> roomImageUrls;
    private Integer capacity;
    private String description;
    private List<Integer> roomNumbers;
}
