package com.example.application.service;

import com.example.application.ports.in.OrderUseCase;
import com.example.domain.model.Order;
import com.example.application.ports.out.OrderPort;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrderService implements OrderUseCase {


    OrderPort orderPort;

    @Inject
    public OrderService(OrderPort orderPort) {
        this.orderPort = orderPort;
    }

    public Order save(Order order) {
        orderPort.save(order);
        return order;
    }

    public List<Order> findAll() {
        return orderPort.findAll();
    }

    public Optional<Order> findById(Long orderId) {
        return orderPort.findById(orderId);
    }

    public void deleteById(Long orderId) {
        orderPort.deleteById(orderId);
    }

}
