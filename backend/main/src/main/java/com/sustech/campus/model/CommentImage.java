package com.sustech.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comment_ID_Image")
public class CommentImage {

    @Column(name = "comment_ID")
    private int commentId;

    @Id
    @Column(name = "image_ID")
    private int imageId;

    // 构造函数
    public CommentImage() {

    }

    public CommentImage(int commentId, int imageId) {
        this.commentId = commentId;
        this.imageId = imageId;
    }

    // Getter和Setter方法
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
                ", commentId=" + commentId +
                ", imageId=" + imageId +
                '}';
    }
}

