package com.example.furniturewebdemo1.serviceimpl;

import com.example.furniturewebdemo1.model.User;
import com.example.furniturewebdemo1.repository.UserRepository;
import com.example.furniturewebdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUserId(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        userRepository.save(user);
        return  user;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }


    @Override
    public String storeAvatar(MultipartFile file, long id) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File convertFile = new File("uploads/customers/"+fileName);
        convertFile.createNewFile();

        try (FileOutputStream fout = new FileOutputStream(convertFile))
        {
            fout.write(file.getBytes());
        }
        catch (Exception exe)
        {
            exe.printStackTrace();
        }
        return file.getOriginalFilename();
    }

    @Override
    public String storeAvatar1(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File convertFile = new File("uploads/customers/"+fileName);
        convertFile.createNewFile();

        try (FileOutputStream fout = new FileOutputStream(convertFile))
        {
            fout.write(file.getBytes());
        }
        catch (Exception exe)
        {
            exe.printStackTrace();
        }
        return file.getOriginalFilename();
    }
}
