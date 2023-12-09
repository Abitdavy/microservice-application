package com.microservice.marketplace.kafka.consumer;

import com.microservice.marketplace.model.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {
    @KafkaListener(topics = "my-marketplace", groupId = "order-consume")
    public void orderListener(OrderDTO orderDTO) {
        log.info("Get request order: '" + orderDTO.toString());
    }
}
