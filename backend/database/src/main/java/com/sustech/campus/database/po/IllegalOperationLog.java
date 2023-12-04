package com.sustech.campus.database.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class IllegalOperationLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String operation;
    @DateTimeField
    private Date operationTime;
    private String ipAddress;
    private Integer port;
}
