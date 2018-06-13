package com.vsign.tech.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vsign.tech.data.dao.entity.Customer;
import com.vsign.tech.data.dto.CustomerDto;

@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer, Long> implements CustomerDao {

	@Autowired
	private OrderDao orderDao;
	
	
	
	
	@Override
	public Criteria getFindByEmailCriteria(String email) {
		
		System.out.println("CustomerDaoImpl getFindByEmailCriteria");
		return getCriteria().add(Restrictions.eq("email", email));
	}
	

	@Override
	public CustomerDto fetchAllOrdersByCustomerId(Long customerId) {
	
		CustomerDto customerDtos = null;
		
		
		
		Criteria criteria =getSession().createCriteria(Customer.class);
		    criteria.createAlias("custOrders", "ord").createAlias("custOrders.products", "product").add(Restrictions.eq("id", customerId));
	
		    
		    criteria.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
			        .add(Projections.property("name"), "name")
			        .add(Projections.property("ord.id"), "id")
			        .add(Projections.property("ord.name"),"name")
			        .add(Projections.property("ord.dateCreated"),"dateCreated")
			        .add(Projections.property("ord.dateUpdated"),"dateUpdated")
			        .add(Projections.property("ord.lastModified"),"lastModified")
			        .add(Projections.property("ord.status"),"status")
			        .add(Projections.property("ord.orderPrice"),"orderPrice"));
			        
			        
			        /*.add(Projections.property("cat.categoryName"), "category")
			        .add(Projections.property("experience"), "experience")
			        .add(Projections.property("city"), "city")
			        .add(Projections.property("state"), "state")*/
				
			
		
		return null;
	}

	@Override
	public List<CustomerDto> fetchAllCandidatesByModifiedDate(Integer fromIndex, Integer toIndex, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerDto> fetchAllCustomerByModifyDate(int from, Integer toIndex, String status) {
		List<CustomerDto> customerDtos = null;
		Criteria criteria = getCriteria().add(Restrictions.eq("status", status))
		        .addOrder(Order.desc("dateUpdated")).setFirstResult(from)
		        .setMaxResults(toIndex)
		        .setProjection(
		                Projections.projectionList().add(Projections.property("id"), "customerId")
		                        .add(Projections.property("firstName"), "firstName")
		                        .add(Projections.property("email"), "email"))
		        .setResultTransformer(Transformers.aliasToBean(CustomerDto.class));
		customerDtos = criteria.list();

		return customerDtos;
	}

}
