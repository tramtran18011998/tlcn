package com.example.furniturewebdemo1.service;


import com.example.furniturewebdemo1.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<Supplier> findAllSupplier();
    Optional<Supplier> findSupplierById(long id);
    void save (Supplier supplier);
    void delete (Supplier supplier);
}
