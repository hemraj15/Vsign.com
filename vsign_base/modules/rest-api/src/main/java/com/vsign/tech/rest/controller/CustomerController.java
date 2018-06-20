package com.vsign.tech.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vsign.tech.auth.service.SystemRoles;
import com.vsign.tech.data.dao.entity.Customer;
import com.vsign.tech.data.dao.entity.User;
import com.vsign.tech.data.exception.InstanceNotFoundException;
import com.vsign.tech.rest.constant.CommonStatus;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.FileDownloadException;
import com.vsign.tech.rest.exception.FileUploadException;
import com.vsign.tech.rest.exception.InvalidPlanException;
import com.vsign.tech.rest.exception.InvalidProductException;
import com.vsign.tech.rest.exception.MailNotSendException;
import com.vsign.tech.rest.exception.OrderInitiateException;
import com.vsign.tech.rest.exception.StatusException;
import com.vsign.tech.rest.exception.UserNotFoundException;
import com.vsign.tech.rest.form.GuestForm;
import com.vsign.tech.rest.form.InitiateOrderForm;
import com.vsign.tech.rest.model.ErrorResponse;
import com.vsign.tech.rest.service.CustomerService;
import com.vsign.tech.rest.service.VsignService;
import com.vsign.tech.rest.utils.ErrorUtils;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	private Logger				LOGGER	= LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private VsignService	vsignService;

	// @Secured(value = { SystemRoles.ADMIN})
	@RequestMapping(value = "/recent", method = RequestMethod.GET)
	public Object fetchAllCustomerByModifyDate(
	        @RequestParam(value = "records", required = true) Integer records,
	        HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info(">> fetchAllCustomerByModifyDate");
		Object data = null;
		try {
		
			data = customerService.fetchAllCustomerByModifyDate(records);
		} catch (DatabaseException e) {
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return data;
	}

	@Secured(value = { SystemRoles.ADMIN, SystemRoles.CUSTOMER })
	@RequestMapping(value = "/my-orders", method = RequestMethod.GET)
	public Object fetchAllOrdersByCustomerId(HttpServletResponse response) {
		LOGGER.info(">> fetchAllOrdersByCustomerId");

		Object data = null;
		User user = null;
		try {
			user = customerService.getLoggedinUser();
			LOGGER.info(">> fetchAllOrdersByCustomerId for customerId " + user.getId());
			data = customerService.fetchAllOrdersByCustomerId(user.getEmailId().trim());
		} catch (DatabaseException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
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

	@Secured(value = { SystemRoles.ADMIN, SystemRoles.CUSTOMER })
	@RequestMapping(value = "/my-active-orders", method = RequestMethod.GET)
	public Object fetchAllActiveOrdersByCustomerId(HttpServletResponse response) {
		LOGGER.info(">> fetchAllOrdersByCustomerId");

		Object data = null;
		User user = null;
		try {
			user = customerService.getLoggedinUser();

			LOGGER.info(">> fetchAllOrdersByCustomerId for customerId " + user.getId());
			data = customerService.fetchAllActiveOrdersByCustomerId(user.getEmailId(),
			        CommonStatus.ACTIVE.toString());
		} catch (DatabaseException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
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

	@Secured(value = { SystemRoles.CUSTOMER })
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public Object fetchLoggedinUser(HttpServletResponse response) {
		LOGGER.info(">> fetchLoggedinUser");

		Object data = null;
		User user = null;
		try {
			LOGGER.info("fetchOrders <<");

			LOGGER.info(">> fetchLoggedinUser for my profile ");
			// data = customerService.fetchAllOrdersByCustomerId();
			String custEmail = customerService.fetchLoggedinCustomer();
			data = customerService.fetchCustomerByEmail(custEmail.trim());

		} catch (DatabaseException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} catch (InstanceNotFoundException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.USER_NOT_FOUND_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);

		} catch (StatusException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.VALIDATION_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

		catch (UserNotFoundException e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.USER_NOT_FOUND_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);

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
	@Secured(value = { SystemRoles.CUSTOMER })
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public Object getLoggedinUser(HttpServletResponse response) {
		LOGGER.info(">> getLoggedinUser");

		Object data = null;
		User user = null;
		try {
			LOGGER.info("fetchOrders <<");
			// data = customerService.fetchAllOrdersByCustomerId(customerId);

			// data=(User) AuthorizationUtil.getLoggedInUser();
			user = customerService.getLoggedinUser();
			System.out.println("");
		}

		catch (Exception e) {
			data = new ErrorResponse();
			LOGGER.error(e.getMessage(), e);
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return user.getEmailId();
	}

	@ResponseBody
	@RequestMapping(value = "/college-order-upload-files", method = RequestMethod.POST, consumes = "multipart/form-data")
	public Object uploadProjectFiles(@RequestParam String fileType,
	        @RequestParam String bindingType, @RequestParam Integer totalPages,   
	        @RequestParam String anyOtherRequest, @RequestParam MultipartFile file,
	        @RequestParam Integer totalColorPage,@RequestParam Integer quantity,@RequestParam String colorPages,
	        HttpServletRequest request, HttpServletResponse response) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		Map<String, Object> map = new HashMap<>();
		Long fileId = null;

		Object data = null;
		if (!isMultipart || fileType == null) {
			data = new ErrorResponse();
			((ErrorResponse) data)
			        .setErrorCode(ErrorCodes.COLLEGE_SECTION_INITIATE_ORDER_NOT_MULTIPART);
			((ErrorResponse) data).setMessage("Form validation failed!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			try {
				
				Integer glossyColorPages=2;
			    Integer nonGlossyColorPages=2;

				fileId = vsignService.uploadFile(fileType, file);
				LOGGER.info("file saved successfully with file id ::" + fileId);
				if(fileId !=null){
					
					LOGGER.info("Placing order >>");
					map = customerService.placeCollegeOrder(glossyColorPages, nonGlossyColorPages,
					        anyOtherRequest, totalPages, bindingType, fileId,totalColorPage,quantity,colorPages);
					map.put("fileId", fileId);
					map.put("message",
					        "order has been initiated succssfully please make payment to confirm order");
					data = map;
				}
				else{
					
					data = new ErrorResponse();
					LOGGER.error("Could not uploaded File ");
					((ErrorResponse) data).setErrorCode(ErrorCodes.FILE_ID_NULL_ERROR);
					((ErrorResponse) data).setMessage("File uploaded returned Null File Id");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					
				}
				
				
				LOGGER.info("order initiated");
			} catch (FileUploadException e) {
				data = new ErrorResponse();
				((ErrorResponse) data).setErrorCode(e.getErrorCode());
				((ErrorResponse) data).setMessage(e.getMessage());
				switch (e.getErrorCode()) {
				case ErrorCodes.CUSTOMER_NOT_FOUND_ERROR:
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					break;
				default:
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					break;
				}
			} catch (UserNotFoundException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.USER_NOT_FOUND_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);

			} catch (InvalidProductException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.INVALID_PRODUCT_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} catch (MailNotSendException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.MAIL_NOT_SENT_ERROR);
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
	@RequestMapping(value = "/initiateOrder", method = RequestMethod.POST, consumes = "application/json")
	public Object initiateOrder(@RequestBody @Valid InitiateOrderForm form ,BindingResult result ,  	       
	        HttpServletRequest request, HttpServletResponse response) {
		//boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		Map<String, Object> map = new HashMap<>();
		Long orderId;
		Long plansId = new Long(form.getPlanId());

		Object data = null;
		if (result.hasErrors()) {
			String message = ErrorUtils.getTextValidationErrorMessage(result.getAllErrors());
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(message);
			((ErrorResponse) data).setMessage("Form validation failed!");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		    } else {
			try {
				LOGGER.info("Placing order >>");
				LOGGER.info("Initiating order for plan name :: : plan id :: "+plansId);
				map = vsignService.initiateOrder(form.getMachineId(),plansId);
				LOGGER.info("order initiated  successfully with planId id ::" + plansId);
				//if(orderId !=null){
					
				
					/*map = customerService.placeCollegeOrder(glossyColorPages, nonGlossyColorPages,
					        anyOtherRequest, totalPages, bindingType, fileId,totalColorPage,quantity,colorPages);*/
					//map.put("fileId", orderId);
					map.put("message",
					        "order has been initiated succssfully please make payment to confirm order");
					data = map;
				//}
				/*else{
					
					data = new ErrorResponse();
					LOGGER.error("Could not uploaded File ");
					((ErrorResponse) data).setErrorCode(ErrorCodes.FILE_ID_NULL_ERROR);
					((ErrorResponse) data).setMessage("File uploaded returned Null File Id");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					
				}*/
				
				
				LOGGER.info("order initiated");
			} /*catch (FileUploadException e) {
				data = new ErrorResponse();
				((ErrorResponse) data).setErrorCode(e.getErrorCode());
				((ErrorResponse) data).setMessage(e.getMessage());
				switch (e.getErrorCode()) {
				case ErrorCodes.CUSTOMER_NOT_FOUND_ERROR:
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					break;
				default:
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					break;
				}
			} */ catch (UserNotFoundException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.USER_NOT_FOUND_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);

			} catch (InvalidPlanException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.INVALID_PLAN_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} catch (OrderInitiateException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.ORDER_INITIATE_ERROR);
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
	@RequestMapping(value = "/confirm-order/{orderId}", method = RequestMethod.GET)
	public Object confirmOrder(@PathVariable Long orderId, HttpServletRequest request,
	        HttpServletResponse response) {

		Map<String, Object> map = new HashMap<>();

		Object data = null;

		try {
			LOGGER.info("order id to comfirm ::" + orderId);
			LOGGER.info("Placing order >>");
			// customerService.confirmOrder(orderId);

			map.put("orderId", orderId);
			map.put("message", "order has been confirmed succssfully !!");
			data = map;
			LOGGER.info("order confirmed");
		}

		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/update-order-status/{orderId}/{ordStatus}", method = RequestMethod.GET)
	public Object changeOrderStatus(@PathVariable Long orderId, @PathVariable String ordStatus,
	        HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<>();

		Object data = null;

		try {
			LOGGER.info("order id to comfirm ::" + orderId);
			LOGGER.info("Placing order >>");
			customerService.changeOrderStatus(ordStatus, orderId);

			map.put("orderId", orderId);
			map.put("message", "order has been updated to " + ordStatus + " succssfully !!");
			data = map;
			LOGGER.info("order tatus changed");
		}

		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		return data;
	}

	@ResponseBody
	@RequestMapping(value = "/download-files/{order_id}", method = RequestMethod.GET)
	public Object downloadCollegeProjectFiles(@PathVariable Long order_id,
	        HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<>();
		Long fileId = null;

		Object data = null;

		try {

			// fileId=vsignService.uploadFile( fileType, file);
			LOGGER.info("file ownload for order id  ::" + order_id);
			LOGGER.info("Placing order >>");
			map = vsignService.downloadCollegeProjectFiles(order_id);
			
			//map.put("fileId", fileId);
			map.put("message","file to download ");
			data = map;
			LOGGER.info("file download section");
		} catch (FileDownloadException e) {
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(e.getErrorCode());
			((ErrorResponse) data).setMessage(e.getMessage());
			switch (e.getErrorCode()) {
			case ErrorCodes.CUSTOMER_NOT_FOUND_ERROR:
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				break;
			default:
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				break;
			}
		} /*
		   * catch (UserNotFoundException e) { data = new ErrorResponse();
		   * LOGGER.error(e.getMessage(), e); ((ErrorResponse)
		   * data).setErrorCode(ErrorCodes.USER_NOT_FOUND_ERROR); ((ErrorResponse)
		   * data).setMessage(e.getMessage()); response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		   * 
		   * }
		   */

		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			data = new ErrorResponse();
			((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
			((ErrorResponse) data).setMessage(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		return data;
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/collegeSec-initiate-order", method = RequestMethod.POST) public
	 * Object collegeSectionInitiateOrder(@RequestBody @Valid CollegeSectionOrderForm
	 * collegeSectionOrderForm ,BindingResult result, HttpServletRequest request,
	 * HttpServletResponse response) { boolean isMultipart =
	 * ServletFileUpload.isMultipartContent(request); Map<String ,Object> map=new HashMap<>(); Long
	 * fileId=null; Long orderId=null;
	 * 
	 * Object data = null; if (!isMultipart || fileType == null) { data = new ErrorResponse();
	 * ((ErrorResponse) data).setErrorCode(ErrorCodes.SIGNUP_REQUEST_NOT_MULTIPART);
	 * ((ErrorResponse) data).setMessage("Form validation failed!");
	 * response.setStatus(HttpServletResponse.SC_BAD_REQUEST); }
	 * 
	 * if (result.hasErrors()) { String message =
	 * ErrorUtils.getTextValidationErrorMessage(result.getAllErrors()); data = new ErrorResponse();
	 * ((ErrorResponse) data).setErrorCode(message); ((ErrorResponse) data).setMessage(
	 * "Form validation failed!"); response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	 * 
	 * }
	 * 
	 * else { try {
	 * 
	 * fileId=vsignService.uploadFile( collegeSectionOrderForm.getFieType(),
	 * collegeSectionOrderForm.getFile()); orderId=customerService.placeOrder(); map.put("fileId",
	 * fileId); map.put("orderId", orderId);
	 * 
	 * } catch (FileUploadException e) { data = new ErrorResponse(); ((ErrorResponse)
	 * data).setErrorCode(e.getErrorCode()); ((ErrorResponse) data).setMessage(e.getMessage());
	 * switch (e.getErrorCode()) { case ErrorCodes.SIGNUP_COMPANY_NOT_FOUND:
	 * response.setStatus(HttpServletResponse.SC_NOT_FOUND); break; default:
	 * response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); break; } } catch (Exception
	 * e) { LOGGER.error(e.getMessage(), e); data = new ErrorResponse(); ((ErrorResponse)
	 * data).setErrorCode(ErrorCodes.SERVER_ERROR); ((ErrorResponse)
	 * data).setMessage(e.getMessage());
	 * response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); } }RPaymetC return data; }
	 */
	 
	 @ResponseBody
		@RequestMapping(value = "/update-trxOrder-status/{trxOrderId}/{ordStatus}", method = RequestMethod.GET)
		public Object changeTrxOrderStatus(@PathVariable Long trxOrderId, @PathVariable String ordStatus,
		        HttpServletRequest request, HttpServletResponse response) {

			Map<String, Object> map = new HashMap<>();

			Object data = null;

			try {
				LOGGER.info("order id to comfirm ::" + trxOrderId);
				LOGGER.info("Placing order >>");
				customerService.changetrxOrderStatus(ordStatus, trxOrderId);

				map.put("orderId", trxOrderId);
				map.put("message", "order has been updated to " + ordStatus + " succssfully !!");
				data = map;
				LOGGER.info("order status changed");
			}

			catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				data = new ErrorResponse();
				((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}

			return data;
		}
	 
	    @ResponseBody
	    //@Secured
	    @RequestMapping(value = "/{customerId}/update-status/{status}", method = RequestMethod.GET)
		public Object updateCustomerStatus(@PathVariable String status,	@PathVariable Long customerId ,	        
		        HttpServletRequest request, HttpServletResponse response) {
			LOGGER.info(">> fetchAllCustomerByModifyDate");
			Object data = null;
			Map<String,Object> map=new HashMap<>();
			try {
			
				 customerService.updateCustomerStatus(status,customerId);
				 map.put("message", "Costomer "+customerId +" status changed to "+status);
				 data=map;
			} catch (InstanceNotFoundException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.USER_NOT_FOUND_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);

			}catch (MailNotSendException e) {
				data = new ErrorResponse();
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.MAIL_NOT_SENT_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}catch (DatabaseException e) {
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.DATABASE_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}

			catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
				((ErrorResponse) data).setMessage(e.getMessage());
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
			return data;
		}
	    @ResponseBody
		@RequestMapping(value = "/purchase", method = RequestMethod.POST, consumes = "application/json")
		public Object purchaseLicense(@RequestBody @Valid GuestForm form ,BindingResult result ,
		     HttpServletRequest request, HttpServletResponse response) {
			
			Object data = null;
			Map<String, Object> map = new HashMap<>();
		/*	if (machineId == null || machineId.isEmpty()) {
				data = new ErrorResponse();
				((ErrorResponse) data)
				        .setErrorCode(ErrorCodes.MACHINE_ID_EMPTY_OR_NULL);
				((ErrorResponse) data).setMessage("Form validation failed!");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			} */
			if (result.hasErrors()) { String message =
					  ErrorUtils.getTextValidationErrorMessage(result.getAllErrors());
			          data = new ErrorResponse();
					  ((ErrorResponse) data).setErrorCode(message); ((ErrorResponse) data).setMessage(
					  "Form validation failed!"); response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					  
					  }
			
			else {
				try {
						LOGGER.info("Placing order >>");
						map = customerService.placePurchaseLicenceOrder(form);
						map.put("message",
						        "order has been initiated succssfully please make payment to confirm order");
						data = map;
					
										
					LOGGER.info("order initiated");
				
				} /*catch (UserNotFoundException e) {
					data = new ErrorResponse();
					LOGGER.error(e.getMessage(), e);
					((ErrorResponse) data).setErrorCode(ErrorCodes.USER_NOT_FOUND_ERROR);
					((ErrorResponse) data).setMessage(e.getMessage());
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);

				} catch (InvalidProductException e) {
					data = new ErrorResponse();
					LOGGER.error(e.getMessage(), e);
					((ErrorResponse) data).setErrorCode(ErrorCodes.INVALID_PRODUCT_ERROR);
					((ErrorResponse) data).setMessage(e.getMessage());
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				} catch (MailNotSendException e) {
					data = new ErrorResponse();
					LOGGER.error(e.getMessage(), e);
					((ErrorResponse) data).setErrorCode(ErrorCodes.MAIL_NOT_SENT_ERROR);
					((ErrorResponse) data).setMessage(e.getMessage());
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				} */catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
					data = new ErrorResponse();
					((ErrorResponse) data).setErrorCode(ErrorCodes.SERVER_ERROR);
					((ErrorResponse) data).setMessage(e.getMessage());
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}
			return data;
		}

}
