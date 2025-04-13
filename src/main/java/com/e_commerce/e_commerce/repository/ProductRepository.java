package com.e_commerce.e_commerce.repository;

import org.springframework.data.repository.CrudRepository;
import com.e_commerce.e_commerce.entity.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
  
  // Example: Find all products by category and brand
  public Iterable<Product> findAllByCategoryAndBrand(String category, String brand);
  
  // Example: Find all products under a certain price
  public Iterable<Product> findAllByPriceLessThanEqual(double price);
  
  // Example: Find all products matching a name pattern
  public Iterable<Product> findAllByNameContainingIgnoreCase(String name);
}
