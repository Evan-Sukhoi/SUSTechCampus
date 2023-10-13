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
public class bus_line {
    @TableId(type = IdType.AUTO)
    private Integer bus_line_ID;
    private Integer line_ID;
    private String station;
    private Integer _index_;
}
