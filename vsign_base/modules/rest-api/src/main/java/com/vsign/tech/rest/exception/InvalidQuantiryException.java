/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class InvalidQuantiryException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String				errorCode;

	public InvalidQuantiryException(String specificMessage) {
		super(specificMessage);
	}

	public InvalidQuantiryException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public InvalidQuantiryException(String specificMessage, String errorCode) {
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
