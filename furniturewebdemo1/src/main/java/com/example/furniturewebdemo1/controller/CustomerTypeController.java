package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;

import com.example.furniturewebdemo1.model.CustomerType;
import com.example.furniturewebdemo1.repository.CustomerTypeRepository;
import com.example.furniturewebdemo1.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerTypeController {
    @Autowired
    private CustomerTypeService customerTypeService;

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @GetMapping("/customertype")
    public List<CustomerType> getAllCustomerType(){
        return customerTypeService.findAllCustomerType();
    }

    @GetMapping("/customertype/{id}")
    public ResponseEntity<CustomerType> getCustomerTypeById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        CustomerType customerType=customerTypeService.findCustomerTypeById(id).orElseThrow(()-> new ResourceNotFoundException("Supplier not found"));
        return ResponseEntity.ok().body(customerType);
    }

    //get by name
    @GetMapping("/customertypename/{name}")
    public ResponseEntity<CustomerType> getCustomerTypeByName(@PathVariable(value = "name") String name) throws ResourceNotFoundException {
        CustomerType customerType=customerTypeRepository.getCustomerTypeByName(name);
        return ResponseEntity.ok().body(customerType);
    }

    @PostMapping("/customertype")
    public  ResponseEntity<CustomerType> createCustomerType(@Valid @RequestBody CustomerType customerType){
        customerTypeService.save(customerType);
        return new ResponseEntity<>(customerType, HttpStatus.CREATED);
    }

    @PutMapping("/customertype/{id}")
    public ResponseEntity<CustomerType> updateCustomerType(@PathVariable(value = "id") long id, @Valid @RequestBody CustomerType customerType) throws ResourceNotFoundException {
        CustomerType currentCustomerType= customerTypeService.findCustomerTypeById(id).orElseThrow(()-> new ResourceNotFoundException("CustomerType not found"));

        currentCustomerType.setName(customerType.getName());

        customerTypeService.save(currentCustomerType);
        return ResponseEntity.ok(currentCustomerType);
    }

    @DeleteMapping("/customertype/{id}")
    public ResponseEntity<CustomerType> deleteCustomerType(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        CustomerType customerType=customerTypeService.findCustomerTypeById(id).orElseThrow(()-> new ResourceNotFoundException("CustomerType not found"));
        customerTypeService.delete(customerType);
        return ResponseEntity.ok(customerType);
    }
}
