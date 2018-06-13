/**
 * 
 */
package com.vsign.tech.rest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsign.tech.data.dao.CustomersMachineWithPlansDao;
import com.vsign.tech.data.dto.DepartmentDto;
import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.exception.DatabaseException;

/**
 * @author Hemraj
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{
	private Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	@Autowired
	private CustomersMachineWithPlansDao deptDao;

	@Override
	public List<DepartmentDto> getDepartmentListByCompanyId(Long companyId) throws DatabaseException {
		
		List<DepartmentDto> deptDtos=null;
		try {
			
			deptDtos=deptDao.getDepartmentListByCompanyId(companyId);
		} catch (Exception e) {
			   LOGGER.error("Error occured while getting Department list through database", e);
			   throw new DatabaseException("Error occured while getting Department list through database",
			           ErrorCodes.DATABASE_ERROR);
			  }
		return deptDtos;
	}

}
