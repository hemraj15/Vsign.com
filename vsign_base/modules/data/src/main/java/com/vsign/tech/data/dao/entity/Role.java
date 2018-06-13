package com.vsign.tech.data.dao.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * 
 * @author Hemraj
 */
@Entity
@Table(name = "role")
public class Role extends VsignBaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private String				name;
	private Set<User>			users				= new HashSet<>(0);

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}