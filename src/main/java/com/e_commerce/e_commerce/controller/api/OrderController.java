package com.e_commerce.e_commerce.controller.api;

import com.e_commerce.e_commerce.entity.Order;
import com.e_commerce.e_commerce.repository.OrderRepository;
import com.e_commerce.e_commerce.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // Create an order
    @PostMapping("/create-order")
    public Response createOrder(@RequestBody Order order) {
        orderRepository.save(order);
        Response response = new Response();
        response.message = "Order Created Successfully";
        response.status = 200;
        return response;
    }

    // Get an order by ID
    @GetMapping("/get-order")
    public Order getOrder(@RequestParam String id) {
        return orderRepository.findByOrderId(id);
    }

    // Get all orders
    @GetMapping("/get-all-orders")
    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Delete an order by ID
    @DeleteMapping("/delete-order")
    public Response deleteOrder(@RequestParam String id) {
        Order order = orderRepository.findById(id).orElse(null);
        Response response = new Response();
        if (order != null) {
            orderRepository.delete(order);
            response.message = "Order Deleted Successfully";
            response.status = 200;
        } else {
            response.message = "Order Not Found";
            response.status = 404;
        }
        return response;
    }
}
