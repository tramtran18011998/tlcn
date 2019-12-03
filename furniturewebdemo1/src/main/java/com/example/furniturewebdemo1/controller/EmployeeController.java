package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.AppException;
import com.example.furniturewebdemo1.exception.ResourceNotFoundException;

import com.example.furniturewebdemo1.model.Employee;
import com.example.furniturewebdemo1.model.Role;
import com.example.furniturewebdemo1.model.RoleName;
import com.example.furniturewebdemo1.model.User;
import com.example.furniturewebdemo1.payload.LoginE;
import com.example.furniturewebdemo1.payload.LoginRequest;
import com.example.furniturewebdemo1.repository.RoleRepository;
import com.example.furniturewebdemo1.service.EmployeeService;
import com.example.furniturewebdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/employee")
    public List<Employee> getAllEmployee() {
        return employeeService.findAllEmployee();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Employee employee = employeeService.findEmployeeId(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping(value = "/employee", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> createEmployee(@Valid @ModelAttribute Employee employee, @Valid @ModelAttribute User user, @RequestParam("file") MultipartFile file) throws IOException {

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        Role userRole = roleRepository.findByName(RoleName.ROLE_EMPLOYEE)
                .orElseThrow(() -> new AppException("User Role not set."));


        user.setRoles(Collections.singleton(userRole));
        user.setImageUrl(userService.storeAvatar1(file));

        userService.save(user);
        employee.setUser(user);
        employeeService.save(employee);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
//    @PostMapping(value = "/employee/admin",produces = MediaType.IMAGE_PNG_VALUE)
//    public  ResponseEntity<Employee> createAdmin(@Valid @ModelAttribute Employee employee,@RequestParam("file") MultipartFile file) throws IOException {
//        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
//        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//                .orElseThrow(() -> new AppException("User Role not set."));
//
//        employee.setRoles(Collections.singleton(userRole));
//        employee.setAvatar(employeeService.storeAvatar1(file));
//
//        employeeService.save(employee);
//        return new ResponseEntity<>(employee, HttpStatus.CREATED);
//    }

    //fix error 406: [org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }

//    @PutMapping("/employee/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") long id, @Valid @RequestBody Employee employee) throws ResourceNotFoundException {
//        Employee currentEmployee= employeeService.findEmployeeId(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
//
//        currentEmployee.setUsername(employee.getUsername());
//        currentEmployee.setPassword(passwordEncoder.encode(employee.getPassword()));
//        currentEmployee.setFullname(employee.getFullname());
//        currentEmployee.setAddress(employee.getAddress());
//        currentEmployee.setEmail(employee.getEmail());
//        currentEmployee.setAvatar(employee.getAvatar());
//        currentEmployee.setPhoneNumber(employee.getPhoneNumber());
//        currentEmployee.setBonus(employee.getBonus());
//        currentEmployee.setPosition(employee.getPosition());
//        currentEmployee.setSalary(employee.getSalary());
//        currentEmployee.setRoles(employee.getRoles());
//
//        employeeService.save(currentEmployee);
//
//        return ResponseEntity.ok(currentEmployee);
//
//    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Employee employee = employeeService.findEmployeeId(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeService.delete(employee);
        return ResponseEntity.ok(employee);
    }

    //login form for employee
//    @PostMapping(value = "/employee/login", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Employee> getByUsernameAndPassword(@Valid @RequestBody LoginE loginE) {
//        Employee employee = employeeService.findByUsername(loginE.getUsername());
//        if( employee != null){
//            if(passwordEncoder.matches(loginE.getPassword(), employee.getPassword())){
//                return ResponseEntity.ok().body(employee);
//            }
//        }
//        return null;
//    }


//    @PostMapping("/employee/{id}")
//    public  ResponseEntity<Employee> upload(@RequestParam("file") MultipartFile file, @PathVariable(value = "id") long id) throws IOException, ResourceNotFoundException {
//        Employee currentEmployee= employeeService.findEmployeeId(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
//        currentEmployee.setAvatar(employeeService.storeAvatar(file,id));
//
//        employeeService.save(currentEmployee);
//        return new ResponseEntity<>(currentEmployee, HttpStatus.CREATED);
//    }
}
