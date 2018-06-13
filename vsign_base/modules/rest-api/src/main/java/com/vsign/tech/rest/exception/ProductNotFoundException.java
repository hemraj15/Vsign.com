/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class ProductNotFoundException extends Exception {
	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public ProductNotFoundException(String specificMessage) {
		super(specificMessage);
	}

	public ProductNotFoundException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public ProductNotFoundException(String specificMessage, String errorCode) {
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

