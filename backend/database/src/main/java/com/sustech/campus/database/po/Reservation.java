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
public class Reservation {
    @TableId(type = IdType.AUTO)
    private Integer reservationId;
    private Integer roomId;
    private Time startTime;
    private Time endTime;
    private Integer userId;
}
