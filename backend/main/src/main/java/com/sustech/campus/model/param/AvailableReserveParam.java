package com.sustech.campus.model.param;

import com.sustech.campus.database.annotation.DateField;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AvailableReserveParam {
    @ApiModelProperty(value = "建筑ID", required = true, example = "123")
    @NotNull
    Integer buildingId;

    @ApiModelProperty(value = "日期", required = true, example = "123")
    @NotNull
    @DateField
    Date date;
}
