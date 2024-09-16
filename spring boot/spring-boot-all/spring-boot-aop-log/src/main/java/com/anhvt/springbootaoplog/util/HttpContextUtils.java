/**
 * Copyright 2024
 * Name: HttpContextUtils
 */
package com.anhvt.springbootaoplog.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * Comment class
 *
 * @author trunganhvu
 * @date 9/15/2024
 */
public class HttpContextUtils {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}