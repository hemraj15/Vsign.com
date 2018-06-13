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
import com.vsign.tech.data.dto.GenericDTO;
import com.vsign.tech.data.dto.OrderDto;

/**
 * @author Hemraj
 *
 */
@Repository
public class OrderDaoImpl extends GenericDaoImpl<CustOrder, Long> implements OrderDao{

	private Logger				LOGGER	= LoggerFactory.getLogger(OrderDaoImpl.class);
	@Override
	public List<CustOrder> fetchAllOrdersByCustomerId(Long customerId) {
	
		List<OrderDto> dtos=null;
		List<CustOrder> custOrders=null;
		
	/*	String orderQuerry="SELECT ord.id AS orderId,ord.status AS orderStatus,ord.price AS orderValue,ord.customer_id AS customerId ,"
		+"prd.id AS productId,prd.name AS productName "+
				" FROM cust_order ord,products prd where  ord.customer_id=?";
				//+  "SELECT ord.id,ord.status,ord.price,ord.customer_id AS customerId ,prd.id,prd.name "+
		//" FROM CustOrder ord,Product prd where ord.customer_id=?";
		
		dtos=getSession().createSQLQuery(orderQuerry).setParameter(0, customerId).setResultTransformer(Transformers.aliasToBean(OrderDto.class)).list();
		*/
		/*Criteria crit=getSession().createCriteria(CustOrder.class,"ord").createAlias("products","product").createAlias("customer","cust");
		
		crit.add(Restrictions.eq("cust.id", customerId));
		 crit.setProjection(Projections.projectionList().add(Projections.property("ord.id"),"id")
		.add(Projections.property("ord.dateCreated"),"dateCreated")
		 .add(Projections.property("ord.dateUpdated"),"dateUpdated")
		 .add(Projections.property("ord.lastModifiedBy"),"lastModified")
		 .add(Projections.property("ord.status"),"status")
		 .add(Projections.property("ord.orderPrice"),"orderPrice")
		 .add(Projections.property("product.id"),"id")
		 .add(Projections.property("product.name"),"name"))
		 .add(Restrictions.eq("cust.id", customerId))
	     .setResultTransformer(Transformers.aliasToBean(OrderDto.class));// here is the priblem
		 */
		
		Criteria crit=getCriteria().add(Restrictions.eq("customer.id", customerId));
				     /* .setProjection(Projections.projectionList().setResultTransformer(Transformers.aliasToBean(OrderDto.class));
				      .add(Projections.property("id"),"id")
				      .add(Projections.property("orderPrice"),"orderPrice")
				      .add(Projections.property("dateCreated"),"dateCreated")
				      .add(Projections.property("dateUpdated"),"dateUpdated")
				      .add(Projections.property("status"),"status"))
				      .setResultTransformer(Transformers.aliasToBean(OrderDto.class));*/
		         
		 
		// System.out.println("order dto list for customer ----- > "+dtos.size());
		custOrders=crit.list();
		
		System.out.println("order dto list for customer "+customerId);
		return custOrders;
	}

	@Override
	public List<CustOrder> fetchAllActiveOrdersByCustomerId(Long customerId,String status) {
		
		List<CustOrder> custOrders=null;
		Criteria crit=getCriteria().add(Restrictions.eq("customer.id", customerId)).add(Restrictions.eq("status", status));
		
           custOrders=crit.list();
		
		System.out.println("order dto list for customer "+customerId);
		return custOrders;
	}

	@Override
	public GenericDTO fetchAllOrders(int pageNum, int count, String sortField, String order) {
		List<CustOrder> ordList = null;
		LOGGER.info(">> OrderDaoImpl.fetchAllTrxOrders");

		GenericDTO result = new GenericDTO();

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
		Criteria crit = getCriteria().setFirstResult((pageNum - 1) * count);
		crit.setMaxResults(count);
		crit.addOrder(Order.desc(sortField));
		
		ordList = crit.list();
		result.setResult(ordList);
		result.setTotalCount(totalOrderCount.intValue());

		return result;
	}

	@Override
	public GenericDTO fetchAllOrdersByStatus(int pageNum, int count, String sortField, String order,
	        String status) {
		LOGGER.info(">> TransacationOrderDaoImpl.fetchAllTrxOrders");

		GenericDTO result = new GenericDTO();
		List<CustOrder> ordList = null;
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
