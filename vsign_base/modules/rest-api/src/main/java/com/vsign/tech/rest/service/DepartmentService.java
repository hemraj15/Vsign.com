/**
 * 
 */
package com.vsign.tech.rest.service;

import java.util.List;

import com.vsign.tech.data.dto.DepartmentDto;
import com.vsign.tech.rest.exception.DatabaseException;

/**
 * @author Hemraj
 *
 */
public interface DepartmentService {

	List<DepartmentDto> getDepartmentListByCompanyId(Long companyId) throws DatabaseException;

}
