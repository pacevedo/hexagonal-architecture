package com.example.application.rest;

import com.example.domain.model.Order;
import com.example.domain.model.OrderItem;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class OrderResourceTest {

    @Test
    public void testCreateOrderEndpoint() {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                .statusCode(201)
                .body("status", is("PENDING"));
    }

    @Test
    public void testAddItemToOrderEndpoint() {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        Order createdOrder = given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                .statusCode(201)
                .extract().as(Order.class);

        OrderItem item = new OrderItem("Product 1", new BigDecimal("50.0"), 2);
        given()
                .contentType("application/json")
                .body(item)
                .when().post("/orders/{orderId}/items", order.getId())
                .then()
                .statusCode(200);
    }

    @Test
    public void testUpdateOrderStatusEndpoint() {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                .statusCode(201)
                .extract().as(Order.class);

        given()
                .contentType("application/json")
                .body("CONFIRMED")
                .when().put("/orders/{orderId}/status", order.getId())
                .then()
                .statusCode(200)
                .body("status", is("CONFIRMED"));
    }
}
