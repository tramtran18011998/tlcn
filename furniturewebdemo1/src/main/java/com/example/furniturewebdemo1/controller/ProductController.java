package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;
import com.example.furniturewebdemo1.model.Product;
import com.example.furniturewebdemo1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public List<Product> getAllProduct(){
        return productService.findAllProduct();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Product product=productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/product")
    public  ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") long id, @Valid @RequestBody Product product) throws ResourceNotFoundException {
        Product currentProduct= productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));

        currentProduct.setColor(product.getColor());
        currentProduct.setDescription(product.getDescription());
        currentProduct.setDiscountPrice(product.getDiscountPrice());
        currentProduct.setMaterial(product.getMaterial());
        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setQuantity(product.getQuantity());
        currentProduct.setSize(product.getSize());
        currentProduct.setCategory(product.getCategory());
        currentProduct.setSupplier(product.getSupplier());

        productService.save(currentProduct);
        return ResponseEntity.ok(currentProduct);

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Product product=productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        productService.delete(product);
        return ResponseEntity.ok(product);
    }
}
