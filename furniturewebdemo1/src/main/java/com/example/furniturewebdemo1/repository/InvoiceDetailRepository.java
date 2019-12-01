package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail,Long> {
}
