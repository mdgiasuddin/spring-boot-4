package org.example.springboot4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springboot4.config.JwtService;
import org.example.springboot4.model.dto.reqeust.AuthRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @GetMapping("/test")
    public String authTest() {
        return jwtService.generateToken("test@gmail.com");
    }

    @PostMapping("/test")
    public String authTest(@RequestBody @Valid AuthRequest authRequest) {
        return jwtService.extractEmail(authRequest.token());
    }
}
