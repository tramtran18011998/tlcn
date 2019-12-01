package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<Detail,Long> {
}
