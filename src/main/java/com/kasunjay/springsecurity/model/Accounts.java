package com.kasunjay.springsecurity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.model
 * @Class: Accounts
 * @Created on: 6/17/2025 at 9:29 AM
 */
@Entity
@Getter
@Setter
public class Accounts {

    @Column(name = "customer_id")
    private long customerId;

    @Id
    @Column(name="account_number")
    private long accountNumber;

    @Column(name="account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_dt")
    private Date createDt;

}
