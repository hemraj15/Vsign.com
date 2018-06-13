package com.vsign.tech.data.dao;

import java.util.List;

import com.vsign.tech.data.dao.entity.City;
import com.vsign.tech.data.dto.CityDto;

public interface CityDao extends GenericDao<City, Long> {
	List<CityDto> getAllCitiesByState(Long stateId);

}
