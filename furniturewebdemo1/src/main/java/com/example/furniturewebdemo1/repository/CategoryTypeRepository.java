package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.Category;
import com.example.furniturewebdemo1.model.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryTypeRepository extends JpaRepository<CategoryType,Long> {

    @Query(value = "select *from categorytype as ctype where ctype.name =:name",nativeQuery = true)
    CategoryType findCategoryTypeByName(@Param("name") String name);
}
