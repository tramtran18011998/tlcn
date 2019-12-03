package com.example.furniturewebdemo1.service;

import com.example.furniturewebdemo1.model.Customer;
import com.example.furniturewebdemo1.model.Employee;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAllCustomer();
    Optional<Customer> findCustomerId(long id);
    void save (Customer customer);
    void delete (Customer customer);
}
