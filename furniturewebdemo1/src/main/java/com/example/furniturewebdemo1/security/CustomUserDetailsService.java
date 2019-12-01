package com.example.furniturewebdemo1.security;


import com.example.furniturewebdemo1.exception.ResourceNotFoundException;
import com.example.furniturewebdemo1.model.User;
import com.example.furniturewebdemo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User not found")
        );

        return UserPrincipal.create(user);
    }
}