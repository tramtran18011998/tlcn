package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.InvoiceDetail_Detail;
import com.example.furniturewebdemo1.repository.InvoiceDetail_DetailRepository;
import com.example.furniturewebdemo1.service.InvoiceDetail_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetail_DetailServiceImpl implements InvoiceDetail_DetailService {
    InvoiceDetail_DetailRepository invoiceDetail_detailRepository;

    @Autowired
    public InvoiceDetail_DetailServiceImpl(InvoiceDetail_DetailRepository invoiceDetail_detailRepository){
        this.invoiceDetail_detailRepository=invoiceDetail_detailRepository;
    }

    @Override
    public List<InvoiceDetail_Detail> findAllInvoiceDetail_Detail() {
        return invoiceDetail_detailRepository.findAll();
    }

    @Override
    public Optional<InvoiceDetail_Detail> findInvoiceDetail_DetailById(long id) {
        return invoiceDetail_detailRepository.findById(id);
    }

    @Override
    public void save(InvoiceDetail_Detail invoiceDetail_detail) {
        invoiceDetail_detailRepository.save(invoiceDetail_detail);
    }

    @Override
    public void delete(InvoiceDetail_Detail invoiceDetail_detail) {
        invoiceDetail_detailRepository.delete(invoiceDetail_detail);
    }
}
