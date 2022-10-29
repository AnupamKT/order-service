package com.example.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
    private String itemName;
    private double itemPrice;
    private int itemQuantity;
    private String userName;
}
