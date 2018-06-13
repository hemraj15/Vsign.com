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

import com.vsign.tech.data.dao.entity.Plans;
import com.vsign.tech.data.dao.entity.Product;
import com.vsign.tech.data.dto.PlansDto;

/**
 * @author Hemraj
 *
 */
@Repository
public class PlansDaoImpl extends GenericDaoImpl<Plans, Long> implements PlansDao {
	private Logger				LOGGER	= LoggerFactory.getLogger(this.getClass());
	@Override
	public List<PlansDto> fetchAllPlans(String status) {
		
		LOGGER.info("PlansDaoImpl fetchAllPlans <<<");
		List<PlansDto> prodDtos=null;
		LOGGER.info("PlansDaoImpl status : "+status);
	
		Criteria crit=getCriteria().add(Restrictions.eq("status", status))
				      .setProjection(Projections.projectionList().add(Projections.property("id"),"id")
				      .add(Projections.property("planName"),"planName")
				      .add(Projections.property("status"),"status")
				      .add(Projections.property("durationinMonths"),"durationinMonths")
				      .add(Projections.property("planPrice"),"planPrice"))
				      .setResultTransformer(Transformers.aliasToBean(PlansDto.class));
		prodDtos = crit.list();
				
		LOGGER.info("PlansDaoImpl PlansDto : "+prodDtos.size());
		return prodDtos;
	}

}
