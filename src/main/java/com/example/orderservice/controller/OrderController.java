package com.example.orderservice.controller;

import com.example.orderservice.common.OrderServiceException;
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
    public ResponseEntity<Response> saveOrder(@RequestBody Order order) throws OrderServiceException {
        Response response = orderService.saveOrder(order);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteOrderById(@PathVariable String id) throws Exception {
        Response response = orderService.deleteOrderById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/fetchall/{userName}")
    public ResponseEntity<Response> fetchAllOrder(@PathVariable String userName) throws OrderServiceException {
        Response response = orderService.getAllOrders(userName);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
