package com.sustech.campus.database.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sustech.campus.database.annotation.TimeField;
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
@TableName("building")
public class Building implements Displayable {
    @TableId(type = IdType.AUTO)
    private Integer buildingId;
    private String name;
    @TimeField
    private Date openTime;
    @TimeField
    private Date closeTime;
    private String locationName;
    private String introduction;
    private String nearestStation;
    private String videoUrl;
    private Integer coverId;
    private String buildingType;

    @Override
    public Integer getImageId() {
        return coverId;
    }

    @Override
    public void setImageId(Integer imageId) {
        this.coverId = imageId;
    }
}
