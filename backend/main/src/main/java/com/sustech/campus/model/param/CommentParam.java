package com.sustech.campus.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CommentParam {
    @ApiModelProperty(value = "用户ID", required = true, example = "123")
    @NotNull
    private Long userId;

    @ApiModelProperty(value = "评论内容", required = true, example = "这是一条评论")
    @NotNull
    private String content;

    @ApiModelProperty(value = "评论照片", example = "[photo1.jpg, photo2.png]")
    private List<MultipartFile> commentPhotos;

    @ApiModelProperty(value = "评论时间", required = true)
    @NotNull
    private String commentTime; // Assuming you want to pass the comment time as a string

    // Additional properties or validation annotations can be added as needed
}
