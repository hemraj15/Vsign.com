package com.vsign.tech.rest.utils;

public class StringUtil {

	@SuppressWarnings("resource")
	public static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}

	public static boolean isNullOrNil(String str) {
		return str == null || str.trim().length() <= 0;
	}
}
