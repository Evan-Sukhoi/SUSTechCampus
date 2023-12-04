package com.sustech.campus.model.param;

import com.sustech.campus.web.annotation.DateParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ReserveParam {
    @ApiModelProperty(value = "房间/讨论间Id",required = true, example = "1")
    @NotNull
    Integer roomId;

    @ApiModelProperty(value = "预约开始日期、时间",required = true, example = "2023-12-31-08-30")
    @DateParam
    @NotNull
    Date startTime;


    @ApiModelProperty(value = "预约结束日期、时间",required = true, example = "2022-12-31-22-30")
    @DateParam
    @NotNull
    Date endTime;

    @ApiModelProperty(value = "预约描述",required = true, example = "这是一条预约")
    @NotNull
    String description;

    @ApiModelProperty(value = "用户id",required = true, example = "1")
    @NotNull
    Integer userId;
}
