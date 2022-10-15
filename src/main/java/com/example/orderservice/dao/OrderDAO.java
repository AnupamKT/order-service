package com.example.orderservice.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="order_info")
@Data
public class OrderDAO {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private String itemName;
    private double itemPrice;
    private int itemQuantity;
    private Date createDate;
    private Date updatedDate;
    private Long userId;
}
