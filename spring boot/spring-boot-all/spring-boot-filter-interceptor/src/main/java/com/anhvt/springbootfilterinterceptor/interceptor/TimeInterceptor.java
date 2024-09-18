/**
 * Copyright 2024
 * Name: TimeInterceptor
 */
package com.anhvt.springbootfilterinterceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/18/2024
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("TimeInterceptor - preHandle");
        request.setAttribute("startTime", new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("TimeInterceptor - postHandle1");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("TimeInterceptor - postHandle2 " + start);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("TimeInterceptor - afterCompletion1");
        Long start = (Long) request.getAttribute("startTime");
        System.out.println("TimeInterceptor - afterCompletion2 " + start);
        System.out.println("TimeInterceptor - afterCompletion3 " + ex);
    }

    @Bean
    public void testBeanInterceptor() {
        System.out.println("testBeanInterceptor");
    }
}
