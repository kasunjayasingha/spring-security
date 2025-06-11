package com.kasunjay.springsecurity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.model
 * @Class: Customer
 * @Created on: 6/5/2025 at 12:52 PM
 */
@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String pwd;
    @Column(name = "role")
    private String role;


}
