package com.vsign.tech.data.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.vsign.tech.data.dao.entity.Customer;
import com.vsign.tech.data.dto.CustomerDto;

public interface CustomerDao extends GenericDao<Customer, Long> {

	List<CustomerDto> fetchAllCandidatesByModifiedDate(Integer fromIndex, Integer toIndex,
	        String status);

	Criteria getFindByEmailCriteria(String email);

	CustomerDto fetchAllOrdersByCustomerId(Long customerId);

	List<CustomerDto> fetchAllCustomerByModifyDate(int from, Integer toIndex, String status);

}
