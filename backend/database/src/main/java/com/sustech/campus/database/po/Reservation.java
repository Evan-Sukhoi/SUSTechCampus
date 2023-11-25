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
public class Reservation {
    @TableId(type = IdType.AUTO)
    private Integer reservation_id;

    private Integer room_id;

    private Date start_time;

    private Date end_time;

    private Integer user_id;
}
