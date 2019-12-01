package com.example.furniturewebdemo1.service;

import com.example.furniturewebdemo1.model.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAllEmployee();
    Optional<Employee> findEmployeeId(long id);
    void save (Employee employee);
    void delete (Employee employee);
//    Optional<Employee> findByEmailAndPassword(String email, String password);
//
//    Employee findByUsername(String username);
    String storeAvatar(MultipartFile file, long id) throws IOException;
    String storeAvatar1(MultipartFile file) throws IOException;
}
