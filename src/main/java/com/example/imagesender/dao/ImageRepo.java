package com.example.imagesender.dao;

import com.example.imagesender.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image,Long> {
}
