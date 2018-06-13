/**
 * 
 */
package com.vsign.tech.rest.utils;

import java.security.SecureRandom;

/**
 * @author Hemraj
 *
 */
public class CommonUtils {
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private static SecureRandom random = new SecureRandom();

	// Generate 5 digit OTP number
	public static Integer get5digitUniqueNumber() {
		return random.nextInt(90000) + 10000;
	}
	
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
		}
	
	public static String randomAlphaString(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_STRING.length());
		builder.append(ALPHA_STRING.charAt(character));
		}
		return builder.toString();
		}


}
