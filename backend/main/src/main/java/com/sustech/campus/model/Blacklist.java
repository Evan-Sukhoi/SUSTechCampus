package com.sustech.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Blacklist")
public class Blacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_ID")
    private int listId;

    @Column(name = "user_ID")
    private int userId;

    @Column(name = "admin_ID")
    private int adminId;

    // 构造函数
    public Blacklist() {

    }

    public Blacklist(int listId, int userId, int adminId) {
        this.listId = listId;
        this.userId = userId;
        this.adminId = adminId;
    }

    // Getter和Setter方法

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Blacklist{" +
                "listId=" + listId +
                ", userId=" + userId +
                ", adminId=" + adminId +
                '}';
    }
}

