package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.Product;
import com.example.furniturewebdemo1.repository.ProductRepository;
import com.example.furniturewebdemo1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
