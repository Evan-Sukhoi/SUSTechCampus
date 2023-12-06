package com.sustech.campus.alipay.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderParam {
    @ApiModelProperty(value = "订单号", required = true, example = "ABCDE1234567890")
    @NotNull
    String orderSn;

    @ApiModelProperty(value = "商品名称", required = true)
    @NotNull
    String subject;

    @ApiModelProperty(value = "商品描述", required = true)
    @NotNull
    String body;

    @ApiModelProperty(value = "金额", required = true)
    @NotNull
    Float amount;


}
