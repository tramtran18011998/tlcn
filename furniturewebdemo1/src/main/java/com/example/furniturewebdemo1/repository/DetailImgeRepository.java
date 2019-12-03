package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.DetailImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailImgeRepository extends JpaRepository<DetailImage,Long> {
}
