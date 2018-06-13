package com.vsign.tech.rest.utils;

import java.security.SecureRandom;

import org.springframework.security.crypto.codec.Base64;

public class PasswordUtils {

	private static SecureRandom random = new SecureRandom();

	// Generate 6 digit OTP number
	public static Integer get6digitUniqueNumber() {
		return random.nextInt(900000) + 100000;
	}

	public static String encode(String message) {
		return new String(Base64.encode(message.getBytes()));
	}

	public static String decode(String message) {
		return new String(Base64.decode(message.getBytes()));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(encode("test1234"));
	}
}
