package com.vsign.tech.auth.exception;

public class AuthenticationException extends Exception {

    private static final long serialVersionUID = 1L;

    public AuthenticationException(String specificMessage) {
        super(specificMessage);
    }

    public AuthenticationException(Throwable throwable) {
        super(throwable);
    }

    public AuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
