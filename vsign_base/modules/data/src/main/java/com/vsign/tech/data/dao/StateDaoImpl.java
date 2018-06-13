package com.vsign.tech.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.vsign.tech.data.dao.entity.State;
import com.vsign.tech.data.dto.StateDto;

@Repository
public class StateDaoImpl extends GenericDaoImpl<State, Long> implements StateDao {

	private Logger LOGGER = LoggerFactory.getLogger(StateDaoImpl.class);

	@Override
	public List<StateDto> getAllStatesByCountry(Long countryId) {

		LOGGER.info(">> getStateDaoList");
		List<StateDto> stateDtos = null;
		Criteria criteria = getCriteria().add(Restrictions.eq("country.id", countryId))
		        .setProjection(Projections.projectionList().add(Projections.property("id"), "id")
		                .add(Projections.property("name"), "name"))
		        .setResultTransformer(Transformers.aliasToBean(StateDto.class));
		stateDtos = criteria.list();
		LOGGER.debug("<< getStateDaoList" + stateDtos.toString());
		return stateDtos;
	}

}
