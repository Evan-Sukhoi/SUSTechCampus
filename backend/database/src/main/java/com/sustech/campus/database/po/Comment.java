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
    private Integer comment_id;
    private Integer user_id;
    private Date time;
    private String text;
    private Integer building_id;
//    DECIMAL(3,2)
    private Double score;
    private Integer admin_id;
}

