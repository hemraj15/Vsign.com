/**
 * 
 */
package com.vsign.tech.rest.exception;

/**
 * @author Hemraj
 *
 */
public class FileDownloadException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public FileDownloadException(String specificMessage) {
		super(specificMessage);
	}

	public FileDownloadException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public FileDownloadException(String specificMessage, String errorCode) {
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

