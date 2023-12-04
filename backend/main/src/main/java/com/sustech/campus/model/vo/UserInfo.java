package com.sustech.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    private Integer userId;
    private String name;
    private String phone;
    private String email;
    private String imageUrl;
    private Boolean isBlocked;
    private String token;
}
