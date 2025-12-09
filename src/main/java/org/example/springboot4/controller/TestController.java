package org.example.springboot4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springboot4.model.dto.reqeust.TestRequest;
import org.example.springboot4.service.TestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping
    public String test(@Valid @RequestBody TestRequest request) {
        return testService.test(request);
    }
}
