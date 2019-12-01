package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.Invoiceproduct_Product;
import com.example.furniturewebdemo1.model.PrimaryKey.Invoiceproduct_Product_Id;
import com.example.furniturewebdemo1.repository.InvoiceProduct_ProductRepository;
import com.example.furniturewebdemo1.service.InvoiceProduct_ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceProduct_ProductServiceImpl implements InvoiceProduct_ProductService {

    InvoiceProduct_ProductRepository invoiceProduct_productRepository;

    @Autowired
    public InvoiceProduct_ProductServiceImpl(InvoiceProduct_ProductRepository invoiceProduct_productRepository){
        this.invoiceProduct_productRepository=invoiceProduct_productRepository;
    }

    @Override
    public List<Invoiceproduct_Product> findAllInvoiceproduct_Product() {
        return invoiceProduct_productRepository.findAll();
    }

    @Override
    public Optional<Invoiceproduct_Product> findInvoiceproduct_ProductById(long id) {
        return invoiceProduct_productRepository.findById(id);
    }



    @Override
    public void save(Invoiceproduct_Product invoiceproduct_product) {
        invoiceProduct_productRepository.save(invoiceproduct_product);
    }

    @Override
    public void delete(Invoiceproduct_Product invoiceproduct_product) {
        invoiceProduct_productRepository.delete(invoiceproduct_product);
    }
}
