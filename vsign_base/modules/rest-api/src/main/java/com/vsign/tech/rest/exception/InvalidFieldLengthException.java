/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class InvalidFieldLengthException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public InvalidFieldLengthException(String specificMessage) {
		super(specificMessage);
	}

	public InvalidFieldLengthException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public InvalidFieldLengthException(String specificMessage, String errorCode) {
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
