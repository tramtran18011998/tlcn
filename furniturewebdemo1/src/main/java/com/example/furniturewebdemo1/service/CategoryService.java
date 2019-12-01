package com.example.furniturewebdemo1.service;

import com.example.furniturewebdemo1.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllCategory();
    Optional<Category> findCategoryById(long id);
    void save (Category category);
    void delete (Category category);
}
