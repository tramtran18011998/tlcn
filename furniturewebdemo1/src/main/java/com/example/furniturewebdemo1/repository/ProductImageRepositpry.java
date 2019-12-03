package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepositpry extends JpaRepository<ProductImage,Long> {
}
