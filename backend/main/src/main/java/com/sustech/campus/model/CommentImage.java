package com.sustech.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comment_ID_Image")
public class CommentImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment_ID")
    private int commentId;

    @Column(name = "image_ID")
    private int imageId;

    // 构造函数
    public CommentImage() {

    }

    public CommentImage(int id, int commentId, int imageId) {
        this.id = id;
        this.commentId = commentId;
        this.imageId = imageId;
    }

    // Getter和Setter方法

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "CommentImage{" +
                "id=" + id +
                ", commentId=" + commentId +
                ", imageId=" + imageId +
                '}';
    }
}

