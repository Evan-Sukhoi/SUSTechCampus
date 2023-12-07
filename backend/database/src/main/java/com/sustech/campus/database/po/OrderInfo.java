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
public class OrderInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Long orderSn;
    private Integer productId;
    private Float amount;
    @DateTimeField
    private Date time;
    private String cdkey;
    private Integer status;
    private String buyerId;
}
