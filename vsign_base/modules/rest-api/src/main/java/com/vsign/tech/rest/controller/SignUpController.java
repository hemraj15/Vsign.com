package com.vsign.tech.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vsign.tech.auth.service.SystemRoles;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.InvalidFieldLengthException;
import com.vsign.tech.rest.exception.SignUpException;
import com.vsign.tech.rest.form.SignUpStep1Form;
import com.vsign.tech.rest.form.SignUpStep2Form;
import com.vsign.tech.rest.model.ErrorResponse;
import com.vsign.tech.rest.model.SignUpErrorResponse;
import com.vsign.tech.rest.service.VsignService;
import com.vsign.tech.rest.service.UserService;
import com.vsign.tech.rest.utils.ErrorUtils;
import com.vsign.tech.rest.utils.PasswordUtils;

@RestController
@RequestMapping("/signup")
public class SignUpController {

	private Logger				LOGGER	= LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	private UserService			userService;

	@Autowired
	private VsignService	vsignService;

	@ResponseBody	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")

	public Object initiate(@RequestBody @Valid SignUpStep1Form signUpstep1Form,
	        BindingResult result, HttpServletResponse response) {
		Object data = null;
		if (result.hasErrors()) {
			String message = ErrorUtils.getTextValidationErrorMessage(result.getAllErrors());
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(message);
			((ErrorResponse) data).setMessage("Form validation failed!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			try {
				userService.initiateSignUp(signUpstep1Form);
				Map<String, String> dataMap = new HashMap<>();
				String token =PasswordUtils.encode(signUpstep1Form.getEmail());
				signUpstep1Form.setEmailToken(token);
				dataMap.put("emailToken : ", token );
				dataMap.put("message :",
				        "Sign Up Initiated successfully, please check your email to proceed further.");
                                
                String tempPassword = vsignService.completeSignup(signUpstep1Form);
				LOGGER.info("temp pasword " + tempPassword);
				data = userService.autoLoginUser(token, tempPassword);
				//data = dataMap;
				response.setStatus(HttpServletResponse.SC_CREATED);

			} catch (SignUpException e) {
				SignUpErrorResponse errorResponse = new SignUpErrorResponse();
				errorResponse.setErrorCode(e.getErrorCode());
				errorResponse.setMessage(e.getMessage());
				switch (e.getErrorCode()) {
				case ErrorCodes.SIGNUP_ALREADY_INITIATED:
					response.setStatus(HttpServletResponse.SC_CONFLICT);
					errorResponse.setEmailToken(PasswordUtils.encode(signUpstep1Form.getEmail()));
					break;
				case ErrorCodes.SIGNUP_ALREADY_ACTIVE:
					response.setStatus(HttpServletResponse.SC_CONFLICT);
					break;
				default:
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					break;
				}
				data = errorResponse;
			}
			catch (InvalidFieldLengthException e) {
				LOGGER.error(e.getMessage(), e);
				data = new ErrorResponse();
				((ErrorResponse) data).setErrorCode(ErrorCodes.VALIDATION_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}   catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				data = new ErrorResponse();
				((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

			}
		}
		return data;

	}

	@ResponseBody
	@RequestMapping(value = "/complete", method = RequestMethod.POST, consumes = "application/json")
	public Object complete(@RequestBody @Valid SignUpStep2Form signUpStep2Form,
	        BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		Object data = null;
		if (result.hasErrors()) {
			String message = ErrorUtils.getTextValidationErrorMessage(result.getAllErrors());
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(message);
			((ErrorResponse) data).setMessage("Form validation failed!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			try {
				String tempPassword = vsignService.completeSignup(null);
				
				LOGGER.info("temp pasword " + tempPassword);
				LOGGER.info("email token" + signUpStep2Form.getEmailToken());
				data = userService.autoLoginUser(signUpStep2Form.getEmailToken(), tempPassword);
				response.setStatus(HttpServletResponse.SC_CREATED);
			} catch (SignUpException e) {
				data = new ErrorResponse();
				((ErrorResponse) data).setErrorCode(e.getErrorCode());
				((ErrorResponse) data).setMessage(e.getMessage());
				switch (e.getErrorCode()) {
				case ErrorCodes.SIGNUP_ALREADY_ACTIVE:
					response.setStatus(HttpServletResponse.SC_CONFLICT);
					break;
				case ErrorCodes.SIGNUP_ACCOUNT_DEACTIVATED:
					response.setStatus(HttpServletResponse.SC_CONFLICT);
					break;
				default:
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					break;
				}
			} catch (InvalidFieldLengthException e) {
				LOGGER.error(e.getMessage(), e);
				data = new ErrorResponse();
				((ErrorResponse) data).setErrorCode(ErrorCodes.VALIDATION_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				data = new ErrorResponse();
				((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/resend-email/{token}", method = RequestMethod.GET)
	public Object resendEmail(@PathVariable String token, HttpServletResponse response) {
		Object data = new ErrorResponse();
		try {
			userService.resendEmail(token);
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("message", "Resend email successfully!");
			data = dataMap;
		} catch (SignUpException e) {
			((ErrorResponse) data).setErrorCode(e.getErrorCode());
			((ErrorResponse) data).setMessage(e.getMessage());
			switch (e.getErrorCode()) {
			case ErrorCodes.SIGNUP_ALREADY_ACTIVE:
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				break;
			case ErrorCodes.SIGNUP_ACCOUNT_DEACTIVATED:
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				break;
			default:
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				break;
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;

	}

	

	@ResponseBody
	@RequestMapping(value = "/email", method = RequestMethod.GET)
//	@Secured({SystemRoles.ROLE_CUSTOMER})
	@Secured({SystemRoles.CUSTOMER})
	public Object getEmailUser()
	
	{
		Object data = null;
		data = userService.getEmail();
		return data;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/emailWoAuth", method = RequestMethod.GET)
//	@Secured({SystemRoles.ROLE_CUSTOMER})
	public Object getEmailUserWOAuth()
	
	{
		Object data = null;
		data = userService.getEmail();
		return data;
	}
}
