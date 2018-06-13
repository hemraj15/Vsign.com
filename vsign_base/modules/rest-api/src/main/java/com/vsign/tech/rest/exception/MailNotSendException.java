/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class MailNotSendException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public MailNotSendException(String specificMessage) {
		super(specificMessage);
	}

	public MailNotSendException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public MailNotSendException(String specificMessage, String errorCode) {
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
