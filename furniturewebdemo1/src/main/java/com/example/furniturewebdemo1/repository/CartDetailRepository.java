package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
}
