package com.example.imagesender.controller;

import com.example.imagesender.entity.Image;
import com.example.imagesender.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("timestamp") String timestamp) {
        try {
            imageService.saveImage(file, timestamp);
            return ResponseEntity.ok("Image uploaded successfully with timestamp: " + timestamp);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload image");
        }
    }
    @GetMapping("/getImage/{id}")
    public ResponseEntity<?> getImage(@PathVariable Long id){
        return new ResponseEntity<>(imageService.getImageById(id), HttpStatus.OK);
    }

    @GetMapping("/getImages")
    public ResponseEntity<?> getImages(){
        return new ResponseEntity<>(imageService.getImages(), HttpStatus.OK);
    }
}
