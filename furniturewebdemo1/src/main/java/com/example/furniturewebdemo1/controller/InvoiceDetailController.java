package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;
import com.example.furniturewebdemo1.model.InvoiceDetail;
import com.example.furniturewebdemo1.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceDetailController {
    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @GetMapping("/invoicedetail")
    public List<InvoiceDetail> getAllInvoiceDetail(){
        return invoiceDetailService.findAllInvoiceDetail();
    }

    @GetMapping("/invoicedetail/{id}")
    public ResponseEntity<InvoiceDetail> getInvoiceDetailById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        InvoiceDetail invoiceDetail=invoiceDetailService.findInvoiceDetailById(id).orElseThrow(()-> new ResourceNotFoundException("InvoiceDetail not found"));
        return ResponseEntity.ok().body(invoiceDetail);
    }

    @PostMapping("/invoicedetail")
    public  ResponseEntity<InvoiceDetail> createInvoiceDetail(@Valid @RequestBody InvoiceDetail invoiceDetail){
        invoiceDetailService.save(invoiceDetail);
        return new ResponseEntity<>(invoiceDetail, HttpStatus.CREATED);
    }

    @PutMapping("/invoicedetail/{id}")
    public ResponseEntity<InvoiceDetail> updateInvoiceDetail(@PathVariable(value = "id") long id, @Valid @RequestBody InvoiceDetail invoiceDetail) throws ResourceNotFoundException {
        InvoiceDetail currentInvoiceDetail= invoiceDetailService.findInvoiceDetailById(id).orElseThrow(()-> new ResourceNotFoundException("InvoiceDetail not found"));

        currentInvoiceDetail.setTotal(invoiceDetail.getTotal());
        currentInvoiceDetail.setEmployee(invoiceDetail.getEmployee());
        currentInvoiceDetail.setCustomer(invoiceDetail.getCustomer());

        invoiceDetailService.save(currentInvoiceDetail);
        return ResponseEntity.ok(currentInvoiceDetail);

    }

    @DeleteMapping("/invoicedetail/{id}")
    public ResponseEntity<InvoiceDetail> deleteInvoiceDetail(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        InvoiceDetail invoiceDetail=invoiceDetailService.findInvoiceDetailById(id).orElseThrow(()-> new ResourceNotFoundException("InvoiceDetail not found"));
        invoiceDetailService.delete(invoiceDetail);
        return ResponseEntity.ok(invoiceDetail);
    }
}
