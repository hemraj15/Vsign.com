/**
 * 
 */
package com.vsign.tech.rest.service;

import com.vsign.tech.data.dto.GenericDTO;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.EmptyListException;
import com.vsign.tech.rest.exception.VaidationException;

/**
 * @author Hemraj
 *
 */
public interface TransactionOrderService {

	GenericDTO fetchAllTrxOrders(int pageNum, int count, String sortField, String order) throws DatabaseException, VaidationException, EmptyListException;

	GenericDTO fetchAllTrxOrdersByStatus(int pageNum, int count, String sortField, String order,
	        String status) throws VaidationException, DatabaseException, EmptyListException;

	GenericDTO fetchAllOrdersByStatus(int pageNum, int count, String sortField, String order,
	        String status)throws VaidationException, DatabaseException, EmptyListException;


}
