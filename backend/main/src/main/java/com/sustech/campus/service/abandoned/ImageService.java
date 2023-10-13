package com.sustech.campus.service.abandoned;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sustech.campus.model.Image;
import com.sustech.campus.repository.ImageRepository;

import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    // 添加图片
    public boolean addImage(byte[] imageBytes) {
        try {
            imageRepository.addImage(imageBytes);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 如果保存过程中出现异常，则返回 false
        }
    }

    // 根据图片ID查询图片
    public Image getImageById(Integer imageId) {
        return imageRepository.customFindById(imageId);
    }

    // 删除图片
    public void deleteImageById(Integer imageId) {
        imageRepository.customDeleteById(imageId);
    }

    // 查询所有图片
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
