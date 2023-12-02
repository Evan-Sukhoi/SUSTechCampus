package com.sustech.campus.database.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sustech.campus.database.annotation.DateTimeField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    @DateTimeField
    private Date loginTime;
    private String ipAddress;
    private Integer port;
}
