/**
 * 
 */
package com.vsign.tech.data.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.vsign.tech.data.dao.entity.Employee;

/**
 * @author Hemraj
 *
 */
@Repository
public class EmployeeDaoImpl extends GenericDaoImpl<Employee, Long> implements EmployeeDao{
	@Override
	public Criteria getFindByEmailCriteria(String email) {
		return getCriteria().add(Restrictions.eq("email", email));
	}
}
