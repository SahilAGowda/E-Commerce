// filepath: c:\Users\Sahil\Desktop\E-commerce\e_commerce\src\main\java\com\e_commerce\e_commerce\model\LoginResponse.java
package com.e_commerce.e_commerce.model;

import com.e_commerce.e_commerce.entity.User;

public class LoginResponse {
    private User user;
    private String message;

    // Constructor
    public LoginResponse(User user, String message) {
        this.user = user;
        this.message = message;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}