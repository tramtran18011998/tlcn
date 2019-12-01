package com.example.furniturewebdemo1.service;

import com.example.furniturewebdemo1.model.Invoiceproduct_Product;
import com.example.furniturewebdemo1.model.PrimaryKey.Invoiceproduct_Product_Id;

import java.util.List;
import java.util.Optional;

public interface InvoiceProduct_ProductService {
    List<Invoiceproduct_Product> findAllInvoiceproduct_Product();
    Optional<Invoiceproduct_Product> findInvoiceproduct_ProductById(long id);
    //Optional<Invoiceproduct_Product> findInvoiceproduct_ProductById2(Invoiceproduct_Product_Id id);
    void save (Invoiceproduct_Product invoiceproduct_product);
    void delete (Invoiceproduct_Product invoiceproduct_product);
}
