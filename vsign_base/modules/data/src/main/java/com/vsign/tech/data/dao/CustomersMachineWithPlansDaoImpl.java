/**
 * 
 */
package com.vsign.tech.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vsign.tech.data.dao.entity.CustomersMachineWithPlans;
import com.vsign.tech.data.dto.DepartmentDto;

/**
 * @author Hemraj
 *
 */
@Repository
public class CustomersMachineWithPlansDaoImpl extends GenericDaoImpl<CustomersMachineWithPlans, Long> implements CustomersMachineWithPlansDao{
	private Logger LOGGER = LoggerFactory.getLogger(CustomersMachineWithPlansDaoImpl.class);

	@Override
	@Transactional
	public List<DepartmentDto> getDepartmentListByCompanyId(Long companyId) {
		List<DepartmentDto> deptDtos=null;
		Criteria criteria = getCriteria().add(Restrictions.eq("company.id", companyId))
		        .setProjection(Projections.projectionList().add(Projections.property("id"), "id")
		                .add(Projections.property("name"), "name"))
		        .setResultTransformer(Transformers.aliasToBean(DepartmentDto.class));
		deptDtos = criteria.list();
		LOGGER.debug("<< getDepartmentDto's" + deptDtos.toString());
		return deptDtos;
		
	}
}
