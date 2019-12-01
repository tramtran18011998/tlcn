package com.example.furniturewebdemo1.service;

import com.example.furniturewebdemo1.model.InvoiceDetail;

import java.util.List;
import java.util.Optional;

public interface InvoiceDetailService {
    List<InvoiceDetail> findAllInvoiceDetail();
    Optional<InvoiceDetail> findInvoiceDetailById(long id);
    void save (InvoiceDetail invoiceDetail);
    void delete (InvoiceDetail invoiceDetail);
}
