package com.sustech.campus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_ID")
    private int imageId;

    @Lob
    @Column(name = "image")
    private byte[] image;

    // 构造函数
    public Image() {

    }

    public Image(int imageId, byte[] image) {
        this.imageId = imageId;
        this.image = image;
    }

    // Getter和Setter方法

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                '}';
    }
}
