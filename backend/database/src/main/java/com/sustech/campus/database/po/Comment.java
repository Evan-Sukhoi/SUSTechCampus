package com.sustech.campus.database.po;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer comment_ID;
    private Integer user_ID;
    private Time time;
    private String text;
    private Integer building_ID;
//    DECIMAL(3,2)
    private Double score;
    private Integer admin_ID;
}

