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
public class IllegalLogInfo {
    private String username;
    @DateTimeField
    private Date operationTime;
    private String operation;
    private String ipAddress;
    private Integer port;
    private Boolean blocked;
}
