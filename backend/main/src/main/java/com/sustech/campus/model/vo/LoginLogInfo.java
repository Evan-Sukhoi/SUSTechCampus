package com.sustech.campus.model.vo;

import com.sustech.campus.database.annotation.DateTimeField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginLogInfo {
    private Integer userId;
    private String username;
    private String email;
    private String ipAddress;
    @DateTimeField
    private Date loginTime;
    private Integer port;
    private Boolean blocked;
}
