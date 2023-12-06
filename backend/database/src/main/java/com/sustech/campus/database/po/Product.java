package com.sustech.campus.database.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer productId;
    private String subject;
    private String body;
    private Float amount;
    private String shop;
    private Integer inventory;
    private Integer imageId;
}
