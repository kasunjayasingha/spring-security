package com.kasunjay.springsecurity.controller;

import com.kasunjay.springsecurity.model.Notice;
import com.kasunjay.springsecurity.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: kasun
 * @Package: com.kasunjay.springsecurity.controller
 * @Class: NoticesController
 * @Created on: 5/27/2025 at 9:44 PM
 */
@RestController
@RequiredArgsConstructor
public class NoticesController {

    private  final NoticeService noticeService;

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices () {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body(noticeService.getNotices());
    }
}
