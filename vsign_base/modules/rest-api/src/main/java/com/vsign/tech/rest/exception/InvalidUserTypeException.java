/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class InvalidUserTypeException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String				errorCode;

	public InvalidUserTypeException(String specificMessage) {
		super(specificMessage);
	}

	public InvalidUserTypeException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public InvalidUserTypeException(String specificMessage, String errorCode) {
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

