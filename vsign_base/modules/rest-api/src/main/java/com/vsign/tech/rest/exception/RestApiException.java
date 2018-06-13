package com.vsign.tech.rest.exception;

public abstract class RestApiException extends Exception {

	private static final long serialVersionUID = 1L;

	protected RestApiException(String specificMessage) {
		super(specificMessage);
	}

}
