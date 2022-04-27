package com.rashad.bookstore.api;


import com.rashad.bookstore.responses.JwtResponse;
import com.rashad.bookstore.responses.LoginRequest;
import com.rashad.bookstore.responses.MessageResponse;
import com.rashad.bookstore.responses.RegisterRequest;
import com.rashad.bookstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/user/register")
    public ResponseEntity<MessageResponse> registerAsUser(@Valid @RequestBody RegisterRequest request){
        authService.registerAsUser(request);
        MessageResponse messageResponse = new MessageResponse("User registered successfully");
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/publisher/register")
    public ResponseEntity<MessageResponse> registerAsPublisher(@Valid @RequestBody RegisterRequest request){
        authService.registerAsPublisher(request);
        MessageResponse messageResponse = new MessageResponse("Publisher registered successfully");
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.login(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }
}
