package com.example.zetiqa.repository;

import com.example.zetiqa.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByEmail(String email);
}
