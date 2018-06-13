package com.vsign.tech.rest.utils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.validation.ObjectError;

public class ErrorUtils {
	public static String getHtmlValidationErrorMessage(List<ObjectError> objectErrors) {
		StringBuilder stringBuilder = new StringBuilder(
		        "Form Validation failed. Following are the errors : <ol>");
		for (ObjectError objectError : objectErrors) {
			stringBuilder.append("<li>" + objectError.getDefaultMessage() + "</li>");
		}
		stringBuilder.append("</ol>");
		return stringBuilder.toString();
	}

	public static String getTextValidationErrorMessage(List<ObjectError> objectErrors) {
		StringBuilder stringBuilder = new StringBuilder();
		Set<String> uniqueErrors = new LinkedHashSet<>();
		for (ObjectError objectError : objectErrors) {
			uniqueErrors.add(objectError.getDefaultMessage());
		}
		for (String error : uniqueErrors) {
			stringBuilder.append(error + ",");
		}
		String errorCodes = stringBuilder.toString();
		errorCodes = errorCodes.substring(0, errorCodes.lastIndexOf(","));
		return errorCodes;
	}
}
