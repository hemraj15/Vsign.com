package com.vsign.tech.rest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vsign.tech.data.dto.UserDto;
import com.vsign.tech.data.exception.InstanceNotFoundException;
import com.vsign.tech.message.exception.MailNotSentException;
import com.vsign.tech.rest.exception.EmptyListException;
import com.vsign.tech.rest.exception.PasswordException;
import com.vsign.tech.rest.exception.SignUpException;
import com.vsign.tech.rest.exception.UserNotFoundException;
import com.vsign.tech.rest.form.ResetPasswordForm;
import com.vsign.tech.rest.form.SignUpStep1Form;

public interface UserService {

	void initiateSignUp(SignUpStep1Form signUpstep1Form)
	        throws MailNotSentException, SignUpException;

	void resendEmail(String email) throws SignUpException;

	void sendForgotPasswordLink(String emailId)
	        throws PasswordException, MailNotSentException, UserNotFoundException;

	void resetPassword(ResetPasswordForm resetPasswordForm)
	        throws PasswordException, UserNotFoundException;

	List<UserDto> recruiterDTOList() throws EmptyListException;

	String loginUser(String token, String email) throws InstanceNotFoundException,
	        UsernameNotFoundException, PasswordException, Exception;

	String autoLoginUser(String token, String password) throws InstanceNotFoundException;
	
	String getEmail();
}
