package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.InvoiceDetail;
import com.example.furniturewebdemo1.repository.InvoiceDetailRepository;
import com.example.furniturewebdemo1.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {

    InvoiceDetailRepository invoiceDetailRepository;

    @Autowired
    public InvoiceDetailServiceImpl(InvoiceDetailRepository invoiceDetailRepository){
        this.invoiceDetailRepository=invoiceDetailRepository;
    }

    @Override
    public List<InvoiceDetail> findAllInvoiceDetail() {
        return invoiceDetailRepository.findAll();
    }

    @Override
    public Optional<InvoiceDetail> findInvoiceDetailById(long id) {
        return invoiceDetailRepository.findById(id);
    }

    @Override
    public void save(InvoiceDetail invoiceDetail) {
        invoiceDetailRepository.save(invoiceDetail);
    }

    @Override
    public void delete(InvoiceDetail invoiceDetail) {
        invoiceDetailRepository.delete(invoiceDetail);
    }
}
