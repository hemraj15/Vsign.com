/**
 * 
 */
package com.vsign.tech.data.dao;

import java.util.List;

import com.vsign.tech.data.dao.entity.CustomersMachineWithPlans;
import com.vsign.tech.data.dto.DepartmentDto;

/**
 * @author Hemraj
 *
 */
public interface CustomersMachineWithPlansDao extends GenericDao<CustomersMachineWithPlans, Long>{
	

	List<DepartmentDto> getDepartmentListByCompanyId(Long companyId);

}
