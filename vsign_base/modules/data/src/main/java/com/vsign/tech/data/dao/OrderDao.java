/**
 * 
 */
package com.vsign.tech.data.dao;

import java.util.List;

import com.vsign.tech.data.dao.entity.CustOrder;
import com.vsign.tech.data.dto.GenericDTO;
import com.vsign.tech.data.dto.OrderDto;

/**
 * @author Hemraj
 *
 */
public interface OrderDao extends GenericDao<CustOrder, Long> {

	List<CustOrder> fetchAllOrdersByCustomerId(Long customerId);

	List<CustOrder> fetchAllActiveOrdersByCustomerId(Long customerId , String status);

	GenericDTO fetchAllOrders(int pageNum, int count, String sortField, String order);

	GenericDTO fetchAllOrdersByStatus(int pageNum, int count, String sortField, String order,
	        String status);

}
