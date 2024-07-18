package com.example.application.service;

import com.example.application.ports.out.OrderPort;
import com.example.application.service.OrderService;
import com.example.domain.model.Order;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
public class OrderServiceTest {

        @Mock
        OrderPort orderPort;

        @InjectMocks
        OrderService orderService;

        @BeforeEach
        void setUp(){
            MockitoAnnotations.openMocks(this);
        }
        @Test
        public void testSaveOrder() {
            Order order = new Order(null, LocalDateTime.now(),"Test Order");
            when(orderPort.save(any(Order.class))).thenReturn(order);
            Order createdOrder = orderService.save(order);
            assertEquals(order.getStatus(), createdOrder.getStatus());
            verify(orderPort, times(1)).save(any(Order.class));
        }

        @Test
        public void testFindOrder() {
            Order order = new Order(null, LocalDateTime.now(),"Test Order");
            when(orderPort.findById(anyLong())).thenReturn(Optional.of(order));
            Order foundOrder = orderService.findById(1L).get();
            assertEquals(order.getStatus(), foundOrder.getStatus());
            verify(orderPort, times(1)).findById(anyLong());
        }

        @Test
        public void testFindAll() {
            Order order = new Order(null, LocalDateTime.now(),"Test Order");
            when(orderPort.findAll()).thenReturn(java.util.List.of(order));
            assertEquals(1, orderService.findAll().size());
            verify(orderPort, times(1)).findAll();
        }

        @Test
        public void testDeletedOrder() {
            Order order = new Order(null, LocalDateTime.now(),"Test Order");
            orderService.deleteById(1L);
            verify(orderPort, times(1)).deleteById(anyLong());
        }

}
