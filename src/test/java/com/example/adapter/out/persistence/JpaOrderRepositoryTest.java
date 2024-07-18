package com.example.adapter.out.persistence;

import com.example.domain.model.Order;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@QuarkusTest
@Transactional
class JpaOrderRepositoryTest {

    @Inject
    JpaOrderRepository jpaOrderRepository;

    @Inject
    EntityManager em;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindOrder() {
        // given
        Order order = new Order(null, LocalDateTime.now(),"Test Order");
        em.persist(order);
        em.flush();
        em.clear();
        Optional<Order> order2 = jpaOrderRepository.findById(order.getId());
        assertTrue(order2.isPresent());
        assertEquals(order.getStatus(), order2.get().getStatus());
    }

    @Test
    void testSaveOrder() {

        Order order = new Order(null, LocalDateTime.now(),"Test Order");
        jpaOrderRepository.save(order);
        em.flush();
        em.clear();

        Optional<Order> savedOrder = jpaOrderRepository.findById(order.getId());
        assertTrue(savedOrder.isPresent());
        assertEquals(order.getStatus(), savedOrder.get().getStatus());
    }

    @Test
    void deleteById() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }
}