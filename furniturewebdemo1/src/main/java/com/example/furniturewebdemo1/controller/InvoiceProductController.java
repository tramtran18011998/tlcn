package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;

import com.example.furniturewebdemo1.model.InvoiceProduct;
import com.example.furniturewebdemo1.service.InvoiceProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceProductController {
    @Autowired
    private InvoiceProductService invoiceProductService;

    @GetMapping("/invoiceproduct")
    public List<InvoiceProduct> getAllInvoiceProduct(){
        return invoiceProductService.findAllInvoiceProduct();
    }

    @GetMapping("/invoiceproduct/{id}")
    public ResponseEntity<InvoiceProduct> getInvoiceProductById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        InvoiceProduct invoiceProduct=invoiceProductService.findInvoiceProductById(id).orElseThrow(()-> new ResourceNotFoundException("InvoiceProduct not found"));
        return ResponseEntity.ok().body(invoiceProduct);
    }

    @PostMapping("/invoiceproduct")
    public ResponseEntity<InvoiceProduct> createInvoiceProduct(@Valid @RequestBody InvoiceProduct invoiceProduct){
        invoiceProductService.save(invoiceProduct);
        return new ResponseEntity<>(invoiceProduct, HttpStatus.CREATED);
    }

    @PutMapping("/invoiceproduct/{id}")
    public ResponseEntity<InvoiceProduct> updateInvoiceProduct(@PathVariable(value = "id") long id, @Valid @RequestBody InvoiceProduct invoiceProduct) throws ResourceNotFoundException {
        InvoiceProduct currentInvoiceProduct= invoiceProductService.findInvoiceProductById(id).orElseThrow(()-> new ResourceNotFoundException("InvoiceProduct not found"));

        currentInvoiceProduct.setTotal(invoiceProduct.getTotal());
        currentInvoiceProduct.setEmployee(invoiceProduct.getEmployee());
        currentInvoiceProduct.setCustomer(invoiceProduct.getCustomer());

        invoiceProductService.save(currentInvoiceProduct);
        return ResponseEntity.ok(currentInvoiceProduct);

    }

    @DeleteMapping("/invoiceproduct/{id}")
    public ResponseEntity<InvoiceProduct> deleteInvoiceProduct(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        InvoiceProduct invoiceProduct=invoiceProductService.findInvoiceProductById(id).orElseThrow(()-> new ResourceNotFoundException("InvoiceProduct not found"));
        invoiceProductService.delete(invoiceProduct);
        return ResponseEntity.ok(invoiceProduct);
    }
}
