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
public class RoomType {
    @TableId(type = IdType.AUTO)
    private Integer roomTypeId;
    private String type;
    private Integer capacity;

    public Integer getRoom_type_ID() {
        return roomTypeId;
    }
}