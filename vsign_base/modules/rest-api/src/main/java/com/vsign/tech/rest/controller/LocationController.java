package com.vsign.tech.rest.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vsign.tech.auth.service.SystemRoles;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.model.ErrorResponse;
import com.vsign.tech.rest.service.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {

	private Logger			LOGGER	= LoggerFactory.getLogger(LocationController.class);

	@Autowired
	private LocationService	locationService;

	@ResponseBody
	@RequestMapping(value = "/countries",method = RequestMethod.GET)
	//@Secured({SystemRoles.ROLE_CUSTOMER})
	//@Secured({SystemRoles.CUSTOMER})
	public Object fetchAllCountries(HttpServletResponse response) {
		Object data = null;
		try {
			data = locationService.getAllCountries();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		return data;

	}

	@ResponseBody
	@RequestMapping(value = "/country/{countryId}/states", method = RequestMethod.GET)
	public Object fetchAllStatesByCountry(@PathVariable Long countryId,
	        HttpServletResponse response) {
		Object data = null;
		try {
			data = locationService.getAllStatesByCountry(countryId);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;

	}

	@ResponseBody
	@RequestMapping(value = "/states/{stateId}/cities", method = RequestMethod.GET)
	public Object fetchAllCitiesBySate(@PathVariable Long stateId, HttpServletResponse response) {
		Object data = null;
		try {
			data = locationService.getAllCitiesByStates(stateId);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;

	}

	@ResponseBody
	@RequestMapping(value = "/{countryId}/states/{stateId}/cities", method = RequestMethod.GET)
	public Object fetchAllCitiesStatesByCountry(@PathVariable Long countryId,
	        @PathVariable Long stateId, HttpServletResponse response) {
		Object data = null;
		try {
			data = locationService.getAllCountriesStatesByCountry(countryId, stateId);

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
