package com.example.furniturewebdemo1.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Getter
@Setter
public class SignUpRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
