package com.practice.srs.controller;

import com.practice.srs.service.AuthService;
import com.practice.srs.utils.AuthenticationRequest;
import com.practice.srs.utils.AuthenticationResponse;
import com.practice.srs.utils.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationController {

    @Autowired
    public AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ){
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest authRequest
    ){
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}
