/**
 * 
 */
package com.vsign.tech.data.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Hemraj
 *
 */

@Entity
@Table(name = "plans")
public class Plans extends VsignBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -831975394904417784L;

	private String				durationinMonths;
	private String planName;
	private Double planPrice;
	
	@Column(name = "months")
	public String getDurationinMonths() {
		return durationinMonths;
	}

	public void setDurationinMonths(String durationinMonths) {
		this.durationinMonths = durationinMonths;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Double getPlanPrice() {
		return planPrice;
	}

	public void setPlanPrice(Double planPrice) {
		this.planPrice = planPrice;
	}
	

}
