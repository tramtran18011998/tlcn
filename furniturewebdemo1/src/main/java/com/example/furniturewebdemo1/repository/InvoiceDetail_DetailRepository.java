package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.InvoiceDetail_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetail_DetailRepository extends JpaRepository<InvoiceDetail_Detail,Long> {
}
