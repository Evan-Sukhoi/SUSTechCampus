package com.sustech.campus.model.param;

import com.sustech.campus.web.annotation.DateParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class LoginParam {
    @ApiModelProperty(value = "用户名或邮箱",required = true, example = "abc或abc@gmail.com")
    @NotNull
    String username;

    @ApiModelProperty(value = "密码",required = true, example = "123456")
    @NotNull
    String password;
}
