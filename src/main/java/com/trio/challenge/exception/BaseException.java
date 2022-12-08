package com.trio.challenge.exception;

import com.trio.challenge.handler.dto.ErrorCode;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;

public class BaseException extends RuntimeException {
    private final HttpStatus status;
    private final ErrorCode errorCode;

    public BaseException(HttpStatus status, ErrorCode errorCode, String message) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    public BaseException(HttpStatus status, ErrorCode errorCode, String message, Long id) {
        super(format(message, id));
        this.status = status;
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
