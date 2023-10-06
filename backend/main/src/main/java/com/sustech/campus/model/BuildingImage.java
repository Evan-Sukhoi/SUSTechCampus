package com.sustech.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Buildings_Image")
public class BuildingImage {


    @Column(name = "building_ID")
    private int buildingId;

    @Id
    @Column(name = "image_ID")
    private int imageId;

    // 构造函数
    public BuildingImage() {

    }

    public BuildingImage(int buildingId, int imageId) {
        this.buildingId = buildingId;
        this.imageId = imageId;
    }

    // Getter和Setter方法

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "BuildingImage{" +
                ", buildingId=" + buildingId +
                ", imageId=" + imageId +
                '}';
    }
}
