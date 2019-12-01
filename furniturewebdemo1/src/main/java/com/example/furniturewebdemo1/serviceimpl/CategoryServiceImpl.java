package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.Category;
import com.example.furniturewebdemo1.repository.CategoryRepository;
import com.example.furniturewebdemo1.repository.CategoryTypeRepository;
import com.example.furniturewebdemo1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl (CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
