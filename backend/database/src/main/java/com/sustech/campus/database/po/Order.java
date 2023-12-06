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
public class Order {
    @TableId
    private String orderSn;
    private Integer productId;
    private Float amount;
    @DateTimeField
    private Date time;
    private String cdkey;
    private Integer status;
}
