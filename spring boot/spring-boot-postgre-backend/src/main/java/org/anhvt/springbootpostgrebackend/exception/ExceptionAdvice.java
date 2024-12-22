package org.anhvt.springbootpostgrebackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.anhvt.springbootpostgrebackend.payload.response.APIResponse;
import org.anhvt.springbootpostgrebackend.utils.constant.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<APIResponse<ErrorResponse>> handleBusinessException(BusinessException ex) {
        LOGGER.error("BusinessException error: {} {}", ex.getCode(), ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), ex.getMessage());
        APIResponse<ErrorResponse> apiResponse = APIResponse.<ErrorResponse>builder()
                .status(ResponseCode.OPERATION_FAILED.getCode())
                .message(ResponseCode.OPERATION_FAILED.getMessage())
                .response(errorResponse)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<APIResponse<ErrorResponse>> handleSystemException(SystemException ex) {
        LOGGER.error("SystemException error: {} {}", "SYSTEM_ERROR", ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("SYSTEM_ERROR", ex.getMessage());
        APIResponse<ErrorResponse> apiResponse = APIResponse.<ErrorResponse>builder()
                .status(ResponseCode.SERVER_SYSTEM_EXCEPTION.getCode())
                .message(ResponseCode.SERVER_SYSTEM_EXCEPTION.getMessage())
                .response(errorResponse)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<ErrorResponse>> handleGenericException(Exception ex) {
        LOGGER.error("Exception error: {} {}", ResponseCode.INTERNAL_SERVER_EXCEPTION.name(), ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(ResponseCode.INTERNAL_SERVER_EXCEPTION.name(), ex.getMessage());
        APIResponse<ErrorResponse> apiResponse = APIResponse.<ErrorResponse>builder()
                .status(ResponseCode.INTERNAL_SERVER_EXCEPTION.getCode())
                .message(ResponseCode.INTERNAL_SERVER_EXCEPTION.getMessage())
                .response(errorResponse)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
