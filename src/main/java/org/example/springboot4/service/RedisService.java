package org.example.springboot4.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public void set(String key, String value) {
        log.info("set key: {}, value: {}", key, value);
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(60));
    }

    public void setObject(String key, Object object) {
        log.info("set key: {}, value: {}", key, object);

        String value = "";
        value = objectMapper.writeValueAsString(object);
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(60));
    }

    public String get(String key) {
        log.info("get key: {}", key);
        return redisTemplate.opsForValue().get(key);
    }
}
