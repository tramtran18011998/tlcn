package com.example.furniturewebdemo1.service;



import com.example.furniturewebdemo1.model.InvoiceDetail_Detail;

import java.util.List;
import java.util.Optional;

public interface InvoiceDetail_DetailService {
    List<InvoiceDetail_Detail> findAllInvoiceDetail_Detail();
    Optional<InvoiceDetail_Detail> findInvoiceDetail_DetailById(long id);
    void save (InvoiceDetail_Detail invoiceDetail_detail);
    void delete (InvoiceDetail_Detail invoiceDetail_detail);
}
