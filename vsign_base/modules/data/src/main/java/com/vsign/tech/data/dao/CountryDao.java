package com.vsign.tech.data.dao;

import java.util.List;

import com.vsign.tech.data.dao.entity.Country;
import com.vsign.tech.data.dto.CountryDto;

public interface CountryDao extends GenericDao<Country, Long> {
	List<CountryDto> getAllCountries();

}
