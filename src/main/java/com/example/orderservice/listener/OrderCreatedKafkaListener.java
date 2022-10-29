package com.example.orderservice.listener;

import com.example.orderservice.controller.OrderController;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "kafkaOrderCreated",
        groupId = "orderCreated",
        containerFactory = "getKafkaConsumerFactory")
@Slf4j
public class OrderCreatedKafkaListener {
    @Autowired
    private OrderController controller;

    @KafkaHandler
    public void createOrder(Order order) {
        try {
            controller.saveOrder(order);
        } catch (Exception e) {
            log.error("error occurred in OrderCreatedKafkaListener while creating order for " + order
                    + e.getMessage());
        }
    }
}
