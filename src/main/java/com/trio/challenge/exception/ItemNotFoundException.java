package com.trio.challenge.exception;

import org.springframework.http.HttpStatus;

import static java.lang.String.format;

public class ItemNotFoundException extends RuntimeException {
    private final HttpStatus status;

    public ItemNotFoundException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }

    public ItemNotFoundException(String message, Long id) {
        super(format(message, id));
        this.status = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

