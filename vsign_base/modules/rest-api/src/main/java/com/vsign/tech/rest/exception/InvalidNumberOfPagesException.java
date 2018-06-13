/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class InvalidNumberOfPagesException extends Exception {
	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public InvalidNumberOfPagesException(String specificMessage) {
		super(specificMessage);
	}

	public InvalidNumberOfPagesException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public InvalidNumberOfPagesException(String specificMessage, String errorCode) {
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
