package com.sustech.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Room_type_Image")
public class RoomTypeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "room_type_ID")
    private int roomTypeId;

    @Column(name = "image_ID")
    private int imageId;

    // 构造函数
    public RoomTypeImage() {

    }

    public RoomTypeImage(int id, int roomTypeId, int imageId) {
        this.id = id;
        this.roomTypeId = roomTypeId;
        this.imageId = imageId;
    }

    // Getter和Setter方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
                "id=" + id +
                ", roomTypeId=" + roomTypeId +
                ", imageId=" + imageId +
                '}';
    }
}

