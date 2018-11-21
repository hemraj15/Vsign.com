
package com.vsign.tech.rest.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vsign.tech.rest.service.PaymentServiceImpl;

import bsh.This;

/**
 * @author Hemraj
 *
 */
public class SignatureGenerate {
	/*
	 * Hashing using key with HMACSHA512
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(This.class);
	public static byte[] encodeWithHMACSHA2(String text, String keyString) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
		java.security.Key sk = new javax.crypto.spec.SecretKeySpec(keyString.getBytes("UTF-8"), "HMACSHA512");
		javax.crypto.Mac mac = javax.crypto.Mac.getInstance(sk.getAlgorithm());
		mac.init(sk);
		byte[] hmac = mac.doFinal(text.getBytes("UTF-8"));
		return hmac;
	}

/*Merchant Integration Document©
	atom technologies limited 21*/

	/*
	 * Convert from byte array to HexString
	 */
	public static String byteToHexString(byte byData[]) {
		StringBuilder sb = new StringBuilder(byData.length * 2);
		for (int i = 0; i < byData.length; i++) {
			int v = byData[i] & 0xff;
			if (v < 16)
				sb.append('0');
			sb.append(Integer.toHexString(v));
		}
		return sb.toString();
	}

	/*
	 * Encoded with HMACSHA512 and encoded with utf-8 using url encoder for
	 * given list of parameter values appended with the key
	 */
	public static String getEncodedValueWithSha2(String hashKey, String... param) {
		String resp = null;
		StringBuilder sb = new StringBuilder();
		for (String s : param) {
			sb.append(s);
		}
		try {
			LOGGER.info("[getEncodedValueWithSha2]String to Encode =" + sb.toString());
			resp = byteToHexString(encodeWithHMACSHA2(sb.toString(), hashKey));
			// resp = URLEncoder.encode(resp,"UTF-8");
		} catch (Exception e) {
			LOGGER.info("[getEncodedValueWithSha2]Unable to encocd value with key :" + hashKey + " and input :"
					+ sb.toString());
			e.printStackTrace();
		}
		return resp;
	}

/*Merchant Integration Document©
	atom technologies limited 22*/

	public static void main(String[] args) {
		String login = "197";
		String pass = "Test@123";
		String ttype = "NBFundTransfer";
		String prodid = "NSE";
		String txnid = "30";
		String amt = "0";
		String txncurr = "INR";
		String reqHashKey = "KEY123657234";
		// login,pass,ttype,prodid,txnid,amt,txncurr
		String signature_request = getEncodedValueWithSha2(reqHashKey, login, pass, ttype, prodid, txnid, amt, txncurr);
		System.out.println("Request signature ::" + signature_request);
		// Response signature based on parameters
		String mmp_txn = "121212";
		String mer_txn = "Mer123";
		String f_code = "Ok";
		String prod = "NSE";
		String discriminator = "NB";
		amt = "3000.00";
		String bank_txn = "Bank123";
		String respHashKey = "KEYRESP123657234";
		// mmp_txn,mer_txn, f_code, prod, discriminator, amt, bank_txn
		String signature_response = getEncodedValueWithSha2(respHashKey, mmp_txn, mer_txn, f_code, prod, discriminator,
				amt, bank_txn);
		System.out.println("Response signature ::" + signature_response);
	}
}