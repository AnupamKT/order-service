package com.example.orderservice.repository;

import com.example.orderservice.dao.OrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderDAO, UUID> {
    Optional<List<OrderDAO>> getByUserId(Long userId);
}
