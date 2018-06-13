/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class InvalidTransactionException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String				errorCode;

	public InvalidTransactionException(String specificMessage) {
		super(specificMessage);
	}

	public InvalidTransactionException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public InvalidTransactionException(String specificMessage, String errorCode) {
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