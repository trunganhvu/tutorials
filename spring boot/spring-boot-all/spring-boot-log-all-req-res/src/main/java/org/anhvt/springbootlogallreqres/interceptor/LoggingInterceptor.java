package org.anhvt.springbootlogallreqres.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    /**
     * Logs HTTP method and URI before the request is handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("Request: {} {}", request.getMethod(), request.getRequestURI());

        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);

        logger.info("Request: {} {} (ID: {})", request.getMethod(), request.getRequestURI(), requestId);
        logger.debug("Headers: {}", getHeadersAsString(request));

        if (isContentTypeAllowed(request.getContentType())) {
            logger.debug("Body: {}", getRequestBody(request));
        }
        return true;  // Allows the request to proceed
    }

    /**
     * Logs response status and URI after request completion. Logs exceptions if any
     * @param request current HTTP request
     * @param response current HTTP response
     * @param HandlerMethod the handler (or {@link HandlerMethod}) that started asynchronous
     * execution, for type and/or instance examination
     * @param ex any exception thrown on handler execution, if any; this does not
     * include exceptions that have been handled through an exception resolver
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object HandlerMethod, Exception ex) {
        logger.info("Response: {} {}", response.getStatus(), request.getRequestURI());
        if (ex != null) {
            // Logs any exception
            logger.error("Exception: ", ex);
        }
    }
}
