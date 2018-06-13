/**
 * 
 */
package com.vsign.tech.data.dao;

import java.util.List;

import com.vsign.tech.data.dao.entity.Plans;
import com.vsign.tech.data.dto.PlansDto;

/**
 * @author Hemraj
 *
 */
public interface PlansDao extends GenericDao<Plans, Long> {

	List<PlansDto> fetchAllPlans(String status);

}
