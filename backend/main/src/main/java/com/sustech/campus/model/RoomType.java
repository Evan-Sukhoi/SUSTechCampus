package com.sustech.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Room_type")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_ID")
    private int roomTypeId;

    @Column(name = "type", length = 255)
    private String type;

    @Column(name = "capacity")
    private int capacity;

    // 构造函数
    public RoomType() {

    }

    public RoomType(int roomTypeId, String type, int capacity) {
        this.roomTypeId = roomTypeId;
        this.type = type;
        this.capacity = capacity;
    }

    // Getter和Setter方法

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "roomTypeId=" + roomTypeId +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}

