package com.example.furniturewebdemo1.payload;

import com.example.furniturewebdemo1.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private User user;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }


    public AuthResponse( User user) {

        this.user = user;
    }


}
