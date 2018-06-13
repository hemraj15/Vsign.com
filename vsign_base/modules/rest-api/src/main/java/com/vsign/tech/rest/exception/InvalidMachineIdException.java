/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class InvalidMachineIdException extends Exception {
	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public InvalidMachineIdException(String specificMessage) {
		super(specificMessage);
	}

	public InvalidMachineIdException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public InvalidMachineIdException(String specificMessage, String errorCode) {
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
