package com.example.orderservice.common;

public class OrderNotFoundException extends Exception{
    public OrderNotFoundException(String msg){
        super(msg);
    }
}
