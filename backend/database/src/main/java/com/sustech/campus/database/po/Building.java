package com.sustech.campus.database.po;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("building")
public class Building implements Displayable {
    @TableId(type = IdType.AUTO)
    private Integer building_id;
    private String name;
    private Time open_time;
    private Time close_time;
    private String location_name;
    private String introduction;
    private String nearest_station;
    private String video_url;
    private Integer cover_id;

    @Override
    public Integer getImageId() {
        return cover_id;
    }

    @Override
    public void setImageId(Integer imageId) {
        this.cover_id = imageId;
    }
}
