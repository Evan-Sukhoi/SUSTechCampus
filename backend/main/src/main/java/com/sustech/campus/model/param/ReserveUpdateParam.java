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
    Long reservationID;

    @ApiModelProperty(value = "房间/讨论间Id",required = true, example = "1")
    @NotNull
    Integer roomId;

    @ApiModelProperty(value = "部门",required = true, example = "教务处")
    @NotNull
    String department;

    @ApiModelProperty(value = "预约开始日期、时间",required = true, example = "2023-12-31-08-30")
    @DateParam
    @NotNull
    Date startTime;

    @ApiModelProperty(value = "预约结束日期、时间",required = true, example = "2022-12-31-22-30")
    @DateParam
    @NotNull
    Date endTime;

    @ApiModelProperty(value = "日期",required = true, example = "2021/9/10")
    @NotNull
    String date;

    @ApiModelProperty(value = "建筑类型",required = true, example = "教学楼")
    @NotNull
    String buildingType;

    @ApiModelProperty(value = "建筑名称",required = true, example = "教学楼A")
    @NotNull
    String buildingName;
}
