package com.vsign.tech.data.dao.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @Author
 * Hemraj
 * 
 */

@Entity
@Table(name = "printstore")
public class PrintStore extends VsignBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= -7270802444598116730L;

	private String					storeName;
	private Address					address;
	private String					storeContactNo;
	private String					storeLogoPath;
	private String					storeVideoPath;
	private String					storeWebsite;
	private Set<User>				users				= new HashSet<>(0);
	private Set<PrintStoreUrl>		printStoreUrls		= new HashSet<>(0);
	private Set<PrintStoreVideo>	printStoreVideos	= new HashSet<>(0);

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getStoreContactNo() {
		return storeContactNo;
	}

	public void setStoreContactNo(String storeContactNo) {
		this.storeContactNo = storeContactNo;
	}

	public String getStoreLogoPath() {
		return storeLogoPath;
	}

	public void setStoreLogoPath(String storeLogoPath) {
		this.storeLogoPath = storeLogoPath;
	}

	public String getStoreVideoPath() {
		return storeVideoPath;
	}

	public void setStoreVideoPath(String storeVideoPath) {
		this.storeVideoPath = storeVideoPath;
	}

	public String getStoreWebsite() {
		return storeWebsite;
	}

	public void setStoreWebsite(String storeWebsite) {
		this.storeWebsite = storeWebsite;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Set<PrintStoreUrl> getPrintStoreUrls() {
		return printStoreUrls;
	}

	public void setPrintStoreUrls(Set<PrintStoreUrl> printStoreUrls) {
		this.printStoreUrls = printStoreUrls;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Set<PrintStoreVideo> getPrintStoreVideos() {
		return printStoreVideos;
	}

	public void setPrintStoreVideos(Set<PrintStoreVideo> printStoreVideos) {
		this.printStoreVideos = printStoreVideos;
	}

}
