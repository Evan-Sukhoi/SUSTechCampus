package com.sustech.campus.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AdminLoginParam {
    @ApiModelProperty(value = "用户名",required = true, example = "abc")
    @NotNull
    String username;

    @ApiModelProperty(value = "密码",required = true, example = "123456")
    @NotNull
    String password;
}
