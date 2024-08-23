package com.example.imagesender.service;

import com.example.imagesender.dao.ImageRepo;
import com.example.imagesender.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    ImageRepo imageRepository;
    @Transactional
    @Override
    public void saveImage(MultipartFile file, String timestamp) throws IOException {
        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setData(file.getBytes());
        image.setTimestamp(timestamp);
        imageRepository.save(image);
    }
    @Override
    public Image getImageById(Long id){
        Image imageEntity = imageRepository.findById(id).get();
        if (imageEntity.getData() != null) {
            String base64Data = Base64.getEncoder().encodeToString(imageEntity.getData());
            imageEntity.setDataAsBase64(base64Data);
        }
       return imageEntity;
    }

    @Override
    public List<Image> getImages() {
        List<Image> images = imageRepository.findAll();
        for(Image imageEntity : images){
            if (imageEntity.getData() != null) {
                String base64Data = Base64.getEncoder().encodeToString(imageEntity.getData());
                imageEntity.setDataAsBase64(base64Data);
            }
        }
        return images;
    }
}
