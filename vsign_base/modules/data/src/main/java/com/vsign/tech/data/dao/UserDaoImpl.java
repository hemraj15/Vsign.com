package com.vsign.tech.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.vsign.tech.data.dao.entity.User;
import com.vsign.tech.data.dto.UserDto;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	private Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public Criteria getFindByEmailCriteria(String email) {
		return getCriteria().add(Restrictions.eq("emailId", email));
	}

	@Override
	public Criteria getFindByUsernameCriteria(String username) {
		return getCriteria().add(Restrictions.eq("emailId", username))
		        .add(Restrictions.in("status", "ACTIVE", "FORGET_PASSWORD_INITIATED"));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDto> getRecruiterDTOList(String status, Long companyId, String roleType) {
		LOGGER.info(">> getRecruiterDTOList");
		List<UserDto> userDtos = null;
		Criteria criteria = getCriteria().createAlias("roles", "roles")
		        .add(Restrictions.eq("status", status))
		        .add(Restrictions.eq("company.id", companyId))
		        .add(Restrictions.eq("roles.name", roleType))
		        .setProjection(Projections.projectionList().add(Projections.property("id"), "id")
		                .add(Projections.property("firstName"), "firstName")
		                .add(Projections.property("lastName"), "lastName")
		                .add(Projections.property("emailId"), "emailId"))
		        .setResultTransformer(Transformers.aliasToBean(UserDto.class));
		userDtos = criteria.list();

		LOGGER.info("getRecruiterDTOList <<");
		return userDtos;
	}

	@Override
	public Criteria getFingByeUserRole(String admin) {
		
		LOGGER.info("getFingByeUserRole for "+admin);
		Criteria crit=getCriteria().createAlias("roles", "role")
				.add(Restrictions.eq("role.name", admin));
		return crit;
	}
}
