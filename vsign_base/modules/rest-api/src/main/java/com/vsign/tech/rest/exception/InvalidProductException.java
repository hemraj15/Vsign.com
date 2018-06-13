/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class InvalidProductException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String				errorCode;

	public InvalidProductException(String specificMessage) {
		super(specificMessage);
	}

	public InvalidProductException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public InvalidProductException(String specificMessage, String errorCode) {
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

