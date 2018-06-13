/**
 * 
 */
package com.vsign.tech.data.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Hemraj
 *
 */
@Entity
@Table(name = "cust_machine_plans")
public class CustomersMachineWithPlans extends VsignBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3498103970963330753L;

	private String	machinId;	
	private Long custId;
	private Long planId;
	private Date planExpiryDate;
	private Date dateOfPurchase;
	private String licenseKey;
	
	
	public String getMachinId() {
		return machinId;
	}
	public void setMachinId(String machinId) {
		this.machinId = machinId;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	//@Column(name = "date_created")
	@Temporal(TemporalType.DATE)
	@PrePersist
	public Date getPlanExpiryDate() {
		return planExpiryDate == null ? new Date() : planExpiryDate;
	}
	public void setPlanExpiryDate(Date planExpiryDate) {
		this.planExpiryDate = planExpiryDate;
	}
	
	@Temporal(TemporalType.DATE)
	@PrePersist
	public Date getDateOfPurchase() {
		return dateOfPurchase == null ? new Date() : dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	/**
	 * @return the licenseKey
	 */
	public String getLicenseKey() {
		return licenseKey;
	}
	/**
	 * @param licenseKey the licenseKey to set
	 */
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

}
