package com.trio.challenge.exception;

import static java.lang.String.format;

public class CrossIntegrityException extends RuntimeException {
    public CrossIntegrityException() {
        super();
    }

    public CrossIntegrityException(String message) {
        super(message);
    }

    public CrossIntegrityException(String message, Long id) {
        super(format(message, id));
    }

    public CrossIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }

    public CrossIntegrityException(Throwable cause) {
        super(cause);
    }

}
