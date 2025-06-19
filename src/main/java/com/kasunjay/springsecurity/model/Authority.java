package com.kasunjay.springsecurity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.model
 * @Class: Authority
 * @Created on: 6/19/2025 at 8:52 AM
 */
@Entity
@Getter
@Setter
@Table(name="authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

}
