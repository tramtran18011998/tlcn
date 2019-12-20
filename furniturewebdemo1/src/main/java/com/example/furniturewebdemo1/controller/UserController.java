package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.AppException;
import com.example.furniturewebdemo1.exception.BadRequestException;
import com.example.furniturewebdemo1.exception.ResourceNotFoundException;
import com.example.furniturewebdemo1.model.*;
import com.example.furniturewebdemo1.payload.ApiResponse;
import com.example.furniturewebdemo1.payload.SignUpRequest;
import com.example.furniturewebdemo1.repository.UserRepository;
import com.example.furniturewebdemo1.security.CurrentUser;
import com.example.furniturewebdemo1.security.UserPrincipal;
import com.example.furniturewebdemo1.service.CustomerService;
import com.example.furniturewebdemo1.service.SupplierService;
import com.example.furniturewebdemo1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) throws ResourceNotFoundException {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }


    //////////////////POST
//    @PostMapping(value = "/api/user", produces = MediaType.IMAGE_PNG_VALUE)
//    public ResponseEntity<?> createUser(@Valid @ModelAttribute Employee employee, @Valid @ModelAttribute User user, @RequestParam("file") MultipartFile file) throws IOException {
//
//        user.setName(signUpRequest.getName());
//        user.setEmail(signUpRequest.getEmail());
//        user.setPassword(signUpRequest.getPassword());
//        user.setProvider(AuthProvider.local);
//
//        user.setCreatedDate(new Date());
//        long a=1;
//        user.setInstatus(a);
//
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//                .orElseThrow(() -> new AppException("User Role not set."));
//
//        user.setRoles(Collections.singleton(userRole));
//        logger.info("role2: "+Collections.singleton(userRole));
//
//        User result = userService.save(user);
//
//        Customer customer = new Customer();
//        customer.setUser(user);
//        customerService.save(customer);
//
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/user/me")
//                .buildAndExpand(result.getId()).toUri();
//
//        return ResponseEntity.created(location)
//                .body(new ApiResponse(true, "User registered successfully@"));
//    }


    ////////////////////////////UPDATE- UPLOAD
    //update
    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.PUT,produces = "application/json")
    public @ResponseBody ResponseEntity<User> updateUser(@PathVariable(value = "id") long id, @Valid @RequestBody User user) throws ResourceNotFoundException {
        User currentUser= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        logger.info(String.valueOf(currentUser));
        currentUser.setInstatus(user.getInstatus());
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        currentUser.setLastModifiedDate(new Date());
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setAddress(user.getAddress());
        //currentUser.setProvider(user.getProvider());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        logger.info(currentUser.getAddress());
        userRepository.save(currentUser);
        return ResponseEntity.ok(currentUser);

    }

    //update image customer information
    @PutMapping("api/userimg/{id}")
    public ResponseEntity<User> upload(@RequestParam("file") MultipartFile file, @PathVariable(value = "id") long id) throws IOException, ResourceNotFoundException {
        User user= userService.findByUserId(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        user.setImageUrl(userService.storeAvatar(file,id));
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }


    ///////////////////////SERVE IMAGE
    //serve image employee
    @RequestMapping(value = "employeeimg/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public  byte[] getImageEmployee(@PathVariable(value = "imageName") String imageName) throws IOException {

        File serverFile = new File("uploads/employees/" + imageName);

        return Files.readAllBytes(serverFile.toPath());
    }

    //serve image customer
    @RequestMapping(value = "customerimg/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public  byte[] getImageCustomer(@PathVariable(value = "imageName") String imageName) throws IOException {

        File serverFile = new File("uploads/customers/" + imageName);

        return Files.readAllBytes(serverFile.toPath());
    }

    ////////////get user by id
    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        User user=userService.findByUserId(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return ResponseEntity.ok().body(user);
    }


    /////////////////////////////CUSTOMER HANDLING
    //get list user role USER (customer)
    @GetMapping("/api/customer")
    public List<User> getCustomer(){
        return userRepository.listCustomer();
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

    /////////////////////////EMPLOYEE HANDLING
    @GetMapping("/api/employeeuser/{user_id}")
    public long getEmployeeById(@PathVariable(value = "user_id") long user_id) throws ResourceNotFoundException {
        long id = userRepository.getEmployeeId(user_id);
        return id;
    }

}
