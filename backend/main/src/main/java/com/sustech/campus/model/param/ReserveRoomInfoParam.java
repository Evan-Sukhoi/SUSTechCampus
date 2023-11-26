package com.sustech.campus.model.param;

import com.sustech.campus.web.annotation.DateParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class ReserveRoomInfoParam {

    @ApiModelProperty(value = "建筑类型",required = true, example = "教学楼")
    @NotNull
    String buildingType;

    @ApiModelProperty(value = "建筑名称",required = true, example = "教学楼A")
    @NotNull
    String buildingName;

    @ApiModelProperty(value = "房间ID",required = true, example = "1")
    @NotNull
    String roomId;
}
