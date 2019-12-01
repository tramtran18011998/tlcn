package com.example.furniturewebdemo1.repository;

import com.example.furniturewebdemo1.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
//    Optional<Employee> findByUsernameOrEmail(String username, String email);
//    Boolean existsByUsername(String username);
//
//    Boolean existsByEmail(String email);
//
//    Employee findByUsername(String username);
}
