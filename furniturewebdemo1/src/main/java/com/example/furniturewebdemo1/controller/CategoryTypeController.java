package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;
import com.example.furniturewebdemo1.model.CategoryType;
import com.example.furniturewebdemo1.repository.CategoryTypeRepository;
import com.example.furniturewebdemo1.service.CategoryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryTypeController {

    @Autowired
    private CategoryTypeService categoryTypeService;

    @Autowired
    private CategoryTypeRepository categoryTypeRepository;

    @GetMapping("/categorytype")
    public List<CategoryType> getAllCategoryType(){
        return categoryTypeService.findAllCategoryTypes();
    }

    @GetMapping("/categorytype/{id}")
    public ResponseEntity<CategoryType> getCategoryTypeById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        CategoryType categoryType=categoryTypeService.findCategoryTypesById(id).orElseThrow(()-> new ResourceNotFoundException("CategoryType not found"));
        return ResponseEntity.ok().body(categoryType);
    }

    @GetMapping("/categorytypename/{name}")
    public ResponseEntity<CategoryType> getCategoryTypeByName(@PathVariable(value = "name") String name) throws ResourceNotFoundException {
        CategoryType categoryType=categoryTypeRepository.findCategoryTypeByName(name);
        return ResponseEntity.ok().body(categoryType);
    }

    @PostMapping("/categorytype")
    public  ResponseEntity<CategoryType> createCategoryType(@Valid @RequestBody CategoryType categoryType){
        categoryTypeService.save(categoryType);
        return new ResponseEntity<>(categoryType, HttpStatus.CREATED);
    }

    @PutMapping("/categorytype/{id}")
    public ResponseEntity<CategoryType> updateCategoryType(@PathVariable(value = "id") long id, @Valid @RequestBody CategoryType categoryType) throws ResourceNotFoundException {
        CategoryType currentCategoryType= categoryTypeService.findCategoryTypesById(id).orElseThrow(()-> new ResourceNotFoundException("CategoryType not found"));

        currentCategoryType.setName(categoryType.getName());
        categoryTypeService.save(currentCategoryType);
        return ResponseEntity.ok(currentCategoryType);

    }

    @DeleteMapping("/categorytype/{id}")
    public ResponseEntity<CategoryType> deleteCategoryType(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        CategoryType categoryType=categoryTypeService.findCategoryTypesById(id).orElseThrow(()-> new ResourceNotFoundException("CategoryType not found"));

        //categoryTypeService.delete(categoryType);
        categoryTypeRepository.delete(categoryType);
        return ResponseEntity.ok(categoryType);
    }
}
