package com.vsign.tech.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vsign.tech.data.dao.entity.Role;
import com.vsign.tech.data.dao.entity.User;

public class UserDetailsImpl implements UserDetails {

	private static final long		serialVersionUID	= -17624535977779037L;

	private Logger					logger				= LoggerFactory
	        .getLogger(UserDetailsImpl.class);

	private User					user;
	private List<GrantedAuthority>	authorityList;

	public UserDetailsImpl(User user) {
		this.user = user;
		createAuthorities();
	}

	private void createAuthorities() {
		logger.debug("in createAuthorities...");
		authorityList = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			authorityList.add(new SimpleGrantedAuthority(role.getName()));
		}
		logger.debug("authorityList::" + authorityList);
		logger.info("permisions loaded!");
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return authorityList;
	}

	@Override
	public String getPassword() {
		//return PasswordUtils.decode(user.getPassword());
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmailId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}
}