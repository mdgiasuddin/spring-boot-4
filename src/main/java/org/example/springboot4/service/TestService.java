package org.example.springboot4.service;

import lombok.extern.slf4j.Slf4j;
import org.example.springboot4.model.dto.reqeust.TestRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {
    public String test(TestRequest request) {
        log.info("Request:{}", request);

        return "Successfully processed.";
    }
}
