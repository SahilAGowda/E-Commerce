package com.e_commerce.e_commerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VerifyUser {

    @Id
    private String email;

    private String otp;

    // No-argument constructor
    public VerifyUser() {
    }

    // Constructor to initialize fields
    public VerifyUser(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    // Optional: Override toString for better debugging
    @Override
    public String toString() {
        return "VerifyUser{email='" + email + "', otp='" + otp + "'}";
    }

    // Optional: Override equals and hashCode for proper comparison and hashing in collections
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerifyUser that = (VerifyUser) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
