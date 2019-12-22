package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select c.category_id, c.name from (category as c inner join category_categorytype as cc on c.category_id = cc.category_id) inner join categorytype as ctype on cc.categorytype_id = ctype.categorytype_id\n" +
            " where ctype.categorytype_id=:id",nativeQuery = true)
    List<Category> findByCategoryTypes(@Param("id") long id);
}
