/**
 * Copyright 2024
 * Name: TimeFilter
 */
package com.anhvt.springbootfilterinterceptor.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/18/2024
 */
@Component
@WebFilter(urlPatterns = {"/*"})
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter - init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("TimeFilter - doFilter1");
        chain.doFilter(request, response);
        System.out.println("TimeFilter - doFilter2: " + Instant.now().toString());
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter - destroy");
    }

    @Bean
    public void testBeanFilter() {
        System.out.println("testBeanFilter");
    }
}
