package com.vsign.tech.data.dao;

import org.hibernate.Criteria;

import com.vsign.tech.data.dao.entity.UrlType;

public interface UrlTypeDao extends GenericDao<UrlType, Long> {
	Criteria getListByStatusCriteria(String status);

}
