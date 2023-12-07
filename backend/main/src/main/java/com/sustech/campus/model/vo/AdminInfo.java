package com.sustech.campus.model.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminInfo {
    private Integer adminId;
    private String name;
    private String phone;
    private String email;
    private String imageUrl;
    private String token;
}
