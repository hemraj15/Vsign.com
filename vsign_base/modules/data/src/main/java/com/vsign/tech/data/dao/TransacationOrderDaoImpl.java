/**
 * 
 */
package com.vsign.tech.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.vsign.tech.data.dao.entity.CustOrder;
import com.vsign.tech.data.dao.entity.TransacationOrder;
import com.vsign.tech.data.dto.GenericDTO;

/**
 * @author Hemraj
 *
 */
@Repository
public class TransacationOrderDaoImpl extends GenericDaoImpl<TransacationOrder, Long>
        implements TransacationOrderDao {
	private Logger LOGGER = LoggerFactory.getLogger(TransacationOrderDaoImpl.class);

	@Override
	public GenericDTO fetchAllTrxOrders(int pageNum, int count, String sortField, String order) {
		List<TransacationOrder> trxList = null;
		LOGGER.info(">> TransacationOrderDaoImpl.fetchAllTrxOrders");

		GenericDTO result = new GenericDTO();

		Long totalTrxOrderCount = (Long) getCriteria().setProjection(Projections.rowCount())
		        .uniqueResult();
		if (totalTrxOrderCount == 0) {
			return null;
		}

		int lastPage = totalTrxOrderCount % count == 0 ? (int) (totalTrxOrderCount / count)
		        : (int) ((totalTrxOrderCount / count) + 1);

		if (pageNum > lastPage) {
			return null;
		}
		Criteria crit = getCriteria().setFirstResult((pageNum - 1) * count);
		crit.setMaxResults(count);
		crit.addOrder(Order.desc(sortField));
		trxList = crit.list();
		result.setResult(trxList);
		result.setTotalCount(totalTrxOrderCount.intValue());

		return result;
	}

	@Override
	public GenericDTO fetchAllTrxOrdersByStatus(int pageNum, int count, String sortField, String order,
	        String status) {
		LOGGER.info(">> TransacationOrderDaoImpl.fetchAllTrxOrdersByStatus");

		GenericDTO result = new GenericDTO();
		List<TransacationOrder> ordList = null;
		Long totalOrderCount = (Long) getCriteria().setProjection(Projections.rowCount())
		        .uniqueResult();
		if (totalOrderCount == 0) {
			return null;
		}

		int lastPage = totalOrderCount % count == 0 ? (int) (totalOrderCount / count)
		        : (int) ((totalOrderCount / count) + 1);

		if (pageNum > lastPage) {
			return null;
		}
		Criteria crit = getCriteria().add(Restrictions.eq("status", status));
		crit.setFirstResult((pageNum - 1) * count);
		crit.setMaxResults(count);
		crit.addOrder(Order.desc(sortField));

		ordList = crit.list();
		result.setResult(ordList);
		result.setTotalCount(totalOrderCount.intValue());

		return result;
	}


}
