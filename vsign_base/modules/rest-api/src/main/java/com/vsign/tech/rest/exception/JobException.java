package com.vsign.tech.rest.exception;

public class JobException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public JobException(String specificMessage) {
		super(specificMessage);
	}

	public JobException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public JobException(String specificMessage, String errorCode) {
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
