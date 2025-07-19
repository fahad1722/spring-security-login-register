package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findByEmail(String email);
}
