package com.panic.panicnotificationservice.security;

import com.panic.panicnotificationservice.services.ClientService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class ApiKeyFilter extends OncePerRequestFilter {


    private final ClientService clientService;
    private String adminToken;

    public ApiKeyFilter(ClientService clientService, String adminToken) {
        this.clientService = clientService;
        this.adminToken = adminToken;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws
            ServletException, IOException {

        String reqApiKey = request.getHeader("X-API-KEY");

        boolean isApiKeyValid = clientService.isApiKeyExist(reqApiKey);

        if(reqApiKey == null && !isApiKeyValid && !reqApiKey.equals(adminToken)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid API Key");
            return;
        }

        var authenticationToken = new UsernamePasswordAuthenticationToken(reqApiKey,
                reqApiKey, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);

    }
}