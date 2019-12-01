package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;


import com.example.furniturewebdemo1.model.Supplier;
import com.example.furniturewebdemo1.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/supplier")
    public List<Supplier> getAllSupplier(){
        return supplierService.findAllSupplier();
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Supplier supplier=supplierService.findSupplierById(id).orElseThrow(()-> new ResourceNotFoundException("Supplier not found"));
        return ResponseEntity.ok().body(supplier);
    }

    @PostMapping("/supplier")
    public  ResponseEntity<Supplier> createSupplier(@Valid @RequestBody Supplier supplier){
        supplierService.save(supplier);
        return new ResponseEntity<>(supplier, HttpStatus.CREATED);
    }

    @PutMapping("/supplier/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable(value = "id") long id, @Valid @RequestBody Supplier supplier) throws ResourceNotFoundException {
        Supplier currentSupplier= supplierService.findSupplierById(id).orElseThrow(()-> new ResourceNotFoundException("Supplier not found"));

        currentSupplier.setName(supplier.getName());
        currentSupplier.setAddress(supplier.getAddress());
        currentSupplier.setEmail(supplier.getEmail());
        currentSupplier.setPhoneNumber(supplier.getPhoneNumber());

        supplierService.save(currentSupplier);
        return ResponseEntity.ok(currentSupplier);
    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<Supplier> deleteSupplier(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Supplier supplier=supplierService.findSupplierById(id).orElseThrow(()-> new ResourceNotFoundException("Supplier not found"));
        supplierService.delete(supplier);
        return ResponseEntity.ok(supplier);
    }
}
