package com.example.furniturewebdemo1.controller;

import com.example.furniturewebdemo1.exception.ResourceNotFoundException;
import com.example.furniturewebdemo1.model.User;
import com.example.furniturewebdemo1.repository.UserRepository;
import com.example.furniturewebdemo1.security.CurrentUser;
import com.example.furniturewebdemo1.security.UserPrincipal;
import com.example.furniturewebdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) throws ResourceNotFoundException {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @PostMapping("api/user/{id}")
    public ResponseEntity<User> upload(@RequestParam("file") MultipartFile file, @PathVariable(value = "id") long id) throws IOException, ResourceNotFoundException {
        User user= userService.findByUserId(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
        user.setImageUrl(userService.storeAvatar(file,id));

        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
