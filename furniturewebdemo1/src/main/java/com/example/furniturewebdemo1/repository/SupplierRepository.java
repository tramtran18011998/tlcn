package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
