/**
 * 
 */
package com.vsign.tech.rest.service;

import java.util.Map;

import com.vsign.tech.data.exception.InstanceNotFoundException;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.EmptyListException;
import com.vsign.tech.rest.exception.InvalidTransactionException;
import com.vsign.tech.rest.exception.OrderStatusException;
import com.vsign.tech.rest.exception.UserNotFoundException;
import com.vsign.tech.rest.form.TransacationOrderForm;
import com.vsign.tech.rest.form.TransactionResponseForm;

/**
 * @author Hemraj
 *
 */
public interface PaymentService {

	Map<String ,Object> initiateTransaction(Long orderId) throws DatabaseException,UserNotFoundException,OrderStatusException,InstanceNotFoundException ;

	Long transactionComplete(TransactionResponseForm completTrxForm) throws DatabaseException, InvalidTransactionException, InstanceNotFoundException;

	Map<String, Object> initiateCartTransaction(TransacationOrderForm form) throws DatabaseException,UserNotFoundException,OrderStatusException,InstanceNotFoundException, EmptyListException ;




}
