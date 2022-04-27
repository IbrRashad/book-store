package com.rashad.bookstore.service;

import com.rashad.bookstore.entity.User;
import com.rashad.bookstore.responses.JwtResponse;
import com.rashad.bookstore.responses.LoginRequest;
import com.rashad.bookstore.responses.RegisterRequest;

public interface AuthService {
    JwtResponse login(LoginRequest request);
    User registerAsPublisher(RegisterRequest request);
    User registerAsUser(RegisterRequest request);
}
