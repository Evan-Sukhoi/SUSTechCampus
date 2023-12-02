package com.sustech.campus.database.po;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sustech.campus.database.annotation.DateTimeField;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {
    @TableId(type = IdType.AUTO)
    private Integer reservationId;

    private Integer roomId;

    @DateTimeField
    private Date startTime;

    @DateTimeField
    private Date endTime;

    private Integer userId;

    private String description;
}
