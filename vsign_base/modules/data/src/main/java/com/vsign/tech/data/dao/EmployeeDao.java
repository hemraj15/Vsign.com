/**
 * 
 */
package com.vsign.tech.data.dao;

import org.hibernate.Criteria;

import com.vsign.tech.data.dao.entity.Employee;

/**
 * @author Hemraj
 *
 */
public interface EmployeeDao extends GenericDao<Employee, Long>{

	Criteria getFindByEmailCriteria(String email);

}
