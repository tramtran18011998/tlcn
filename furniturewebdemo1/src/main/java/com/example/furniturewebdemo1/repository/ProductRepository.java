package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
