/**
 * 
 */
package com.vsign.tech.rest.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vsign.tech.auth.service.SystemRoles;
import com.vsign.tech.data.dao.entity.User;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.EmptyListException;
import com.vsign.tech.rest.exception.VaidationException;
import com.vsign.tech.rest.model.ErrorResponse;
import com.vsign.tech.rest.service.OrderService;
import com.vsign.tech.rest.service.TransactionOrderService;

/**
 * @author Hemraj
 *
 */

@RestController
@RequestMapping(value = "/custorder")
public class OrderController {
	private Logger					LOGGER	= LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService			ordService;

	@Autowired
	private TransactionOrderService	trxService;

	// @Secured(value = { SystemRoles.ADMIN})
	@ResponseBody
	@RequestMapping(value = "/all-trx-orders", method = RequestMethod.POST)
	public Object fetchAllTrxOrders(@RequestParam(value = "pageno", defaultValue = "1") int pageNum,
	        @RequestParam(value = "count", defaultValue = "10") int count,
	        @RequestParam(value = "sortfield", defaultValue = "dateUpdated") String sortField,
	        @RequestParam(value = "order", defaultValue = "desc") String order,
	        HttpServletResponse response) {
		LOGGER.info(">> fetchAllTrxOrders");

		Object data = null;

		try {

			LOGGER.info(">> fetchAllTrxOrders ");
			data = trxService.fetchAllTrxOrders(pageNum, count, sortField, order);
			LOGGER.info("loaded all trx ");
		} catch (EmptyListException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.TRANSACTION_ORDER_LIST_EMPTY);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		catch (DatabaseException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (VaidationException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.VALIDATION_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		catch (Exception e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/all-orders", method = RequestMethod.POST)
	public Object fetchAllOrders(@RequestParam(value = "pageno", defaultValue = "1") int pageNum,
	        @RequestParam(value = "count", defaultValue = "10") int count,
	        @RequestParam(value = "sortfield", defaultValue = "dateUpdated") String sortField,
	        @RequestParam(value = "order", defaultValue = "desc") String order,
	        HttpServletResponse response) {
		LOGGER.info(">> fetchAllOrders");

		Object data = null;

		try {

			LOGGER.info(">> fetchAllOrders ");
			data = ordService.fetchAllOrders(pageNum, count, sortField, order);
		} catch (EmptyListException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.TRANSACTION_ORDER_LIST_EMPTY);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		catch (DatabaseException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (VaidationException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.VALIDATION_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		catch (Exception e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/all-orders-by-status", method = RequestMethod.POST)
	public Object fetchAllOrdersByStatus(@RequestParam(value = "pageno", defaultValue = "1") int pageNum,
	        @RequestParam(value = "count", defaultValue = "10") int count,
	        @RequestParam(value = "sortfield", defaultValue = "dateUpdated") String sortField,
	        @RequestParam(value = "order", defaultValue = "desc") String order,String status,
	        HttpServletResponse response) {
		LOGGER.info(">> fetchAllOrdersByStatus");

		Object data = null;

		try {

			LOGGER.info(">> fetchAllOrdersByStatus ");
			data = ordService.fetchAllOrdersByStatus(pageNum, count, sortField, order,status);
		} catch (EmptyListException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.TRANSACTION_ORDER_LIST_EMPTY);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		catch (DatabaseException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (VaidationException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.VALIDATION_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		catch (Exception e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/all-trxOrders-by-status", method = RequestMethod.POST)
	public Object fetchAllTrxOrdersByStatus(@RequestParam(value = "pageno", defaultValue = "1") int pageNum,
	        @RequestParam(value = "count", defaultValue = "10") int count,
	        @RequestParam(value = "sortfield", defaultValue = "dateUpdated") String sortField,
	        @RequestParam(value = "order", defaultValue = "desc") String order,String status,
	        HttpServletResponse response) {
		LOGGER.info(">> fetchAllOrdersByStatus");

		Object data = null;

		try {

			LOGGER.info(">> fetchAllOrdersByStatus ");
			data = trxService.fetchAllOrdersByStatus(pageNum, count, sortField, order,status);
		} catch (EmptyListException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.TRANSACTION_ORDER_LIST_EMPTY);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		catch (DatabaseException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (VaidationException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.VALIDATION_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		catch (Exception e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;
	}

}
