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
public class RegisterParam {
    @ApiModelProperty(value = "用户名",required = true, example = "ikun")
    @NotNull
    String username;

    @ApiModelProperty(value = "密码",required = true, example = "小黑子食不食油饼")
    @NotNull
    String password;


    @ApiModelProperty(value = "邮箱",required = true, example = "ikun@mail.sustech.edu.cn")
    @NotNull
    String email;

    @ApiModelProperty(value = "电话号码",required = true, example = "12345678910")
    @NotNull
    Long phoneNumber;

    @ApiModelProperty(value = "头像",required = true, example = "https://www.baidu.com/img/flexible/logo/pc/result.png")
    @NotNull
    MultipartFile image;
}
