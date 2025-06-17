package com.kasunjay.springsecurity.repository;

import com.kasunjay.springsecurity.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.repository
 * @Interface: AccountsRepository
 * @Created on: 6/17/2025 at 9:48 AM
 */
@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {

    Accounts findByCustomerId(long customerId);

}
