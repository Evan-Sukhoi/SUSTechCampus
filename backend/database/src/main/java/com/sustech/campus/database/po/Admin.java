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
public class Admin implements Displayable {
    @TableId(type = IdType.AUTO)
    private short admin_ID;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String imageID;

    @Override
    public String getImageId() {
        return imageID;
    }

    @Override
    public void setImageId(String imageId) {
        this.imageID = imageId;
    }
}
