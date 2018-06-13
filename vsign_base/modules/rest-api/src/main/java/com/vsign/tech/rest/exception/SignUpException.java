package com.vsign.tech.rest.exception;

public class SignUpException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public SignUpException(String specificMessage) {
		super(specificMessage);
	}

	public SignUpException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public SignUpException(String specificMessage, String errorCode) {
		super(specificMessage);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
