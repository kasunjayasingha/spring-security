package com.kasunjay.springsecurity.service.impl;

import com.kasunjay.springsecurity.model.Notice;
import com.kasunjay.springsecurity.repository.NoticeRepository;
import com.kasunjay.springsecurity.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.service.impl
 * @Class: NoticeServiceImpl
 * @Created on: 6/17/2025 at 10:30 AM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public List<Notice> getNotices() {
        List<Notice> notices = noticeRepository.findAllActiveNotices();
        if (notices != null) {
            return notices;
        } else {
            return null;
        }
    }
}
