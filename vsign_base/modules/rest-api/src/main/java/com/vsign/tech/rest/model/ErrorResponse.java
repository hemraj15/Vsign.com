package com.vsign.tech.rest.model;

public class ErrorResponse {
    private String errorCode;
    private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ErrorResponse [errorCode=");
        builder.append(errorCode);
        builder.append(", message=");
        builder.append(message);
        builder.append("]");
        return builder.toString();
    }

}
