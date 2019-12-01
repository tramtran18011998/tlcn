package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;

import com.example.furniturewebdemo1.model.InvoiceDetail_Detail;
import com.example.furniturewebdemo1.service.InvoiceDetail_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceDetail_DetailController {
    @Autowired
    private InvoiceDetail_DetailService invoiceDetail_detailService;

    @GetMapping("/invoicedetail-detail")
    public List<InvoiceDetail_Detail> getAllInvoiceDetail_Detail(){
        return invoiceDetail_detailService.findAllInvoiceDetail_Detail();
    }

    @GetMapping("/invoicedetail-detail/{id}")
    public ResponseEntity<InvoiceDetail_Detail> getInvoiceDetail_DetailById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        InvoiceDetail_Detail invoiceDetail_detail=invoiceDetail_detailService.findInvoiceDetail_DetailById(id).orElseThrow(()-> new ResourceNotFoundException("InvoiceDetail_Detail not found"));
        return ResponseEntity.ok().body(invoiceDetail_detail);
    }

    @PostMapping("/invoicedetail-detail")
    public  ResponseEntity<InvoiceDetail_Detail> createInvoiceDetail_Detail(@Valid @RequestBody InvoiceDetail_Detail invoiceDetail_detail){
        invoiceDetail_detailService.save(invoiceDetail_detail);
        return new ResponseEntity<>(invoiceDetail_detail, HttpStatus.CREATED);
    }

    @PutMapping("/invoicedetail-detail/{id}")
    public ResponseEntity<InvoiceDetail_Detail> updateInvoiceDetail_Detail(@PathVariable(value = "id") long id, @Valid @RequestBody InvoiceDetail_Detail invoiceDetail_detail) throws ResourceNotFoundException {
        InvoiceDetail_Detail currentInvoiceDetail_Detail= invoiceDetail_detailService.findInvoiceDetail_DetailById(id).orElseThrow(()-> new ResourceNotFoundException("InvoiceDetail_Detail not found"));

        currentInvoiceDetail_Detail.setDetail(invoiceDetail_detail.getDetail());
        currentInvoiceDetail_Detail.setPrice(invoiceDetail_detail.getPrice());
        currentInvoiceDetail_Detail.setQuantity(invoiceDetail_detail.getQuantity());
        currentInvoiceDetail_Detail.setTotalprice(invoiceDetail_detail.getTotalprice());
        currentInvoiceDetail_Detail.setInvoiceDetail(invoiceDetail_detail.getInvoiceDetail());
        invoiceDetail_detailService.save(currentInvoiceDetail_Detail);
        return ResponseEntity.ok(currentInvoiceDetail_Detail);

    }

    @DeleteMapping("/invoicedetail-detail/{id}")
    public ResponseEntity<InvoiceDetail_Detail> deleteInvoiceDetail_Detail(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        InvoiceDetail_Detail invoiceDetail_detail=invoiceDetail_detailService.findInvoiceDetail_DetailById(id).orElseThrow(()-> new ResourceNotFoundException("InvoiceDetail_Detail not found"));
        invoiceDetail_detailService.delete(invoiceDetail_detail);
        return ResponseEntity.ok(invoiceDetail_detail);
    }
}
