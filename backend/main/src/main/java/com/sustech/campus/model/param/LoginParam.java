package com.sustech.campus.model.param;

import com.sustech.campus.web.annotation.DateParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class LoginParam {
    @ApiModelProperty(value = "用户名",required = true, example = "ikun")
    @NotNull
    String username;

    @ApiModelProperty(value = "密码",required = true, example = "小黑子食不食油饼")
    @NotNull
    String password;
}
