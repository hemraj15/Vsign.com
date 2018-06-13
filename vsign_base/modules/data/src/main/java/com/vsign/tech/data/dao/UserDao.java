package com.vsign.tech.data.dao;

import java.util.List;

import org.hibernate.Criteria;

import com.vsign.tech.data.dao.entity.User;
import com.vsign.tech.data.dto.UserDto;

public interface UserDao extends GenericDao<User, Long> {

	Criteria getFindByEmailCriteria(String email);

	Criteria getFindByUsernameCriteria(String username);

	List<UserDto> getRecruiterDTOList(String status, Long companyId, String roleType);

	Criteria getFingByeUserRole(String admin);

}
