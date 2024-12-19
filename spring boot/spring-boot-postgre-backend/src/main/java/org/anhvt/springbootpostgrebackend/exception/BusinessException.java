package org.anhvt.springbootpostgrebackend.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessException extends RuntimeException {
    private String code;

    public BusinessException(String errorCode, String message) {
        super(message);
        this.code = errorCode;
    }
}
