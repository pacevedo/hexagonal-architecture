package com.example.adapter.out.persistence;

import com.example.application.ports.out.OrderPort;
import com.example.domain.model.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JpaOrderRepository implements OrderPort {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Order save(Order order) {
        if (order.getId() == null){
            em.persist(order);
            return order;
        } else {
            return em.merge(order);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long orderId) {
        em.remove(em.find(Order.class,orderId));
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(em.find(Order.class, id));
    }

    @Override
    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

}
