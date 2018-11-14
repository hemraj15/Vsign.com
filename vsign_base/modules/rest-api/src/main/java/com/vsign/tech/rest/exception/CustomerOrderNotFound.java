/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class CustomerOrderNotFound extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public CustomerOrderNotFound(String specificMessage) {
		super(specificMessage);
	}

	public CustomerOrderNotFound(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public CustomerOrderNotFound(String specificMessage, String errorCode) {
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

