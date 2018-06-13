package com.vsign.tech.rest.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vsign.tech.auth.service.SystemRoles;
import com.vsign.tech.rest.exception.EmptyListException;
import com.vsign.tech.rest.model.ErrorResponse;
import com.vsign.tech.rest.service.UserService;

@RestController
@RequestMapping("/recruiters")
public class RecruiterController {

	@Autowired
	UserService		userService;

	private Logger	LOGGER	= LoggerFactory.getLogger(RecruiterController.class);

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	//@Secured(value = { SystemRoles.ROLE_ADMIN })
	public Object getRecruiters(HttpServletResponse response) {
		Object data = null;
		try {

			data = userService.recruiterDTOList();
		} catch (EmptyListException e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}

		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		return data;

	}

}
