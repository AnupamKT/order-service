package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.Orders;
import com.example.orderservice.model.Response;
import com.example.orderservice.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<Response> saveOrder(Order order) {
        ObjectMapper mapper = new ObjectMapper();
        Response response = new Response();
        com.example.orderservice.dao.Order orderDao = mapper.convertValue(order, com.example.orderservice.dao.Order.class);
        orderDao.setCreateDate(new Date());
        orderDao.setUpdatedDate(new Date());
        com.example.orderservice.dao.Order savedOrder = orderRepository.save(orderDao);
        if (savedOrder != null) {
            response.setMessage("order created successfully");
            response.setStatusCode(202);
            return ResponseEntity.accepted().body(response);
        } else {
            response.setStatusCode(500);
            response.setMessage("error occurred while creating order!! please try again");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<Response> deleteOrderById(String id) {
        Response response = new Response();
        com.example.orderservice.dao.Order orderToDelete = null;
        try {
            orderToDelete = orderRepository.getById(UUID.fromString(id));
            if (orderToDelete != null) {
                orderRepository.deleteById(UUID.fromString(id));
                response.setMessage("order delete successfully");
                response.setStatusCode(200);
            }
        } catch (Exception e) {
            if (e instanceof EntityNotFoundException) {
                response.setMessage("Internal server error occurred while deleting order");
                response.setStatusCode(500);
                return ResponseEntity.notFound().build();
            } else {
                response.setMessage("Internal server error occurred while deleting order");
                response.setStatusCode(500);
                return ResponseEntity.internalServerError().body(response);
            }
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response> getAllOrders(Long userId) {
        Response response = new Response();
        Optional<List<com.example.orderservice.dao.Order>> ordersOptional = null;
        try {
            ordersOptional = orderRepository.getByUserId(userId);
            if (ordersOptional.isPresent() && !CollectionUtils.isEmpty(ordersOptional.get())) {
                Orders orders = new Orders();
                orders.setOrders(ordersOptional.get());
                response.setStatusCode(200);
                response.setResponseBody(orders);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();

        }


    }
}
