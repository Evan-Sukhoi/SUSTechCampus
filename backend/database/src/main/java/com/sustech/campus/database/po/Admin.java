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
    private Integer admin_id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private Integer image_id;

    @Override
    public Integer getImageId() {
        return image_id;
    }

    @Override
    public void setImageId(Integer image_id) {
        this.image_id = image_id;
    }
}
