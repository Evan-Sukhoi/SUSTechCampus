package com.sustech.campus.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterParam {
    @ApiModelProperty(value = "用户名",required = true, example = "ikun")
    @NotNull
    String username;

    @ApiModelProperty(value = "密码",required = true, example = "abc123456")
    @NotNull
    String password;

    @ApiModelProperty(value = "邮箱",required = true, example = "ikun@mail.sustech.edu.cn")
    @NotNull
    @Email
    String email;

    @ApiModelProperty(value = "电话号码",required = true, example = "12345678910")
    @NotNull
    String phoneNumber;

    @ApiModelProperty(value = "头像", example = "https://www.baidu.com/img/flexible/logo/pc/result.png")
    MultipartFile image;

    @ApiModelProperty(value = "验证码",required = true, example = "123456")
    @Size(min=6,max=6,message="6位")
    Integer authCode;
}
