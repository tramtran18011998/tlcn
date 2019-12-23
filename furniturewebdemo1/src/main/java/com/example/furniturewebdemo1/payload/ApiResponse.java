package com.example.furniturewebdemo1.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(boolean success) {
        this.success = success;
    }

}
