package com.example.furniturewebdemo1.service;

import com.example.furniturewebdemo1.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProduct();
    Optional<Product> findProductById(long id);
    void save (Product product);
    void delete (Product product);

    String storeImg(MultipartFile file) throws IOException;
}
