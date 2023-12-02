package com.sustech.campus.model.vo;


import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentInfo {
    private Integer commentId;
    private Integer userId;
    private Date time;
    private String text;
    private Integer buildingId;
    private Integer score;

    private String username;

    private String userImageUrl;

    private List<String> imageUrl;
    private Integer adminId;
}
