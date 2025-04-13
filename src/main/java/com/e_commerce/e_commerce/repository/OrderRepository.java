package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
    Order findByOrderId(String orderId);
    Iterable<Order> findAllByEmail(String email);
}
