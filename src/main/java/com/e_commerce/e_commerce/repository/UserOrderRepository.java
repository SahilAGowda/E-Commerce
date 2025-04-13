package com.e_commerce.e_commerce.repository;

import org.springframework.data.repository.CrudRepository;
import com.e_commerce.e_commerce.entity.UserOrderItem;

public interface UserOrderRepository extends CrudRepository<UserOrderItem, Integer> {
  
  // Find all orders placed by a user based on their unique order ID (like 'PNR' in the train booking app)
  Iterable<UserOrderItem> findAllByOrderNumber(String orderNumber);
  
  // Find all orders by user email (or you can use a user ID or similar)
  Iterable<UserOrderItem> findAllByUserEmail(String email);
  
  // Find all orders by order status (e.g., "pending", "shipped", etc.)
  Iterable<UserOrderItem> findAllByStatus(String status);
}
