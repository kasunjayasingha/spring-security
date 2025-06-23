package com.kasunjay.springsecurity.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.filter
 * @Class: AuthoritiesLoggingAtFilter
 * @Created on: 6/23/2025 at 11:46 AM
 */
@Slf4j
public class AuthoritiesLoggingAtFilter implements Filter {
    /**
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
     *                 to for further processing
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Authentication Validation is in progress");
        chain.doFilter(request,response);

    }
}
