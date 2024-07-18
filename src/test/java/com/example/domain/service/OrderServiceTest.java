package com.example.domain.service;

import com.example.application.service.OrderService;
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
        public void testSave() {
            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            Order createdOrder = orderService.save(order);
            assertNotNull(createdOrder.getId());
        }

}
