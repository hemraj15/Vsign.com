/**
 * 
 */
package com.vsign.tech.rest.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.utils.ValidationUtils;

/**
 * @author Hemraj
 *
 */
public class LoginForm {
	
	@NotNull(message = ErrorCodes.LOGIN_USER_ID_NULL)
	@NotEmpty(message = ErrorCodes.LOGIN_USER_ID_EMPTY)
	@Email(message = ErrorCodes.LOGIN_EMAIL_INVALID, regexp = ValidationUtils.EMAIL_PATTERN)
	private String username;
	
	@NotNull(message = ErrorCodes.LOGIN_USER_PASSWORD_NULL)
	@NotEmpty(message = ErrorCodes.LOGIN_USER_PASSWORD_EMPTY)
	@Length(max = 25, min =5, message = ErrorCodes.PASSWORD_INVALID)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}