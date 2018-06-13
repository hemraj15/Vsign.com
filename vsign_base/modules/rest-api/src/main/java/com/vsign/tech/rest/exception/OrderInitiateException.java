package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class OrderInitiateException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public OrderInitiateException(String specificMessage) {
		super(specificMessage);
	}

	public OrderInitiateException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public OrderInitiateException(String specificMessage, String errorCode) {
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

