package com.example.adapter.in.web;

import com.example.domain.model.Order;
import com.example.application.service.OrderService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

    OrderService orderService;

    @Inject
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @POST
    public Response createOrder(Order order) {
        Order createdOrder = orderService.save(order);
        return Response.status(Response.Status.CREATED).entity(createdOrder).build();
    }

    @GET
    public List<Order> getAllOrders() {
        List<Order> orders = orderService.findAll();
        return orders;
    }

    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam("id") Long id) {
        Optional<Order> order = orderService.findById(id);
        return order.map(value -> Response.ok(value).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") Long id) {
        orderService.deleteById(id);
        return Response.noContent().build();
    }
}
