package com.sustech.campus.database.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 建筑媒体（图片或视频）
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuildingMedia implements Playable<Building> {
    private Integer fileId;
    private Integer buildingId;

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
        buildingId = entityID;
    }

    @Override
    public Integer getEntityID() {
        return buildingId;
    }
}
