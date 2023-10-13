package com.sustech.campus.database.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Blacklist {
    @TableId(type = IdType.AUTO)
    private Integer list_ID;
    private Integer user_ID;
    private Integer admin_ID;

}


