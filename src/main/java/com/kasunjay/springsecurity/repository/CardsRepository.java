package com.kasunjay.springsecurity.repository;

import com.kasunjay.springsecurity.model.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.repository
 * @Interface: CardsRepository
 * @Created on: 6/17/2025 at 9:48 AM
 */
@Repository
public interface CardsRepository extends CrudRepository<Cards, Long> {

    List<Cards> findByCustomerId(long customerId);

}
