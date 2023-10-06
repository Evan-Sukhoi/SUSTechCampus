package com.sustech.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_ID")
    private int roomId;

    @Column(name = "building_ID")
    private int buildingId;

    @Column(name = "number")
    private int number;

    @Column(name = "room_type_ID")
    private int roomTypeId;

    // 构造函数
    public Room() {

    }

    public Room(int roomId, int buildingId, int number, int roomTypeId) {
        this.roomId = roomId;
        this.buildingId = buildingId;
        this.number = number;
        this.roomTypeId = roomTypeId;
    }

    // Getter和Setter方法

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", buildingId=" + buildingId +
                ", number=" + number +
                ", roomTypeId=" + roomTypeId +
                '}';
    }
}
