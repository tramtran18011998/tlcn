package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;
import com.example.furniturewebdemo1.model.Invoiceproduct_Product;
import com.example.furniturewebdemo1.repository.InvoiceProduct_ProductRepository;
import com.example.furniturewebdemo1.service.InvoiceProduct_ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceProduct_ProductController {
    @Autowired
    private InvoiceProduct_ProductService invoiceProduct_productService;
    @Autowired
    private InvoiceProduct_ProductRepository invoiceProduct_productRepository;

    @GetMapping("/invoiceproduct-product")
    public List<Invoiceproduct_Product> getAllInvoiceproduct_Product(){
        return invoiceProduct_productService.findAllInvoiceproduct_Product();
    }

    @GetMapping("/invoiceproduct-product/{id}")
    public ResponseEntity<Invoiceproduct_Product> getInvoiceproduct_ProductById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Invoiceproduct_Product invoiceproduct_product=invoiceProduct_productService.findInvoiceproduct_ProductById(id).orElseThrow(()-> new ResourceNotFoundException("Invoiceproduct_Product not found"));
        return ResponseEntity.ok().body(invoiceproduct_product);
    }

    @PostMapping("/invoiceproduct-product")
    public  ResponseEntity<Invoiceproduct_Product> createInvoiceproduct_Product(@Valid @RequestBody Invoiceproduct_Product invoiceproduct_product){
        invoiceProduct_productService.save(invoiceproduct_product);
        return new ResponseEntity<>(invoiceproduct_product, HttpStatus.CREATED);
    }

    @PutMapping("/invoiceproduct-product/{id}")
    public ResponseEntity<Invoiceproduct_Product> updateInvoiceproduct_Product(@PathVariable(value = "id") long id, @Valid @RequestBody Invoiceproduct_Product invoiceproduct_product) throws ResourceNotFoundException {
        Invoiceproduct_Product currentInvoiceproduct_Product= invoiceProduct_productService.findInvoiceproduct_ProductById(id).orElseThrow(()-> new ResourceNotFoundException("Invoiceproduct_Product not found"));

        currentInvoiceproduct_Product.setProduct(invoiceproduct_product.getProduct());
        currentInvoiceproduct_Product.setPrice(invoiceproduct_product.getPrice());
        currentInvoiceproduct_Product.setQuantity(invoiceproduct_product.getQuantity());
        currentInvoiceproduct_Product.setTotalprice(invoiceproduct_product.getTotalprice());
        currentInvoiceproduct_Product.setInvoiceProduct(invoiceproduct_product.getInvoiceProduct());
        invoiceProduct_productService.save(currentInvoiceproduct_Product);
        return ResponseEntity.ok(currentInvoiceproduct_Product);

    }

    @DeleteMapping("/invoiceproduct-product/{id}")
    public ResponseEntity<Invoiceproduct_Product> deleteInvoiceproduct_Product(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Invoiceproduct_Product invoiceproduct_product=invoiceProduct_productService.findInvoiceproduct_ProductById(id).orElseThrow(()-> new ResourceNotFoundException("Invoiceproduct_Product not found"));
        invoiceProduct_productService.delete(invoiceproduct_product);
        return ResponseEntity.ok(invoiceproduct_product);
    }

    @GetMapping("/invoiceproduct-product/{id1}/{id2}")
    public ResponseEntity<Invoiceproduct_Product> getById(@PathVariable(value = "id1") long id1,@PathVariable(value = "id2") long id2) {
        Invoiceproduct_Product invoiceproduct_product=invoiceProduct_productRepository.findAllInvoiceP_PById(id1,id2);
        return ResponseEntity.ok().body(invoiceproduct_product);
    }
}
