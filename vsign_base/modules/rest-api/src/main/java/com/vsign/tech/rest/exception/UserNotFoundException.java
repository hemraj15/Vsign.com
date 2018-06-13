/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class UserNotFoundException extends Exception {



	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public UserNotFoundException(String specificMessage) {
		super(specificMessage);
	}

	public UserNotFoundException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public UserNotFoundException(String specificMessage, String errorCode) {
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
