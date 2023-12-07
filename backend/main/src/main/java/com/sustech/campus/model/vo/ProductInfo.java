package com.sustech.campus.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInfo {
    private Integer productId;
    private String subject;
    private String body;
    private Float amount;
    private String shop;
    private Integer inventory;
    private String imageUrl;
}
