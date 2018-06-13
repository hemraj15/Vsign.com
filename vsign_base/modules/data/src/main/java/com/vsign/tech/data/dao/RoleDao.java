package com.vsign.tech.data.dao;

import org.hibernate.Criteria;

import com.vsign.tech.data.dao.entity.Role;

public interface RoleDao extends GenericDao<Role, Long> {

	Criteria getFindByNameCriteria(String string);

}
