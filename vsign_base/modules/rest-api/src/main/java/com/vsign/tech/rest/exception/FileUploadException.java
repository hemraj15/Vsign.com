package com.vsign.tech.rest.exception;

public class FileUploadException extends Exception {

	private static final long	serialVersionUID	= 1L;

	private String	          errorCode;

	public FileUploadException(String specificMessage) {
		super(specificMessage);
	}

	public FileUploadException(String specificMessage, Throwable e) {
		super(specificMessage, e);
	}

	public FileUploadException(String specificMessage, String errorCode) {
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
