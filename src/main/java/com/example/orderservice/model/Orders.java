package com.example.orderservice.model;

import com.example.orderservice.dao.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private List<Order> orders;
}
