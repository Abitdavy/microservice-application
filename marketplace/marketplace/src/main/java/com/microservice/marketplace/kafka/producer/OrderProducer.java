package com.microservice.marketplace.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.marketplace.entity.Order;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@NoArgsConstructor
@Component
public class OrderProducer {
    final String orderTopic = "my-marketplace";

    private ObjectMapper objectMapper;

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public OrderProducer(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageOrder(Order message) {
        try {
            String finalMsg = objectMapper.writeValueAsString(message);
            this.kafkaTemplate.send(orderTopic, finalMsg);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
