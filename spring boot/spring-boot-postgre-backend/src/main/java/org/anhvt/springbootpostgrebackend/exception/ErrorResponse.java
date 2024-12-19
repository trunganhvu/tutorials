package org.anhvt.springbootpostgrebackend.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
    private final String code;
    private final String message;
    private final Date date;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.date = Date.from(Instant.now());
    }
}
