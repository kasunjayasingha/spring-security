package com.kasunjay.springsecurity.repository;

import com.kasunjay.springsecurity.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.repository
 * @Interface: ContactRepository
 * @Created on: 6/17/2025 at 9:49 AM
 */
@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {


}
