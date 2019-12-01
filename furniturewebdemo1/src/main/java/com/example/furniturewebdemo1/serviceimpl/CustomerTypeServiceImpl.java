package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.CustomerType;
import com.example.furniturewebdemo1.repository.CustomerTypeRepository;
import com.example.furniturewebdemo1.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

    CustomerTypeRepository customerTypeRepository;

    @Autowired
    public CustomerTypeServiceImpl(CustomerTypeRepository customerTypeRepository){
        this.customerTypeRepository=customerTypeRepository;
    }

    @Override
    public List<CustomerType> findAllCustomerType() {
        return customerTypeRepository.findAll();
    }

    @Override
    public Optional<CustomerType> findCustomerTypeById(long id) {
        return customerTypeRepository.findById(id);
    }

    @Override
    public void save(CustomerType customerType) {
        customerTypeRepository.save(customerType);
    }

    @Override
    public void delete(CustomerType customerType) {
        customerTypeRepository.delete(customerType);
    }
}
