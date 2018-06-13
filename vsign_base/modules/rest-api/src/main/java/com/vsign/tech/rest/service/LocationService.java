package com.vsign.tech.rest.service;

import java.util.List;

import com.vsign.tech.data.dto.CityDto;
import com.vsign.tech.data.dto.CountryDto;
import com.vsign.tech.data.dto.StateDto;
import com.vsign.tech.data.exception.InstanceNotFoundException;
import com.vsign.tech.rest.exception.DatabaseException;

public interface LocationService {

	List<CountryDto> getAllCountries();

	List<StateDto> getAllStatesByCountry(Long countryId);

	List<CityDto> getAllCitiesByStates(Long stateId);

	List<CountryDto> getAllCountriesStatesByCountry(Long countryId, Long stateId)
	        throws InstanceNotFoundException, DatabaseException;

}
