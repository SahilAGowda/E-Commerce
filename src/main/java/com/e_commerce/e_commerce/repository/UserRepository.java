package com.e_commerce.e_commerce.repository;

import org.springframework.data.repository.CrudRepository;
import com.e_commerce.e_commerce.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
  
  // Find user by their email address
  public User findByEmail(String email);
  
  // Find user by their phone number
  public User findByMobileNumber(String mobileNumber);

  // Find user by their username
  public User findByUsername(String username);
}
