package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepositpry extends JpaRepository<ProductImage,Long> {
    @Query(value = "select *from product_image as pi where pi.product_id=:product_id",nativeQuery = true)
    List<ProductImage> listProductImageByProductId(@Param("product_id") long product_id);
}
