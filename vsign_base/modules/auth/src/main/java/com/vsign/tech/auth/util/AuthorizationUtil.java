package com.vsign.tech.auth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vsign.tech.auth.service.UserDetailsImpl;
import com.vsign.tech.data.dao.entity.User;

public final class AuthorizationUtil {

	private static Logger logger = LoggerFactory.getLogger(AuthorizationUtil.class);

	public static User getLoggedInUser() {

		logger.debug(" >> in getLoginUser");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			
		logger.debug("logged in iuser null block");
			return null;
		}

		Object principal = authentication.getPrincipal();

		if (principal instanceof UserDetailsImpl) {
			
			logger.debug("Logged in user found ");
			return ((UserDetailsImpl) principal).getUser();
		}
		return null;
	}

	/*
	 * return the httpSession object
	 */
	public static ServletRequestAttributes getSession() {
		logger.debug(" >> getSession");
		return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	}

	public static void setSessionAttribute(String attributeName, Object attributeValue) {
		logger.debug(" >> setSessionAttribute");
		getSession().setAttribute(attributeName, attributeValue, RequestAttributes.SCOPE_SESSION);
		logger.debug("setSessionAttribute >> ");
	}

	public static Object getSessionAttribute(String attributeName) {
		Object attributeValue = getSession().getAttribute(attributeName,
		        RequestAttributes.SCOPE_SESSION);
		logger.debug("Value Retrieved From Session : " + attributeValue);
		return attributeValue;
	}

	public static boolean isRolePresent(String role) {
		logger.debug("is role present of:" + role);
		boolean isRolePresent = false;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
			isRolePresent = grantedAuthority.getAuthority().equals(role);
			if (isRolePresent)
				break;
		}
		return isRolePresent;
	}
}
