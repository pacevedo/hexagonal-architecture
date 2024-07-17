package com.example.domain.service;

import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class OrderServiceTest {

        @Inject
        OrderService orderService;

        @Test
        public void testCreateOrder() {
            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            Order createdOrder = orderService.createOrder(order);
            assertNotNull(createdOrder.getId());
        }

        @Test
        public void testAddItemToOrder() {
            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            Order createdOrder = orderService.createOrder(order);

            OrderItem item = new OrderItem("Product 1", BigDecimal.valueOf(100), 1);
            orderService.addItemToOrder(createdOrder.getId(), item);

            Order updatedOrder = orderService.findById(createdOrder.getId());
            assertEquals(1, updatedOrder.getItems().size());
        }

        @Test
        public void testUpdateOrderStatus() {
            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            Order createdOrder = orderService.createOrder(order);

            orderService.updateOrderStatus(createdOrder.getId(), "SHIPPED");

            Order updatedOrder = orderService.findById(createdOrder.getId());
            assertEquals("SHIPPED", updatedOrder.getStatus());
        }


}
