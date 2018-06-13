package com.vsign.tech.message.exception;

public class MailNotSentException extends Exception {

    private static final long serialVersionUID = 1L;

    public MailNotSentException(String specificMessage) {
        super(specificMessage);
    }

    public MailNotSentException(Throwable throwable) {
        super(throwable);
    }

    public MailNotSentException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
