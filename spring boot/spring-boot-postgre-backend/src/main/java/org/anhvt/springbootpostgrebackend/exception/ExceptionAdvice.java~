package org.anhvt.springbootpostgrebackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.anhvt.springbootpostgrebackend.payload.response.APIResponse;
import org.anhvt.springbootpostgrebackend.utils.constant.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<APIResponse<ErrorResponse>> handleBusinessException(BusinessException ex) {
        log.error(String.format("BusinessException error: %s %s %s", ex.getCode(), ex.getMessage()));
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
        ErrorResponse errorResponse = new ErrorResponse(ResponseCode.INTERNAL_SERVER_EXCEPTION.name(), ex.getMessage());
        APIResponse<ErrorResponse> apiResponse = APIResponse.<ErrorResponse>builder()
                .status(ResponseCode.INTERNAL_SERVER_EXCEPTION.getCode())
                .message(ResponseCode.INTERNAL_SERVER_EXCEPTION.getMessage())
                .response(errorResponse)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
