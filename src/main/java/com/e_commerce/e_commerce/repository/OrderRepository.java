package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Order;
import com.e_commerce.e_commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    List<Order> findByStatus(String status);
    List<Order> findByUserAndStatus(User user, String status);
} 