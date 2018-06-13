package com.vsign.tech.data.dao.entity;

import java.io.Serializable;

// Generated Apr 26, 2016 12:08:16 AM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxyHelper;

/**
 * @author Hemraj
 */
@Entity
@Table(name = "user")
public class User extends VsignBaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String				emailId;
	private String				password;
	private Date				lastLoginDateTime;
	private String				firstName;
	private String				lastName;
	private Set<Role>			roles				= new HashSet<Role>(0);
	private String				tempPassword;
	private String userType;

	@Column(name = "email_id", nullable = false, length = 100)
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "password", nullable = false, length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_date_time")
	public Date getLastLoginDateTime() {
		return this.lastLoginDateTime;
	}

	public void setLastLoginDateTime(Date lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

/*	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "store_id")
	public PrintStore getCompany() {
		return printStore;
	}

	public void setCompany(PrintStore printStore) {
		this.printStore = printStore;
	}*/

	@ManyToMany( cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = {
	        @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
	                @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Column(name = "temp_password")
	public String getTempPassword() {
		return tempPassword;
	}

	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
}
