package com.vsign.tech.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.PasswordException;
import com.vsign.tech.rest.exception.UserNotFoundException;
import com.vsign.tech.rest.form.ResetPasswordForm;
import com.vsign.tech.rest.model.ErrorResponse;
import com.vsign.tech.rest.service.UserService;
import com.vsign.tech.rest.utils.ErrorUtils;
import com.vsign.tech.rest.utils.PasswordUtils;

@RestController
@RequestMapping("/password")
public class PasswordController {

	private Logger		LOGGER	= LoggerFactory.getLogger(PasswordController.class);

	@Autowired
	private UserService	userService;

	@ResponseBody
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public Object forgotPassword(@RequestParam String emailId, HttpServletRequest request,
	        HttpServletResponse response) {
		Object data = null;

		try {
			LOGGER.info("emailToken : "+PasswordUtils.encode(emailId));
			userService.sendForgotPasswordLink(emailId);
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("message", "forgot email sent successfully to user!");
			dataMap.put("emailToken", PasswordUtils.encode(emailId));
			data = dataMap;

		} 
		
		catch(UserNotFoundException e){
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(ErrorCodes.USER_NOT_FOUND_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
		}
		catch (PasswordException e) {
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(e.getErrorCode());
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/reset", method = RequestMethod.PUT,consumes="application/json")
	public Object resetPassword(@RequestBody @Valid ResetPasswordForm resetPasswordForm,
	        BindingResult result, HttpServletResponse response) {
		Object data = null;
		if (result.hasErrors()) {
			String message = ErrorUtils.getTextValidationErrorMessage(result.getAllErrors());
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(message);
			((ErrorResponse) data).setMessage("Form validation failed!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		try {
			userService.resetPassword(resetPasswordForm);
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("message", "Password reset successfully");
			data = dataMap;
			LOGGER.error("Password reset Success Fully");
		} catch (PasswordException e) {
			LOGGER.error(e.getMessage(), e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(e.getErrorCode());
			((ErrorResponse) data).setMessage(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;

	}

}

