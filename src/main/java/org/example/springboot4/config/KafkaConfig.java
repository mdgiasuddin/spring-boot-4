package org.example.springboot4.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Value("${spring.kafka.test-topic}")
    private String testTopic;

    @Bean
    public NewTopic kafkaTopic() {
        return TopicBuilder.name(testTopic)
                .partitions(3)
                .build();
    }
}
