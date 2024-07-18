package com.example.application.ports.in;

import com.example.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderUseCase {
    Order save (Order order);
    List<Order> findAll();
    Optional<Order> findById(Long orderId);
    void deleteById(Long orderId);
}
