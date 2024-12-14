package org.anhvt.springbootlogallreqres.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(CustomClientHttpRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Modify the request (e.g., add headers)
        request.getHeaders().add("Custom-Header", "value");

        // Log the outgoing request
        logger.info("Outgoing request: URI={}, Method={}, Headers={}",
                request.getURI(), request.getMethod(), request.getHeaders());

        // Proceed with the request execution
        return execution.execute(request, body);
    }
}
