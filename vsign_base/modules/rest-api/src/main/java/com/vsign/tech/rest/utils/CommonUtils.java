/**
 * 
 */
package com.vsign.tech.rest.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;

import org.slf4j.LoggerFactory;

import com.vsign.tech.data.dto.PaymentRequestDto;

import org.slf4j.Logger;

/**
 * @author Hemraj
 *
 */
public class CommonUtils {
	private static Logger LOGGER =LoggerFactory.getLogger(CommonUtils.class);
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

	public static String executePost(String targetURL, String urlParameters) {
		  HttpURLConnection connection = null;
		  LOGGER.info("executePost");
		  try {
		    //Create connection
		    URL url = new URL(targetURL);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type", 
		        "application/x-www-form-urlencoded");

		    connection.setRequestProperty("Content-Length", 
		        Integer.toString(urlParameters.getBytes().length));
		    connection.setRequestProperty("Content-Language", "en-US");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    wr.writeBytes(urlParameters);
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    return response.toString();
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  } finally {
		    if (connection != null) {
		      connection.disconnect();
		    }
		  }
		}
	public  static String  processPaymentGet(String targetURL, PaymentRequestDto dto) throws IOException {
	    URL urlForGetRequest = new URL(targetURL);
	    StringBuffer response = new StringBuffer();
	    LOGGER.info("process payment GET target URL"+urlForGetRequest);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");
	    conection.setRequestProperty("pass", dto.getPass()); // set userId its a sample here
	    conection.setRequestProperty("ttype", dto.getTtype());
	    conection.setRequestProperty("prodid", dto.getProdid());
	    conection.setRequestProperty("amt", dto.getAmt());
	    conection.setRequestProperty("txncurr", dto.getTxncurr());
	    conection.setRequestProperty("txnscamt", null != dto.getTxnscamt() ? dto.getTxnscamt().toString() : "0");
	    conection.setRequestProperty("clientcode", dto.getClientcode());
	    conection.setRequestProperty("txnid", dto.getTxnid().toString());
	    conection.setRequestProperty("date", dto.getDate());
	    conection.setRequestProperty("custacc", dto.getCustacc().toString());
	    conection.setRequestProperty("mdd", dto.getMdd());
	    conection.setRequestProperty("ru", dto.getRu());
	    conection.setRequestProperty("signature", dto.getSignature());
	    conection.setRequestProperty("udf1", dto.getUdf1());
	    conection.setRequestProperty("udf2", dto.getUdf2());
	    conection.setRequestProperty("udf3", null != dto.getUdf3() ? dto.getUdf3().toString() : "NA");
	    conection.setRequestProperty("udf4", dto.getUdf4());
	    conection.setRequestProperty("udf5", null != dto.getUdf5() ? dto.getUdf5().toString() : "NA");
	    conection.setRequestProperty("udf6",null != dto.getUdf6() ? dto.getUdf6().toString() : "NA");
	    conection.setRequestProperty("udf9", "Vsign Licence Payment");
	    
	    
	    int responseCode = conection.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        while ((readLine = in .readLine()) != null) {
	            response.append(readLine);
	        } in .close();
	        // print result
	        System.out.println("JSON String Result " + response.toString());
	        //GetAndPost.POSTRequest(response.toString());
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
	    return response.toString();
	}
}
