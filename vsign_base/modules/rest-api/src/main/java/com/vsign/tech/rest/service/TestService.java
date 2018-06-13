package com.vsign.tech.rest.service;

import java.util.List;

import com.vsign.tech.data.dao.entity.PrintStore;
import com.vsign.tech.data.exception.InstanceNotFoundException;

public interface TestService {

	List<PrintStore> fetchAllCompanies();

	Long createEntity();

	void updateEntity(Long id) throws InstanceNotFoundException;

}
