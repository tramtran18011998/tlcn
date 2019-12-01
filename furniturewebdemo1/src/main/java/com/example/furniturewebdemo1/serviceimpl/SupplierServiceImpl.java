package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.Supplier;
import com.example.furniturewebdemo1.repository.SupplierRepository;
import com.example.furniturewebdemo1.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository){
        this.supplierRepository=supplierRepository;
    }


    @Override
    public List<Supplier> findAllSupplier() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> findSupplierById(long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void delete(Supplier supplier) {
        supplierRepository.delete(supplier);
    }
}
