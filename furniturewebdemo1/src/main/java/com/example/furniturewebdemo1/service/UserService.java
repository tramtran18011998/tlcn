package com.example.furniturewebdemo1.service;

import com.example.furniturewebdemo1.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findByUserId(long id);
    User save(User user);
    void delete(User user);

    String storeAvatar(MultipartFile file, long id) throws IOException;
    String storeAvatar1(MultipartFile file) throws IOException;
    String storeAuto() throws IOException;
}
