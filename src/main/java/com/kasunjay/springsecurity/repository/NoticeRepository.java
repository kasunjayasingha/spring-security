package com.kasunjay.springsecurity.repository;

import com.kasunjay.springsecurity.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.repository
 * @Interface: NoticeRepository
 * @Created on: 6/17/2025 at 9:51 AM
 */
@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query(value = "from Notice n where CURDATE() BETWEEN n.noticBegDt AND n.noticEndDt")
    List<Notice> findAllActiveNotices();

}
