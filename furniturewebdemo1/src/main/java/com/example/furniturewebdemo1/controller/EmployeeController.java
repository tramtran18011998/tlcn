package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.AppException;
import com.example.furniturewebdemo1.exception.BadRequestException;
import com.example.furniturewebdemo1.exception.ResourceNotFoundException;

import com.example.furniturewebdemo1.model.*;
import com.example.furniturewebdemo1.payload.LoginE;
import com.example.furniturewebdemo1.payload.LoginRequest;
import com.example.furniturewebdemo1.repository.RoleRepository;
import com.example.furniturewebdemo1.repository.UserRepository;
import com.example.furniturewebdemo1.service.EmployeeService;
import com.example.furniturewebdemo1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    //get list user role employee
    @GetMapping("/employee")
    public List<User> getAllEmployee() {
        //return employeeService.findAllEmployee();
        return userRepository.listEmployee();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Employee employee = employeeService.findEmployeeId(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping(value = "/employee", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> createEmployee(@Valid @ModelAttribute Employee employee, @Valid @ModelAttribute User user, @RequestParam("file") MultipartFile file) throws IOException {

        if(userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_EMPLOYEE)
                .orElseThrow(() -> new AppException("User Role not set."));

        long a=1;
        user.setInstatus(a);
        user.setProvider(AuthProvider.local);
        user.setRoles(Collections.singleton(userRole));

//        if(file.isEmpty()){
////            user.setImageUrl(userService.storeAuto());
////        }else {
////            user.setImageUrl(userService.storeAvatar1(file));
////        }
        user.setImageUrl(userService.storeAvatar1(file));
        user.setCreatedDate(new Date());

        userService.save(user);
        employee.setUser(user);
        employeeService.save(employee);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping(value = "/employee/admin",produces = MediaType.IMAGE_PNG_VALUE)
    public  ResponseEntity<Employee> createAdmin(@Valid @ModelAttribute Employee employee, @Valid @ModelAttribute User user,@RequestParam("file") MultipartFile file) throws IOException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        user.setImageUrl(userService.storeAvatar1(file));

        user.setCreatedDate(new Date());

        long a=1;
        user.setInstatus(a);
        user.setProvider(AuthProvider.local);
        userService.save(user);
        employee.setUser(user);
        employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    //fix error 406: [org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") long id, @Valid @RequestBody Employee employee) throws ResourceNotFoundException {
        Employee currentEmployee= employeeService.findEmployeeId(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));

        //logger.info("i: "+String.valueOf(currentEmployee));
        if(employee.getPosition()!=null){
            currentEmployee.setPosition(employee.getPosition());
        }

        if(employee.getBonus()!=0){
            currentEmployee.setBonus(employee.getBonus());
        }
        if(employee.getSalary()!=0){
            currentEmployee.setSalary(employee.getSalary());
        }

        employeeService.save(currentEmployee);

        return ResponseEntity.ok(currentEmployee);

    }
    @PutMapping("/employeeuser/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "user_id") long user_id, @Valid @RequestBody User user) throws ResourceNotFoundException {
        User currentUser= userRepository.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        if(user.getInstatus()!=null){
            currentUser.setInstatus(user.getInstatus());
        }
        if(user.getPassword()!=null){
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        currentUser.setLastModifiedDate(new Date());

        if(user.getName()!=null){
            currentUser.setName(user.getName());
        }
        if(user.getEmail()!=null){
            currentUser.setEmail(user.getEmail());
        }
        if(user.getAddress()!=null){
            currentUser.setAddress(user.getAddress());
        }
        if(user.getPhoneNumber()!=null){
            currentUser.setPhoneNumber(user.getPhoneNumber());
        }

        logger.info(currentUser.getAddress());
        userRepository.save(currentUser);

        return ResponseEntity.ok(currentUser);

    }

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


    public long getIdE(long id){
        long id2 = userRepository.getEmployeeId(id);
        return id2;
    }

//    @PutMapping("/employee/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") long id, @Valid @RequestBody Employee employee ,@RequestBody User user) throws ResourceNotFoundException {
//
//        Employee currentEmployee= employeeService.findEmployeeId(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
//        //logger.info("i: "+String.valueOf(currentEmployee));
//        currentEmployee.setBonus(employee.getBonus());
//        currentEmployee.setPosition(employee.getPosition());
//        currentEmployee.setSalary(employee.getSalary());
//        employeeService.save(currentEmployee);
//        logger.info("i: "+String.valueOf(currentEmployee.getPosition()));
//
//        long id2 = getIdE(id);
//        logger.info(String.valueOf(id2));
//        User currentUser= userRepository.findById(id2).orElseThrow(()-> new ResourceNotFoundException("User not found"));
//        //logger.info(String.valueOf(currentUser));
//        currentUser.setInstatus(user.getInstatus());
//        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
//        currentUser.setLastModifiedDate(new Date());
//        currentUser.setName(user.getName());
//        currentUser.setEmail(user.getEmail());
//        currentUser.setAddress(user.getAddress());
//        //currentUser.setProvider(user.getProvider());
//        currentUser.setPhoneNumber(user.getPhoneNumber());
//        logger.info(currentUser.getAddress());
//        userRepository.save(currentUser);
//
//        return ResponseEntity.ok(currentEmployee);
//
//    }
}
