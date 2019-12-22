package com.example.furniturewebdemo1.controller;


import com.example.furniturewebdemo1.exception.AppException;
import com.example.furniturewebdemo1.exception.BadRequestException;
import com.example.furniturewebdemo1.exception.ResourceNotFoundException;
import com.example.furniturewebdemo1.model.*;
import com.example.furniturewebdemo1.payload.ApiResponse;
import com.example.furniturewebdemo1.payload.AuthResponse;
import com.example.furniturewebdemo1.payload.LoginRequest;
import com.example.furniturewebdemo1.payload.SignUpRequest;
import com.example.furniturewebdemo1.repository.RoleRepository;
import com.example.furniturewebdemo1.repository.UserRepository;
import com.example.furniturewebdemo1.security.TokenProvider;
import com.example.furniturewebdemo1.service.CustomerService;
import com.example.furniturewebdemo1.service.CustomerTypeService;
import com.example.furniturewebdemo1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerTypeService customerTypeService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws ResourceNotFoundException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

//        tokenProvider.createToken(authentication);
        String token = tokenProvider.createToken(authentication);
        logger.info("Token: "+token);

        String role = userRepository.findByRoleUser(loginRequest.getEmail());
        logger.info("role: "+role);
        User user = findUser(loginRequest.getEmail());
        //logger.info("user: "+user);
        AuthResponse authResponse = new AuthResponse(token,"Bearer",user);
//        return ResponseEntity.ok(new AuthResponse(token, userRepository.findByEmail(loginRequest.getEmail())));
//        return ResponseEntity.ok(userRepository.findByEmail(loginRequest.getEmail()));
        return ResponseEntity.ok(authResponse);
    }



    @GetMapping(value = "/loginCheck/{email}",produces="application/json")
    public String checkUser(@PathVariable(value = "email") String email) {
        //boolean check = false;

        String role = userRepository.findByRoleUser(email);

        logger.info("roleUSER: "+role);
        return role;
    }

    @GetMapping(value = "/userfind/{email}",produces="application/json")
    public User findUser(@PathVariable(value = "email") String email) throws ResourceNotFoundException {
        User user =userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
        return user;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws ResourceNotFoundException {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();


        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);

        user.setCreatedDate(new Date());
        long a=1;
        user.setInstatus(a);


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        logger.info("role2: "+Collections.singleton(userRole));

        User result = userService.save(user);

        Customer customer = new Customer();
        //Optional<CustomerType> customerType = customerTypeService.findCustomerTypeById(1);
        CustomerType customerType= customerTypeService.findCustomerTypeById(1).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
        customer.setUser(user);
        customer.setCustomerType(customerType);
        customerService.save(customer);


        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

}
