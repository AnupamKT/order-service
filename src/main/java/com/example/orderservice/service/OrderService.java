package com.example.orderservice.service;

import com.example.orderservice.common.OrderNotFoundException;
import com.example.orderservice.common.OrderServiceException;
import com.example.orderservice.dao.OrderDAO;
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
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Response saveOrder(Order order) throws OrderServiceException {
        ObjectMapper mapper = new ObjectMapper();
        OrderDAO savedOrder = null;
        try {
            OrderDAO dao = mapper.convertValue(order, OrderDAO.class);
            dao.setCreateDate(new Date());
            dao.setUpdatedDate(new Date());
            savedOrder = orderRepository.save(dao);
        } catch (Exception e) {
            String msg = "error occurred while creating order:" + order;
            throw new OrderServiceException(msg);
        }
        return new Response(200, savedOrder);
    }

    public Response deleteOrderById(String id) throws Exception {
        Response response = new Response();
        Optional<OrderDAO> orderToDeleteOptional = Optional.empty();
        String msg = null;
        try {
            orderToDeleteOptional = orderRepository.findById(UUID.fromString(id));
            if (orderToDeleteOptional.isPresent()) {
                orderRepository.deleteById(UUID.fromString(id));
            } else {
                msg = "order not found with Id:" + id;
                throw new OrderNotFoundException(msg);
            }
        } catch (Exception e) {
            msg = "error occurred while deleting order with Id:" + id;
            throw new OrderServiceException(msg);

        }
        msg = "Order deleted successfully";
        return new Response(200, msg);
    }

    public Response getAllOrders(String userName) throws OrderServiceException {
        Orders orders = new Orders();
        try {
            Optional<List<OrderDAO>> ordersOptional = orderRepository.getByUserName(userName);
            if (ordersOptional.isPresent() && !CollectionUtils.isEmpty(ordersOptional.get())) {
                orders.setOrders(ordersOptional.get());
            } else {
                orders.setOrders(new ArrayList<OrderDAO>());
            }
        } catch (Exception e) {
            String msg = "error occurred while fetching orders for userId:" + userName;
            throw new OrderServiceException(msg);
        }
        return new Response(200, orders);

    }
}
