package org.example.springboot4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springboot4.model.dto.reqeust.RedisObjectPutRequest;
import org.example.springboot4.model.dto.reqeust.RedisPutObject;
import org.example.springboot4.model.dto.reqeust.RedisPutRequest;
import org.example.springboot4.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/redis")
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public void set(@Valid @RequestBody RedisPutRequest request) {
        redisService.set(request.key(), request.value());
    }

    @PostMapping("/object")
    public void setObject(@Valid @RequestBody RedisObjectPutRequest request) {
        redisService.setObject(request.key(), request.value());
    }

    @GetMapping("/{key}")
    public String get(@PathVariable String key) {
        return redisService.get(key);
    }

    @GetMapping("/object/{key}")
    public RedisPutObject getObject(@PathVariable String key) {
        String value = redisService.get(key);
        return objectMapper.readValue(value, RedisPutObject.class);
    }
}
