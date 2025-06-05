package com.kasunjay.springsecurity.repository;

import com.kasunjay.springsecurity.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.repository
 * @Interface: CustomerRepository
 * @Created on: 6/5/2025 at 2:53 PM
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
}
