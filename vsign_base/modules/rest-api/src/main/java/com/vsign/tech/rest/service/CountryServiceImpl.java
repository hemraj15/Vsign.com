package com.vsign.tech.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsign.tech.data.dao.CountryDao;
import com.vsign.tech.data.dto.CountryDto;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;

	@Override
	@Transactional
	public List<CountryDto> getAllCountries() {

		return countryDao.getAllCountries();
	}

}
