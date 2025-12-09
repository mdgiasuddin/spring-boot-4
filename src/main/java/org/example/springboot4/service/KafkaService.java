package org.example.springboot4.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springboot4.model.dto.reqeust.PersonRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.security.SecureRandom;
import java.util.Random;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, PersonRequest> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final Random random = new SecureRandom();

    @Value("${spring.kafka.test-topic}")
    private String testTopic;

    public void sendMessage(PersonRequest request) {
        Message<PersonRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(TOPIC, testTopic)
                .build();

        kafkaTemplate.send(message);
        log.info("Message sent -> {}", request);
    }
}
