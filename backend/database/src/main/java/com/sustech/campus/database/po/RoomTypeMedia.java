package com.sustech.campus.database.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教室媒体（图片或视频）
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomTypeMedia implements Playable<Room>{
    private Integer fileId;
    private Integer roomId;

    @Override
    public void setMediaID(Integer mediaId) {
        fileId = mediaId;
    }

    @Override
    public Integer getMediaID() {
        return fileId;
    }

    @Override
    public void setEntityID(Integer entityID) {
        roomId = entityID;
    }

    @Override
    public Integer getEntityID() {
        return roomId;
    }
}
