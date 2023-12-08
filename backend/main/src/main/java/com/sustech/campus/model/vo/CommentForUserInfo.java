package com.sustech.campus.model.vo;

import com.sustech.campus.database.annotation.DateTimeField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentForUserInfo {
    private Integer commentId;
    private Integer userId;
    @DateTimeField
    private Date time;
    private String text;
    private Integer buildingId;
    private Integer score;

}
