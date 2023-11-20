package com.sustech.campus.controller;

import com.sustech.campus.web.annotation.MappingController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

@MappingController("/fileupload")
public class FileUploadController {

    @Value("${upload-dir}") // 配置文件中指定上传文件保存的目录
    private String uploadDir;

    @RequestMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("image") MultipartFile file) {
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            System.out.println(uploadDir);

            // 构建文件保存路径
            Path filePath = Paths.get(uploadDir, fileName);
            System.out.println(filePath);

            // 保存文件
            Files.write(filePath, file.getBytes());

            // 返回文件保存的相对路径（可根据实际情况修改）
            String relativePath = fileName;

            return new ResponseEntity<>(relativePath, HttpStatus.OK);
        } catch (IOException e) {
            System.out.println("Failed to upload file.");
            return new ResponseEntity<>("Failed to upload file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
