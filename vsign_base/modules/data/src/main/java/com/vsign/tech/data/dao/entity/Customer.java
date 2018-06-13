package com.vsign.tech.data.dao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/***
 * 
 * @author Hemraj
 * */
@Entity
@Table(name = "customer")
public class Customer extends VsignBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8142887062092391543L;

	private String				firstName;
	private String				lastName;
	private String				email;
	private Long				contactNumber;
	private Long				altContactNumber;
	private Address				address;
	//private Set<CustOrder> custOrders=new HashSet<>();

	@Column(name = "firstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "contactNumber")
	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Column(name = "altContactNumber")
	public Long getAltContactNumber() {
		return altContactNumber;
	}

	public void setAltContactNumber(Long altContactNumber) {
		this.altContactNumber = altContactNumber;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


	/*@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "customer_orders", joinColumns = {
	        @JoinColumn(name = "cust_id", nullable = false, updatable = false) }, inverseJoinColumns = {
	                @JoinColumn(name = "ord_id", nullable = false, updatable = false) })
	public Set<CustOrder> getCustOrders() {
		return custOrders;
	}

	public void setCustOrders(Set<CustOrder> custOrders) {
		this.custOrders = custOrders;
	}
	*/
	

}