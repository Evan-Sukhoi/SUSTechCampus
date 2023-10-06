package com.sustech.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bus_line")
public class BusLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_line_ID")
    private int busLineId;

    @Column(name = "line_ID")
    private int lineId;

    @Column(name = "station", length = 255)
    private String station;

    @Column(name = "_index_")
    private int index;

    // 构造函数
    public BusLine() {

    }

    public BusLine(int busLineId, int lineId, String station, int index) {
        this.busLineId = busLineId;
        this.lineId = lineId;
        this.station = station;
        this.index = index;
    }

    // Getter和Setter方法

    public int getBusLineId() {
        return busLineId;
    }

    public void setBusLineId(int busLineId) {
        this.busLineId = busLineId;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "BusLine{" +
                "busLineId=" + busLineId +
                ", lineId=" + lineId +
                ", station='" + station + '\'' +
                ", index=" + index +
                '}';
    }
}

