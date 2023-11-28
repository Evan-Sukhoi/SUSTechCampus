package com.sustech.campus.model.param;

import com.sustech.campus.web.annotation.DateParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class CommentParam {
    @ApiModelProperty(value = "用户ID", required = true, example = "123")
    @NotNull
    Integer userId;

    @ApiModelProperty(value = "评论内容", required = true, example = "这是一条评论")
    @NotNull
    String text;

    @ApiModelProperty(value = "评论照片", example = "[photo1.jpg, photo2.png]")
    List<MultipartFile> commentPhotos;

    @ApiModelProperty(value = "评论时间", required = true, example = "2021-12-31-08-30")
    @DateParam
    @NotNull
    Date time;

    @ApiModelProperty(value = "建筑ID", required = true, example = "123")
    @NotNull
    Integer buildingId;
}
