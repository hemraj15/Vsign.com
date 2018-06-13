/**
 * 
 */
package com.vsign.tech.rest.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsign.tech.auth.service.SystemRoles;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.model.ErrorResponse;
import com.vsign.tech.rest.service.DepartmentService;

/**
 * @author Hemraj
 *
 */
@RestController
@RequestMapping(value="/")
public class DepartmentController {
	
	private Logger		LOGGER	= LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService deptService;
	
	//@Secured(value = { SystemRoles.ROLE_ADMIN})
	@Secured(value = { SystemRoles.ADMIN})
	@RequestMapping(value="/companies/{companyId}/departments", method = RequestMethod.GET)
	public Object fetchDepartmentListByCompanyId( @PathVariable  Long companyId,
			HttpServletResponse response) {
		LOGGER.info(">> fetchDepartmentListByCompanyId");
		Object data = null;
		try {
			data = deptService.getDepartmentListByCompanyId(companyId);
		} catch (DatabaseException e) {
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			LOGGER.error("Exception" + e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;
	}


}
