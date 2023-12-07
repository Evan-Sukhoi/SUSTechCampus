package com.sustech.campus.database.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentLike {
    @TableId(type = IdType.AUTO)
    private Integer likeId;
    private Integer commentId;
    private Integer userId;
}
