package com.vsign.tech.rest.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticateUtil {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthenticateUtil.class);

	public static String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	public static String calculateRFC2104HMAC(String data, String secret)
			throws java.security.SignatureException {
		String digest = null;
		try {
			SecretKeySpec key = new SecretKeySpec(secret.getBytes(),
					HMAC_SHA1_ALGORITHM);
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(key);
			byte[] bytes = mac.doFinal(data.getBytes("ASCII"));
			StringBuffer hash = new StringBuffer();

			for (int i = 0; i < bytes.length; i++) {
				String hex = Integer.toHexString(0xFF & bytes[i]);
				if (hex.length() == 1) {
					hash.append('0');
				}
				hash.append(hex);
			}
			digest = hash.toString();
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage());
		} catch (InvalidKeyException e) {
			LOGGER.error(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage());
		}
		return digest;
	}
}
