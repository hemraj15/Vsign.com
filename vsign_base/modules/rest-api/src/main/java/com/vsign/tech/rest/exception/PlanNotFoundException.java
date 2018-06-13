
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class PlanNotFoundException extends Exception {


	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public PlanNotFoundException(String specificMessage) {
		super(specificMessage);
	}

	public PlanNotFoundException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public PlanNotFoundException(String specificMessage, String errorCode) {
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

