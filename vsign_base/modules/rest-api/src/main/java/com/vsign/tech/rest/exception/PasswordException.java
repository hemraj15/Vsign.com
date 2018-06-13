package com.vsign.tech.rest.exception;

public class PasswordException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public PasswordException(String specificMessage) {
		super(specificMessage);
	}

	public PasswordException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public PasswordException(String specificMessage, String errorCode) {
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
