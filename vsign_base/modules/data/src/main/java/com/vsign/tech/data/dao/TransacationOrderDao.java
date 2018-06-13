/**
 * 
 */
package com.vsign.tech.data.dao;

import com.vsign.tech.data.dao.entity.TransacationOrder;
import com.vsign.tech.data.dto.GenericDTO;

/**
 * @author Hemraj
 *
 */
public interface TransacationOrderDao extends GenericDao<TransacationOrder, Long> {

	GenericDTO fetchAllTrxOrders(int pageNum, int count, String sortField,
	        String order);

	GenericDTO fetchAllTrxOrdersByStatus(int pageNum, int count, String sortField, String order,
	        String status);

}
