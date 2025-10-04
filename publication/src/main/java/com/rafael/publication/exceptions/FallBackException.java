package com.rafael.publication.exceptions;

public class FallBackException extends RuntimeException {

    public FallBackException() {
        super();
    }

    public FallBackException(String message) {
        super(message);
    }

    public FallBackException(String message, Throwable cause) {
        super(message, cause);
    }

}
