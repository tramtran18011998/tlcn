package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
