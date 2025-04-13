package com.e_commerce.e_commerce.repository;

import com.e_commerce.e_commerce.entity.VerifyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifyUserRepository extends JpaRepository<VerifyUser, String> {
    VerifyUser findByEmail(String email);
    void deleteByEmail(String email);
}
