package org.example.springboot4.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springboot4.config.JwtService;
import org.example.springboot4.model.dto.reqeust.AuthenticationRequest;
import org.example.springboot4.model.dto.reqeust.UserRegisterRequest;
import org.example.springboot4.model.dto.response.AuthenticationResponse;
import org.example.springboot4.model.entity.User;
import org.example.springboot4.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public void registerUser(UserRegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);
    }

    public AuthenticationResponse login(@Valid AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        ));

        String accessToken = jwtService.generateToken(request.username());
        return new AuthenticationResponse(accessToken);
    }
}
