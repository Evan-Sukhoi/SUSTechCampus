package com.sustech.campus.database.po;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer commentId;
    private Integer userId;
    private Date time;
    private String text;
    private Integer buildingId;
//    DECIMAL(3,2)
    private Integer score;
    private Integer adminId;
}

