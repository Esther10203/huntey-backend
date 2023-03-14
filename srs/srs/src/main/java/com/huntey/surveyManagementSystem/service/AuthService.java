package com.huntey.surveyManagementSystem.service;

import com.huntey.surveyManagementSystem.model.User;
import com.huntey.surveyManagementSystem.repository.UserRepository;
import com.huntey.surveyManagementSystem.roles.Role;
import com.huntey.surveyManagementSystem.utils.AuthenticationRequest;
import com.huntey.surveyManagementSystem.utils.AuthenticationResponse;
import com.huntey.surveyManagementSystem.utils.JWTService;
import com.huntey.surveyManagementSystem.utils.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;

    private final JWTService jwtService;
    private final AuthenticationManager authManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
     var user = User.builder()
             .firstname(registerRequest.getFirstname())
             .lastname(registerRequest.getLastname())
             .email(registerRequest.getEmail())
             .password(
                     passwordEncoder.encode(registerRequest.getPassword())
             )
             .role(Role.USER)
             .build();
     userRepository.save(user);
    return AuthenticationResponse.
             builder()
             .token(jwtService.generateToken(user))
             .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        return AuthenticationResponse.
                builder()
                .token(jwtService.generateToken(user))
                .build();
    }
}
