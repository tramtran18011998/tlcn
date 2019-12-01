package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerType,Long> {
}
