/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class EmptyListException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String				errorCode;

	public EmptyListException(String specificMessage) {
		super(specificMessage);
	}

	public EmptyListException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public EmptyListException(String specificMessage, String errorCode) {
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
