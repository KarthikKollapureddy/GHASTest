package com.example.imagesender.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.imagesender.entity.Image;

import java.io.IOException;
import java.util.List;

@Service
public interface ImageService {
     void saveImage(MultipartFile file, String timestamp) throws IOException;
     Image getImageById(Long id);
     List<Image> getImages();
}
