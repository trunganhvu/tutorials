package org.anhvt.springbootpostgrebackend.utils.constant;

public enum ResponseCode {

    // Success Codes
    OK("S200", "Success"),
    NO_CONTENT("S204", "No Content"),

    // Client Error Codes
    BAD_REQUEST("S400", "Bad Request"),
    UNAUTHORIZED("S401", "Unauthorized"),
    FORBIDDEN("S403", "Forbidden"),
    NOT_FOUND("S404", "Not Found"),

    // Server Error Codes
    SERVER_SYSTEM_EXCEPTION("S500", "Server System Exception"),
    INTERNAL_SERVER_EXCEPTION("S599", "Internal Server Error"),

    // Business Exception Codes
    USER_NOT_FOUND("B404", "User Not Found"),
    INVALID_INPUT("B400", "Invalid Input Data"),
    DUPLICATE_RECORD("B409", "Duplicate Record"),
    OPERATION_FAILED("B500", "Business Operation Failed"),
    RESOURCE_CONFLICT("B409", "Resource Conflict");

    private final String code;

    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
