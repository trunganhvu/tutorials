package org.anhvt.springbootpostgrebackend.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.anhvt.springbootpostgrebackend.exception.ErrorResponse;
import org.anhvt.springbootpostgrebackend.payload.response.APIResponse;
import org.anhvt.springbootpostgrebackend.utils.constant.ResponseCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(response.getStatus());

        ErrorResponse errorResponse = new ErrorResponse(String.valueOf(response.getStatus()), authException.getMessage());
        APIResponse<ErrorResponse> apiResponse = APIResponse.<ErrorResponse>builder()
                .status(ResponseCode.UNAUTHORIZED.getCode())
                .message(ResponseCode.UNAUTHORIZED.getMessage())
                .response(errorResponse)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(apiResponse);
        response.getWriter().write(json);
        response.getWriter().flush();
    }
}
