/**
 * 
 */
package com.vsign.tech.rest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.vsign.tech.data.dao.TransacationOrderDao;
import com.vsign.tech.data.dao.entity.CustOrder;
import com.vsign.tech.data.dao.entity.TransacationOrder;
import com.vsign.tech.data.dto.GenericDTO;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.DatabaseException;
import com.vsign.tech.rest.exception.EmptyListException;
import com.vsign.tech.rest.exception.VaidationException;

/**
 * @author Hemraj
 *
 */
@Service
public class TransactionOrderServiceImpl implements TransactionOrderService {

	private Logger					LOGGER	= LoggerFactory
	        .getLogger(TransactionOrderServiceImpl.class);

	@Autowired
	private TransacationOrderDao	trxOrdDao;

	@Override
	@Transactional
	public GenericDTO fetchAllTrxOrders(int pageNum, int count, String sortField,
	        String order) throws VaidationException, DatabaseException, EmptyListException {

		if (pageNum == 0 || count == 0) {
			throw new VaidationException("Invalid PageNumber or Job Count",
			        ErrorCodes.INVALID_PAGENUM_OR_COUNT);
		}
		List<TransacationOrder> trxList = null;
		
		GenericDTO result=null;
		try {

			result = trxOrdDao.fetchAllTrxOrders(pageNum, count, sortField, order);
		} catch (Exception e) {
			LOGGER.error("Error occured while getting transaction order list through database", e);
			throw new DatabaseException("Error occured while getting transaction order  list through database",
			        ErrorCodes.DATABASE_ERROR);
		}
		
		if (CollectionUtils.isEmpty(result.getResult())) {
			throw new EmptyListException("Transaction CustOrder List is empty", ErrorCodes.TRANSACTION_ORDER_LIST_EMPTY);
		}

		LOGGER.info("TransactionOrderServiceImpl.fetchAllTrxOrders <<");
		return result;
	}

	@Override
	@Transactional
	public GenericDTO fetchAllTrxOrdersByStatus(int pageNum, int count, String sortField, String order,String status)
	        throws VaidationException, DatabaseException, EmptyListException {

		if (pageNum == 0 || count == 0) {
			throw new VaidationException("Invalid PageNumber or Job Count",
			        ErrorCodes.INVALID_PAGENUM_OR_COUNT);
		}
		List<TransacationOrder> trxList = null;
		
		GenericDTO result=null;
		try {

			result = trxOrdDao.fetchAllTrxOrdersByStatus(pageNum, count, sortField, order,status);
		} catch (Exception e) {
			LOGGER.error("Error occured while getting  CustOrder list through database", e);
			throw new DatabaseException("Error occured while getting  CustOrder  list through database",
			        ErrorCodes.DATABASE_ERROR);
		}
		
		if (CollectionUtils.isEmpty(result.getResult())) {
			throw new EmptyListException(" CustOrder List is empty", ErrorCodes.TRANSACTION_ORDER_LIST_EMPTY);
		}

		LOGGER.info("OrderServiceImpl.fetchAllOrders <<");
		return result;
	}

	@Override
	@Transactional
	public GenericDTO fetchAllOrdersByStatus(int pageNum, int count, String sortField, String order,
	        String status) throws VaidationException, DatabaseException, EmptyListException {
	
		

			if (pageNum == 0 || count == 0) {
				throw new VaidationException("Invalid PageNumber or Job Count",
				        ErrorCodes.INVALID_PAGENUM_OR_COUNT);
			}
			List<CustOrder> ordList = null;
			
			GenericDTO result=null;
			try {

				result = trxOrdDao.fetchAllTrxOrdersByStatus(pageNum, count, sortField, order,status);
			} catch (Exception e) {
				LOGGER.error("Error occured while getting  CustOrder list through database", e);
				throw new DatabaseException("Error occured while getting  CustOrder  list through database",
				        ErrorCodes.DATABASE_ERROR);
			}
			
			if (CollectionUtils.isEmpty(result.getResult())) {
				throw new EmptyListException(" CustOrder List is empty", ErrorCodes.TRANSACTION_ORDER_LIST_EMPTY);
			}

			LOGGER.info("OrderServiceImpl.fetchAllOrders <<");
			return result;
		
	}

}
