package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.Product;
import com.example.furniturewebdemo1.model.ProductImage;
import com.example.furniturewebdemo1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

//    @Query(value = "select *from product_image as pi where pi.product_id=:product_id",nativeQuery = true)
//    List<ProductImage> listProductImageByProductId(@Param("product_id") long product_id);
}
