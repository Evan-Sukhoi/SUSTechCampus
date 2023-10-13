package com.sustech.campus.database.po;


/**
 * 实体的可播放媒体，如教室的图片、视频
 */

public interface Playable<Entity> {
    /**
     * 获取媒体的ID
     */
    Integer getMediaID();

    /**
     * 设置媒体的ID
     */
    void setMediaID(Integer mediaID);

    /**
     * 获取实体的ID
     */
    Integer getEntityID();

    /**
     * 设置实体的ID
     */
    void setEntityID(Integer entityID);

}
