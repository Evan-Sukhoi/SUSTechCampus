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
public class Room{
    @TableId(type = IdType.AUTO)
    private Integer roomId;
    private Integer buildingId;
    private Integer number;
    private Integer roomTypeId;
}
