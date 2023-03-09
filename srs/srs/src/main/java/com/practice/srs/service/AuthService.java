package com.practice.srs.service;

import com.practice.srs.model.User;
import com.practice.srs.repository.UserRepository;
import com.practice.srs.roles.Role;
import com.practice.srs.utils.AuthenticationRequest;
import com.practice.srs.utils.AuthenticationResponse;
import com.practice.srs.utils.JWTService;
import com.practice.srs.utils.RegisterRequest;
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
