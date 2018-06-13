/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class TransactionOrderNotFoundException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public TransactionOrderNotFoundException(String specificMessage) {
		super(specificMessage);
	}

	public TransactionOrderNotFoundException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public TransactionOrderNotFoundException(String specificMessage, String errorCode) {
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