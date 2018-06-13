package com.vsign.tech.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.vsign.tech.data.dao.UserDao;
import com.vsign.tech.data.dao.entity.User;
import com.vsign.tech.data.exception.InstanceNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	private Logger	logger	= LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserDao	userDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		logger.info("try to login user .. username:" + username);
		try {
			User user = (User) userDao.getByCriteria(userDao.getFindByUsernameCriteria(username));
			return new UserDetailsImpl(user);
		} catch (InstanceNotFoundException e) {
			logger.error("User not found !", e);
			throw new UsernameNotFoundException("User not found : " + e.getLocalizedMessage());
		}
	}

}