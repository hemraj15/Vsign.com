package com.vsign.tech.rest.service;

import java.util.List;
import java.util.Map;

import com.vsign.tech.data.dao.entity.User;
import com.vsign.tech.data.dto.CustomerDto;
import com.vsign.tech.data.exception.InstanceNotFoundException;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.InvalidNumberOfPagesException;
import com.vsign.tech.rest.exception.InvalidProductException;
import com.vsign.tech.rest.exception.InvalidQuantiryException;
import com.vsign.tech.rest.exception.MailNotSendException;
import com.vsign.tech.rest.exception.StatusException;
import com.vsign.tech.rest.exception.UserNotFoundException;
import com.vsign.tech.rest.form.GuestForm;

public interface CustomerService {

	List<CustomerDto> fetchAllCandidatesByModifiedDate(Integer records) throws DatabaseException;

	Object fetchAllOrdersByCustomerId(String mailId)throws DatabaseException;

	Object fetchAllCustomerByModifyDate(Integer records)throws DatabaseException;

	String fetchLoggedinCustomer() throws DatabaseException, UserNotFoundException;

	Object fetchCustomerByEmail(String email)throws DatabaseException, UserNotFoundException, InstanceNotFoundException, StatusException;

	User getLoggedinUser();

	Object fetchAllActiveOrdersByCustomerId(String string2, String string)throws DatabaseException;

	Map<String,Object> placeCollegeOrder(Integer glossyColorPages, Integer nonGlossyColorPages, String anyOtherRequest, Integer totalPages, String bindingType,Long fileId, Integer totalColorPage, Integer quantity, String colorPages)throws DatabaseException,InvalidProductException, MailNotSendException, InvalidNumberOfPagesException, InvalidQuantiryException;

	String confirmOrder(Long orderId, String string)throws DatabaseException;

	void changeOrderStatus(String status, Long orderId) throws DatabaseException;

	void changetrxOrderStatus(String ordStatus, Long trxOrderId)throws DatabaseException;

	void updateCustomerStatus(String status, Long customerId)throws DatabaseException, InstanceNotFoundException ,MailNotSendException;

	Map<String, Object> placePurchaseLicenceOrder(GuestForm form) throws DatabaseException;
	
	Object getDummyLicence();

}
