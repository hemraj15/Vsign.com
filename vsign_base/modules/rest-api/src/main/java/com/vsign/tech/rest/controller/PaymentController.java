
package com.vsign.tech.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

import com.vsign.tech.data.dto.PaymentRequestDto;
import com.vsign.tech.data.exception.InstanceNotFoundException;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.InvalidTransactionException;
import com.vsign.tech.rest.exception.OrderStatusException;
import com.vsign.tech.rest.form.PaymentResponseForm;
import com.vsign.tech.rest.model.ErrorResponse;
import com.vsign.tech.rest.service.CustomerService;
import com.vsign.tech.rest.service.PaymentService;
import com.vsign.tech.rest.utils.ErrorUtils;

/**
 * @author Hemraj
 *
 */
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private CustomerService custService;

	/**
	 * Initiate transaction for customer order id
	 * 
	 * @param custOrderId
	 * 
	 **/
	@ResponseBody
	@RequestMapping(value = "/trxInitiate", method = RequestMethod.GET)
	public Object initiateTransaction(@RequestParam("orderId") long orderId, HttpServletResponse response) {
		PaymentRequestDto dto = null;
		Object data = null;
		Long id = orderId;
		String paymentResponse ="";
		try {

			dto = paymentService.initiateTransaction(id);

			// map.put("test", "test");
			// data=map;
			paymentResponse = paymentService.processPayment(dto);
			data = paymentResponse;
			LOGGER.info("Congratulations gateway call is success full ");
		}

		catch (DatabaseException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (InstanceNotFoundException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.ORDER_NOT_FOUND_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} catch (OrderStatusException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.ORDER_STATUS_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		} catch (Exception e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(ErrorCodes.VALIDATION_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;

	}

	@ResponseBody
	@RequestMapping(value = "/trxComplete", method = RequestMethod.POST, consumes = "application/json")
	public Object transactionComplete(@RequestBody PaymentResponseForm paymentRespForm, BindingResult result,
			HttpServletResponse response) {

		Object data = null;
		Long trxOrderId = null;
		if (result.hasErrors()) {
			String message = ErrorUtils.getTextValidationErrorMessage(result.getAllErrors());
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(message);
			((ErrorResponse) data).setMessage("Form validation failed!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {

			try {

				Map<String, Object> map = new HashMap<>();

				map = paymentService.transactionComplete(paymentRespForm);

				map.put("transactioMessage",
						"payment successfull - transaction completed for transaction order id :" + trxOrderId);
				trxOrderId = (Long) map.get("transactioOrderId");
				LOGGER.info("transaction order id to confirm ::" + trxOrderId);
				LOGGER.info("Placing order >>");

				String licenseKey = custService.confirmOrder(trxOrderId, paymentRespForm.getF_code());
				map.put("licence key : ", licenseKey);

				map.put("message : ", "order has been completed succssfully for transaction order id :" + trxOrderId);
				data = map;

			} catch (InstanceNotFoundException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.INVALID_TRANSACTION_NUMBER_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} catch (InvalidTransactionException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.INVALID_TRANSACTION_NUMBER_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} catch (DatabaseException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (Exception e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				data = new ErrorResponse();
				((ErrorResponse) data).setErrorCode(ErrorCodes.VALIDATION_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
		return data;

	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="/cartTrxInitiate" ,method =
	 * RequestMethod.POST,consumes="application/json") public Object
	 * initiateTransaction(@RequestBody TransacationOrderForm form
	 * ,BindingResult result,HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * Object data=null; if (result.hasErrors()) { String message =
	 * ErrorUtils.getTextValidationErrorMessage(result.getAllErrors()); data =
	 * new ErrorResponse(); ((ErrorResponse) data).setErrorCode(message);
	 * ((ErrorResponse) data).setMessage("Form validation failed!");
	 * response.setStatus(HttpServletResponse.SC_BAD_REQUEST); }
	 * 
	 * else{
	 * 
	 * try {
	 * 
	 * LOGGER.info("cart transaction initiate list of orders "
	 * +form.getOrderIdList());
	 * 
	 * Map<String ,Object> map=new HashMap<>();
	 * 
	 * map=paymentService.initiateCartTransaction(form); //map.put("test",
	 * "test");
	 * 
	 * 
	 * PayUMoneyIntegrationUtils kit = new PayUMoneyIntegrationUtils();
	 * 
	 * request.setAttribute("txnid", map.get("tansactionId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * request.setAttribute("txnid", map.get("transactionOrderId"));
	 * 
	 * 
	 * data=map;
	 * 
	 * }
	 * 
	 * catch (DatabaseException e) { data = new ErrorResponse();
	 * LOGGER.error(e.getMessage(), e); ((ErrorResponse)
	 * data).setErrorCode(ErrorCodes.DATABASE_ERROR); ((ErrorResponse)
	 * data).setMessage(e.getMessage());
	 * response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); } catch
	 * (InstanceNotFoundException e) { data = new ErrorResponse();
	 * LOGGER.error(e.getMessage(), e); ((ErrorResponse)
	 * data).setErrorCode(ErrorCodes.ORDER_NOT_FOUND_ERROR); ((ErrorResponse)
	 * data).setMessage(e.getMessage());
	 * response.setStatus(HttpServletResponse.SC_NOT_FOUND); } catch
	 * (OrderStatusException e) { data = new ErrorResponse();
	 * LOGGER.error(e.getMessage(), e); ((ErrorResponse)
	 * data).setErrorCode(ErrorCodes.ORDER_STATUS_ERROR); ((ErrorResponse)
	 * data).setMessage(e.getMessage());
	 * response.setStatus(HttpServletResponse.SC_CONFLICT); } catch (Exception
	 * e) { data = new ErrorResponse(); LOGGER.error(e.getMessage(), e); data =
	 * new ErrorResponse(); ((ErrorResponse)
	 * data).setErrorCode(ErrorCodes.VALIDATION_ERROR); ((ErrorResponse)
	 * data).setMessage(e.getMessage() );
	 * response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); } }
	 * return data;
	 * 
	 * 
	 * }
	 */
	@ResponseBody
	@RequestMapping(value = "/getDummyLicense", method = RequestMethod.GET)
	public Object getDummyLicence(HttpServletResponse response) {

		Object data = null;

		try {

			LOGGER.info("getting dummy  ");

			data = custService.getDummyLicence();
			// map.put("test", "test");

		}

		catch (Exception e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(ErrorCodes.VALIDATION_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		return data;

	}

}
