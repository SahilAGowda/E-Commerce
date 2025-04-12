package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.entity.Order;
import com.e_commerce.e_commerce.entity.User;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(Long id);
    Optional<Order> getOrderById(Long id);
    List<Order> getAllOrders();
    List<Order> getOrdersByUser(User user);
    List<Order> getOrdersByStatus(String status);
    List<Order> getOrdersByUserAndStatus(User user, String status);
} 