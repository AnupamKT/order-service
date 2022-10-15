package com.example.orderservice.common;

import com.example.orderservice.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OrderServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = OrderServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleOrderServiceException(OrderServiceException ex) {
        ErrorResponse response = new ErrorResponse(500, ex.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(value = OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleOrderNotFoundException(OrderNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(404).body(response);
    }
}
