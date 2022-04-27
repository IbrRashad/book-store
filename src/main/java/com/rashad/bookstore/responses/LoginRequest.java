package com.rashad.bookstore.responses;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class LoginRequest {
    @NotBlank(message = "username is required")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(max=20, message = "Pasword cannot be bigger than 20")
    private String password;
}
