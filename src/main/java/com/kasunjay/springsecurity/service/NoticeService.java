package com.kasunjay.springsecurity.service;

import com.kasunjay.springsecurity.model.Notice;

import java.util.List;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service
 * @Interface: NoticeService
 * @Created on: 6/17/2025 at 10:30 AM
 */
public interface NoticeService {

    List<Notice> getNotices();
}
