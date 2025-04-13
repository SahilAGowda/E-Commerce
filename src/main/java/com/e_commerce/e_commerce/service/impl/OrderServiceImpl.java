package com.e_commerce.e_commerce.service.impl;

import com.e_commerce.e_commerce.entity.Order;
import com.e_commerce.e_commerce.entity.OrderItem;
import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.repository.OrderRepository;
import com.e_commerce.e_commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {
        // Set initial status
        order.setStatus("PENDING");
        
        // Calculate total amount based on order items
        if (order.getTotalAmount() == null || order.getTotalAmount().compareTo(BigDecimal.ZERO) == 0) {
            calculateOrderTotal(order);
        }
        
        return orderRepository.save(order);
    }

    private void calculateOrderTotal(Order order) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : order.getOrderItems()) {
            item.setOrder(order);
            if (item.getSubtotal() == null && item.getQuantity() != null && item.getUnitPrice() != null) {
                item.setSubtotal(item.getUnitPrice().multiply(new BigDecimal(item.getQuantity())));
            }
            total = total.add(item.getSubtotal());
        }
        order.setTotalAmount(total);
    }

    @Override
    @Transactional
    public Order updateOrder(Order order) {
        // Recalculate total amount if needed
        calculateOrderTotal(order);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> getOrdersByUserAndStatus(User user, String status) {
        return orderRepository.findByUserAndStatus(user, status);
    }
    
    @Override
    @Transactional
    public Order processOrderPayment(Order order) {
        // Validate order
        if (order == null || order.getStatus() == null) {
            throw new IllegalArgumentException("Invalid order");
        }
        
        // Process payment (in a real app, integrate with payment gateway)
        boolean paymentSuccessful = processPayment(order);
        
        if (paymentSuccessful) {
            order.setStatus("PAID");
            return orderRepository.save(order);
        } else {
            order.setStatus("PAYMENT_FAILED");
            orderRepository.save(order);
            throw new RuntimeException("Payment processing failed");
        }
    }
    
    // Mock payment processing
    private boolean processPayment(Order order) {
        // In a real application, this would integrate with a payment gateway
        // For now, we'll just simulate successful payment
        return true;
    }
    
    @Override
    public List<Order> getRecentOrders(int days) {
        LocalDateTime startDate = LocalDateTime.now().minusDays(days);
        return orderRepository.findRecentOrders(startDate);
    }
}