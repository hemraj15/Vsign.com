package com.vsign.tech.data.dao;

import java.util.List;

import com.vsign.tech.data.dao.entity.State;
import com.vsign.tech.data.dto.StateDto;

public interface StateDao extends GenericDao<State, Long> {
	List<StateDto> getAllStatesByCountry(Long companyId);

}
