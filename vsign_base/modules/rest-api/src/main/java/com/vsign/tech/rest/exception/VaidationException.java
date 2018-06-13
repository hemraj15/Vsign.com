/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class VaidationException extends Exception {
	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public VaidationException(String specificMessage) {
		super(specificMessage);
	}

	public VaidationException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public VaidationException(String specificMessage, String errorCode) {
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
