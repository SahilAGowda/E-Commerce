package com.e_commerce.e_commerce.service;

import com.e_commerce.e_commerce.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    List<User> getAllUsers();
    boolean existsByEmail(String email);
} 