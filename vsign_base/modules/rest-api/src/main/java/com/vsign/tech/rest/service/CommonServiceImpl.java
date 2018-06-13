/**
 * 
 */
package com.vsign.tech.rest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsign.tech.data.dao.UrlTypeDao;
import com.vsign.tech.data.dao.entity.UrlType;
import com.vsign.tech.rest.constant.CommonStatus;

/**
 * @author Developer
 *
 */

@Service
public class CommonServiceImpl implements CommonService {

	private Logger		LOGGER	= LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired
	private UrlTypeDao	urlTypeDao;

	@Override
	@Transactional
	public List<UrlType> getActiveUrlTypesCriteria() {
		List<UrlType> urlTypes = null;
		urlTypes = urlTypeDao.searchByCriteria(
		        urlTypeDao.getListByStatusCriteria(CommonStatus.ACTIVE.toString()));
		LOGGER.debug("URL Types : " + urlTypes);
		return urlTypes;
	}
}
