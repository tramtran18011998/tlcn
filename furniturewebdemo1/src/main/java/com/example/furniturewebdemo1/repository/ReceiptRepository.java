package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt,Long> {
}
