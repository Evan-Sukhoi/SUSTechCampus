package com.sustech.campus.model.param;

import com.sustech.campus.web.annotation.DateParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ReserveUpdateParam {

    @ApiModelProperty(value = "预约ID",required = true, example = "1")
    @NotNull
    Long reservation_id;

    @ApiModelProperty(value = "房间/讨论间Id",required = true, example = "1")
    @NotNull
    Integer room_id;

    @ApiModelProperty(value = "预约开始日期、时间",required = true, example = "2023-12-31-08-30")
    @DateParam
    @NotNull
    Date startTime;

    @ApiModelProperty(value = "预约结束日期、时间",required = true, example = "2022-12-31-22-30")
    @DateParam
    @NotNull
    Date endTime;

    @ApiModelProperty(value = "用户ID",required = true, example = "1")
    @NotNull
    Integer userId;
}
