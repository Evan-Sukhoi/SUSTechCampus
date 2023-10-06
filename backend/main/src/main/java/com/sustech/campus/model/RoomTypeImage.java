package com.sustech.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Room_type_Image")
public class RoomTypeImage {
    @Column(name = "room_type_ID")
    private int roomTypeId;

    @Id
    @Column(name = "image_ID")
    private int imageId;

    // 构造函数
    public RoomTypeImage() {

    }

    public RoomTypeImage(int roomTypeId, int imageId) {
        this.roomTypeId = roomTypeId;
        this.imageId = imageId;
    }

    // Getter和Setter方法

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "RoomTypeImage{" +
                ", roomTypeId=" + roomTypeId +
                ", imageId=" + imageId +
                '}';
    }
}

