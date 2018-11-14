/**
 * 
 */
package com.vsign.tech.rest.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Hemraj
 *
 */

public class AtomAESUtil {
	private String password = "8E41C78439831010F81F61C344B7BFC7";
	private String salt = "200000054575202";
	private static int pswdIterations = 65536;
	private static int keySize = 256;
	private final byte[] ivBytes = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };

	public String decrypt(String encryptedText, String key, String merchantTxnId) throws Exception {
		this.password = key;
		this.salt = merchantTxnId;
		return decrypt(encryptedText);
	}

	private String decrypt(String encryptedText)

			throws Exception

	{
		byte[] saltBytes = this.salt.getBytes("UTF-8");
		byte[] encryptedTextBytes = hex2ByteArray(encryptedText);
		SecretKeyFactory factory =

				SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		PBEKeySpec spec = new PBEKeySpec(this.password.toCharArray(), saltBytes, pswdIterations, keySize);

		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(),

				"AES");

		IvParameterSpec localIvParameterSpec = new

		IvParameterSpec(this.ivBytes);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(2, secret, localIvParameterSpec);
		byte[] decryptedTextBytes = (byte[]) null;
		decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
		return new String(decryptedTextBytes);
	}

	private byte[] hex2ByteArray(String sHexData) {
		byte[] rawData = new byte[sHexData.length() / 2];
		for (int i = 0; i < rawData.length; ++i) {
			int index = i * 2;
			int v = Integer.parseInt(sHexData.substring(index, index + 2), 16);
			rawData[i] = (byte) v;
		}
		return rawData;
	}
}
