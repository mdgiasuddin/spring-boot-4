package org.example.springboot4.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springboot4.model.dto.reqeust.PersonRequest;
import org.example.springboot4.service.KafkaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaService kafkaService;

    @PostMapping
    public void sendMessage(@Valid @RequestBody PersonRequest request) {
        kafkaService.sendMessage(request);
    }
}
