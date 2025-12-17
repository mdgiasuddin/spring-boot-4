package org.example.springboot4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springboot4.model.dto.reqeust.AuthenticationRequest;
import org.example.springboot4.model.dto.reqeust.UserRegisterRequest;
import org.example.springboot4.model.dto.response.AuthenticationResponse;
import org.example.springboot4.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @ResponseStatus(CREATED)
    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody UserRegisterRequest request) {
        authenticationService.registerUser(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse authTest(@RequestBody @Valid AuthenticationRequest request) {
        return authenticationService.login(request);
    }
}
