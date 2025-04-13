package com.e_commerce.e_commerce.service.impl;

import com.e_commerce.e_commerce.entity.User;
import com.e_commerce.e_commerce.repository.UserRepository;
import com.e_commerce.e_commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        // Validate user
        if (existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        
        // Set default role if not specified
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("USER");
        }
        
        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        // Check if user exists
        Optional<User> existingUser = getUserById(user.getId());
        if (!existingUser.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        
        // If email changed, check if new email already exists
        if (!existingUser.get().getEmail().equals(user.getEmail()) && existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        
        // Handle password - don't update if not provided
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            user.setPassword(existingUser.get().getPassword());
        } else {
            // Encode new password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    @Override
    public Optional<User> authenticateUser(String email, String password) {
        Optional<User> user = getUserByEmail(email);
        
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        
        return Optional.empty();
    }
    
    @Override
    @Transactional
    public boolean changePassword(Long userId, String currentPassword, String newPassword) {
        Optional<User> user = getUserById(userId);
        
        if (user.isPresent() && passwordEncoder.matches(currentPassword, user.get().getPassword())) {
            user.get().setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user.get());
            return true;
        }
        
        return false;
    }
    
    @Override
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
}