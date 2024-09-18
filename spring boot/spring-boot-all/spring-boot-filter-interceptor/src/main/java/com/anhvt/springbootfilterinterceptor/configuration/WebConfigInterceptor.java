/**
 * Copyright 2024
 * Name: WebConfigInterceptor
 */
package com.anhvt.springbootfilterinterceptor.configuration;

import com.anhvt.springbootfilterinterceptor.filter.TimeFilter;
import com.anhvt.springbootfilterinterceptor.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/18/2024
 */
@Configuration
public class WebConfigInterceptor implements WebMvcConfigurer {
    @Autowired
    private TimeInterceptor timeInterceptor;

//    @Bean
//    public FilterRegistrationBean timeFilter() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        System.out.println("WebConfig - timeFilter - 1");
//        TimeFilter timeFilter = new TimeFilter();
//        filterRegistrationBean.setFilter(timeFilter);
//        System.out.println("WebConfig - timeFilter - 2");
//        timeFilter.testBeanFilter();
//        List<String> urlList = new ArrayList<>();
//        urlList.add("/*");
//        System.out.println("WebConfig - timeFilter - 3");
//
//        filterRegistrationBean.setUrlPatterns(urlList);
//        return filterRegistrationBean;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("WebConfig - addInterceptors");

        registry.addInterceptor(timeInterceptor);
    }

    @Bean
    public Integer testBeanWebConfig() {
        System.out.println("testBeanWebConfig");
        return 0;
    }
}
