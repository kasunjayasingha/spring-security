package com.kasunjay.springsecurity.repository;

import com.kasunjay.springsecurity.model.AccountTransactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.repository
 * @Interface: AccountTransactionsRepository
 * @Created on: 6/17/2025 at 9:47 AM
 */
@Repository
public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, String> {

    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(long customerId);

}
