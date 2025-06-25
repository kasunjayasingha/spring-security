package com.kasunjay.springsecurity.filter;

import com.kasunjay.springsecurity.constants.ApplicationConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Locale;

/**
 * @Author: kasun_t
 * @Package: com.kasunjay.springsecurity.filter
 * @Class: RequestValidationBeforeFilter
 * @Created on: 6/20/2025 at 8:53 AM
 * A servlet filter that validates incoming Authorization headers before authentication.
 */
public class RequestValidationBeforeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(header)) {
            header = header.trim();

            try {
                if (StringUtils.startsWithIgnoreCase(header, ApplicationConstants.BASIC_PREFIX)) {
                    validateBasicAuth(header, res);
                } else if (StringUtils.startsWithIgnoreCase(header, ApplicationConstants.BEARER_PREFIX)) {
                    validateBearerToken(header, res, req);
                } else {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            } catch (BadCredentialsException e) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private void validateBasicAuth(String header, HttpServletResponse res) {
        String base64Credentials = header.substring(ApplicationConstants.BASIC_PREFIX.length()).trim();
        byte[] decoded;

        try {
            decoded = Base64.getDecoder().decode(base64Credentials.getBytes(StandardCharsets.UTF_8));
            String credentials = new String(decoded, StandardCharsets.UTF_8);
            int delim = credentials.indexOf(':');

            if (delim == -1) {
                throw new BadCredentialsException("Invalid basic authentication token");
            }

            String email = credentials.substring(0, delim);
            if (email.toLowerCase(Locale.ROOT).contains("test")) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token", e);
        }
    }

    private void validateBearerToken(String header, HttpServletResponse res, HttpServletRequest request) {
        String jwtToken = header.substring(ApplicationConstants.BEARER_PREFIX.length()).trim();

        if (!StringUtils.hasText(jwtToken)) {
            throw new BadCredentialsException("Invalid JWT token");
        }

        // Add your JWT validation logic here if needed.
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // No-op
    }

    @Override
    public void destroy() {
        // No-op
    }
}
