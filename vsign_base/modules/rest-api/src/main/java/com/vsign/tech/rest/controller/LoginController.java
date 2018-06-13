/**
 * 
 */
package com.vsign.tech.rest.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.PasswordException;
import com.vsign.tech.rest.exception.StatusException;
import com.vsign.tech.rest.exception.UserNotFoundException;
import com.vsign.tech.rest.form.LoginForm;
import com.vsign.tech.rest.model.ErrorResponse;
import com.vsign.tech.rest.service.UserService;
import com.vsign.tech.rest.utils.ErrorUtils;
import com.vsign.tech.rest.utils.PasswordUtils;

/**
 * @author Hemraj
 *
 */

@RestController
@RequestMapping("/app")
public class LoginController {

	private Logger		LOGGER	= LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService	userService;

	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public Object userLogin(@RequestBody @Valid LoginForm loginForm, BindingResult result,
	        HttpServletResponse response) {
		LOGGER.info("Login user !!");

		Object data;
		if (result.hasErrors()) {
			String message = ErrorUtils.getTextValidationErrorMessage(result.getAllErrors());
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(message);
			((ErrorResponse) data).setMessage("Form validation failed!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		}
		try {
			/*
			 * String pwd=request.getParameter("passowrd"); String
			 * userId=request.getParameter("userName");
			 */
			LOGGER.info("Password entered :" + loginForm.getPassword());
			LOGGER.info("user Name :" + loginForm.getUsername());

			data = userService.loginUser(PasswordUtils.encode(loginForm.getUsername()),
			        loginForm.getPassword());
			LOGGER.info("login data response :" + data);
		}

		
		catch(UserNotFoundException e){
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(ErrorCodes.USER_NOT_FOUND_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
		}
		catch (PasswordException p) {
			LOGGER.error(p.getMessage(), p);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(ErrorCodes.PASSWORD_INVALID);
			((ErrorResponse) data).setMessage(p.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} catch (StatusException e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(ErrorCodes.SIGNUP_INITIATED);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(ErrorCodes.PASSWORD_INVALID);
			((ErrorResponse) data).setMessage(e.getMessage() + " - " + "Invalid Password");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;

	}
}
