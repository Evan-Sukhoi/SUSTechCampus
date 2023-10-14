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
    private Integer building_ID;
    private String name;
    private Time openTime;
    private Time closeTime;
    private String location_name;
    private String introduction;
    private String nearest_station;
    private String video_url;
    private Integer cover_ID;

    @Override
    public Integer getImageId() {
        return cover_ID;
    }

    @Override
    public void setImageId(Integer imageId) {
        this.cover_ID = imageId;
    }
}
