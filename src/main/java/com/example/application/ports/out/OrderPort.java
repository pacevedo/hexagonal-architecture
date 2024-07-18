package com.example.application.ports.out;

import com.example.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderPort {

    Order save (Order order);
    List<Order> findAll();
    Optional<Order> findById(Long orderId);
    void deleteById(Long orderId);
}
