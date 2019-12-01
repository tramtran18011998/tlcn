package com.example.furniturewebdemo1.service;


import com.example.furniturewebdemo1.model.CustomerType;

import java.util.List;
import java.util.Optional;

public interface CustomerTypeService {
    List<CustomerType> findAllCustomerType();
    Optional<CustomerType> findCustomerTypeById(long id);
    void save (CustomerType customerType);
    void delete (CustomerType customerType);

}
