package com.sustech.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_ID")
    private int buildingId;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "open_time")
    private java.sql.Time openTime;

    @Column(name = "close_time")
    private java.sql.Time closeTime;

    @Lob
    @Column(name = "video")
    private byte[] video;

    @Lob
    @Column(name = "introduction")
    private String introduction;

    @Column(name = "nearest_station", length = 255)
    private String nearestStation;

    @Column(name = "comment_ID")
    private int commentId;

    // 构造函数
    public Building() {

    }

    public Building(int buildingId, String name, java.sql.Time openTime, java.sql.Time closeTime, byte[] video, String introduction, String nearestStation, int commentId) {
        this.buildingId = buildingId;
        this.name = name;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.video = video;
        this.introduction = introduction;
        this.nearestStation = nearestStation;
        this.commentId = commentId;
    }

    // Getter和Setter方法

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.sql.Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(java.sql.Time openTime) {
        this.openTime = openTime;
    }

    public java.sql.Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(java.sql.Time closeTime) {
        this.closeTime = closeTime;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(String nearestStation) {
        this.nearestStation = nearestStation;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", name='" + name + '\'' +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", introduction='" + introduction + '\'' +
                ", nearestStation='" + nearestStation + '\'' +
                '}';
    }
}

