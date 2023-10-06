package com.sustech.campus.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_ID")
    private int commentId;

    @Column(name = "user_ID")
    private int userId;

    @Column(name = "time")
    private Timestamp time;

    @Lob
    @Column(name = "text")
    private String text;

    @Column(name = "building_ID")
    private int buildingId;

    @Column(name = "score", precision = 3, scale = 2)
    private double score;

    @Column(name = "admin_ID")
    private int adminId;

    // 构造函数
    public Comment() {

    }

    public Comment(int commentId, int userId, Timestamp time, String text, int buildingId, double score, int adminId) {
        this.commentId = commentId;
        this.userId = userId;
        this.time = time;
        this.text = text;
        this.buildingId = buildingId;
        this.score = score;
        this.adminId = adminId;
    }

    // Getter和Setter方法

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", time=" + time +
                ", buildingId=" + buildingId +
                ", score=" + score +
                '}';
    }
}

