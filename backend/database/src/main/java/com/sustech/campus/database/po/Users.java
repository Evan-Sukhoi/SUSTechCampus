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
public class Users {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String image;
}
