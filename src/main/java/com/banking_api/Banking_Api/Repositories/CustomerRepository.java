package com.banking_api.Banking_Api.Repositories;

import com.banking_api.Banking_Api.Entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Object> findByEmail(@Email(message = "Email should be valid") @NotBlank(message = "Email cannot be blank") String email);
    // JpaRepository provides basic CRUD methods, so no extra code is needed here.
}

