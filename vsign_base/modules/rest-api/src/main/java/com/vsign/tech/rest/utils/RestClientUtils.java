
package com.vsign.tech.rest.utils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class RestClientUtils {

	public static String autoLogin(String aUserName, String aPassword, String aGrantType,
	        String aClientId, String aClientSecret, String aScope, String aHost, String aPort) {

		RestTemplate rest = new RestTemplate();

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("grant_type", aGrantType);
		map.add("client_id", aClientId);
		map.add("client_secret", aClientSecret);
		map.add("username", aUserName);
		map.add("password", aPassword);
		map.add("scope", aScope);

		String result = rest.postForObject(
		        "http://" + aHost + ":" + aPort + "/vsign-api/oauth/token", map, String.class);
		return result;
	}

	public static void main(String[] agrs) {

		System.out.println(RestClientUtils.autoLogin("hemraj.it12@gmail.com", "Hemraj@123",
		        "password", "printkaari_app", "printkaari_app_s3cr3t", "read,write,trust",
		        "162.220.61.86", "8080"));
	}

}

