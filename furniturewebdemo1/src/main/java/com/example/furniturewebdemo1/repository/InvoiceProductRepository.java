package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct,Long> {
}
