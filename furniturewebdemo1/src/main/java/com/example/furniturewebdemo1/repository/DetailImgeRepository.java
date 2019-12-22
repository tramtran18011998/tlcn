package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.DetailImage;
import com.example.furniturewebdemo1.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailImgeRepository extends JpaRepository<DetailImage,Long> {
    @Query(value = "Select *from detail_image as a where a.detail_id=:detail_id",nativeQuery = true)
    List<DetailImage> listDetailImageByDetailId(@Param("detail_id") long detail_id);
}
