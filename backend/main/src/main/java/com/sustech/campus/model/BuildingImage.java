package com.sustech.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Buildings_Image")
public class BuildingImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "building_ID")
    private int buildingId;

    @Column(name = "image_ID")
    private int imageId;

    // 构造函数
    public BuildingImage() {

    }

    public BuildingImage(int id, int buildingId, int imageId) {
        this.id = id;
        this.buildingId = buildingId;
        this.imageId = imageId;
    }

    // Getter和Setter方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
                "id=" + id +
                ", buildingId=" + buildingId +
                ", imageId=" + imageId +
                '}';
    }
}
