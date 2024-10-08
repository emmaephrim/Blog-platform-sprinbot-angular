package com.blog_platform.spirngsecuritycors;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(CorsFilter.class);

    public CorsFilter() {
        log.info("CorsFilter Init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // You can allow multiple origins or use "*"
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        // Allow credentials like cookies or Authorization header
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // Allow all methods
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, PATCH");
        // Cache the preflight response for 1 hour
        response.setHeader("Access-Control-Max-Age", "3600");
        // Allow custom headers
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");
        // Expose the Authorization header
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
