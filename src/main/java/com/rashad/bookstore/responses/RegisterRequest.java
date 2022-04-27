package com.rashad.bookstore.responses;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class RegisterRequest {
    @NotBlank(message = "Username is requierd")
    private String username;

    @NotBlank(message = "Passwod is required")
    @Size(max=20, message = "Password cannot be bigger than 20")
    private String password;

}
