
package com.vsign.tech.rest.service;

import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.EmptyListException;
import com.vsign.tech.rest.exception.VaidationException;

/**
 * @author Hemraj
 *
 */
public interface OrderService {

	Object fetchAllOrders(int pageNum, int count, String sortField, String order) throws VaidationException, DatabaseException, EmptyListException ;

	Object fetchAllOrdersByStatus(int pageNum, int count, String sortField, String order,
	        String status) throws VaidationException, DatabaseException, EmptyListException;

	
}
