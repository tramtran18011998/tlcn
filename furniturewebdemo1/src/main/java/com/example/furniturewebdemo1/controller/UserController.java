package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;
import com.example.furniturewebdemo1.model.Customer;
import com.example.furniturewebdemo1.model.Supplier;
import com.example.furniturewebdemo1.model.User;
import com.example.furniturewebdemo1.repository.UserRepository;
import com.example.furniturewebdemo1.security.CurrentUser;
import com.example.furniturewebdemo1.security.UserPrincipal;
import com.example.furniturewebdemo1.service.CustomerService;
import com.example.furniturewebdemo1.service.SupplierService;
import com.example.furniturewebdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/user/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) throws ResourceNotFoundException {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


    //update customer information
    @PostMapping("api/user/{id}")
    public ResponseEntity<User> upload(@RequestParam("file") MultipartFile file, @PathVariable(value = "id") long id) throws IOException, ResourceNotFoundException {
        User user= userService.findByUserId(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        user.setImageUrl(userService.storeAvatar(file,id));

        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }


    @GetMapping("/api/customer")
    public List<User> getCustomer(){
        return userRepository.listCustomer();
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        User user=userService.findByUserId(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/api/customeruser/{user_id}")
    public long getSupplierById(@PathVariable(value = "user_id") long user_id) throws ResourceNotFoundException {

        long id = userRepository.getCustomerId(user_id);
        return id;
    }

    @GetMapping("/api/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        Customer customer=customerService.findCustomerId(id).orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
        return ResponseEntity.ok().body(customer);
    }

}
