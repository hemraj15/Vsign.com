package com.vsign.tech.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.vsign.tech.data.dao.entity.Country;
import com.vsign.tech.data.dto.CountryDto;

@Repository
public class CountryDaoImpl extends GenericDaoImpl<Country, Long> implements CountryDao {
	private Logger LOGGER = LoggerFactory.getLogger(CountryDaoImpl.class);

	@Override
	public List<CountryDto> getAllCountries() {
		LOGGER.info(">> getCountryDTOList");
		List<CountryDto> countryDtos = null;
		Criteria criteria = getCriteria()
		        .setProjection(Projections.projectionList().add(Projections.property("id"), "id")
		                .add(Projections.property("name"), "name"))
		        .setResultTransformer(Transformers.aliasToBean(CountryDto.class));
		countryDtos = criteria.list();
		LOGGER.debug("<< getCountryDTOList" + countryDtos);
		return countryDtos;
	}

}
