/**
 * 
 */
package com.vsign.tech.rest.service;

import java.util.List;

import com.vsign.tech.data.dao.entity.UrlType;

/**
 * @author Developer
 *
 */
public interface CommonService {

	List<UrlType> getActiveUrlTypesCriteria();

}
