package com.vsign.tech.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class LogoutController {

	@Autowired
	private TokenStore tokenStore;

	@RequestMapping(value = "/oauth/revoke-token", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Object logout(HttpServletRequest request) {
		Object data = null;
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
			String tokenValue = authHeader.replace("Bearer", "").trim();
			OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
			tokenStore.removeAccessToken(accessToken);
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("message", "Logged out succesfully!");
			data = dataMap;
		}
		return data;
	}

}
