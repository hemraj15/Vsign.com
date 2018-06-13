/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class OrderStatusException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public OrderStatusException(String specificMessage) {
		super(specificMessage);
	}

	public OrderStatusException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public OrderStatusException(String specificMessage, String errorCode) {
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
