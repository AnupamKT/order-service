package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.Response;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveOrder(@RequestBody Order order) {
        ResponseEntity<Response> response = orderService.saveOrder(order);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteOrderById(@PathVariable String id) {
        ResponseEntity<Response> response = orderService.deleteOrderById(id);
        return response;
    }

    @GetMapping("/fetchall/{userId}")
    public ResponseEntity<Response> fetchAllOrder(@PathVariable Long userId) {
        ResponseEntity<Response> response = orderService.getAllOrders(userId);
        return response;
    }
}
