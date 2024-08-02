package com.lenora.staj.mesajlasma.rest.filter;

import com.lenora.staj.mesajlasma.rest.config.ExternalServiceChecker;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class CustomRequestFilter extends OncePerRequestFilter {

    @Autowired
    private ExternalServiceChecker serviceChecker;

    private static final List<String> PERMIT_ALL_URLS = List.of(
            "/api/auth/login",
            "/api/auth/createUser",
            "/api/auth/logout",
            "/api/auth/userInfo"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();

        if (PERMIT_ALL_URLS.stream().anyMatch(requestUri::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Missing or invalid Authorization header");
            return;
        }

        String token = authHeader.substring(7);
        /*if (!serviceChecker.isServiceAvailable(token)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "External service is unavailable");
            return;
        }*/

        filterChain.doFilter(request, response);
    }
}
