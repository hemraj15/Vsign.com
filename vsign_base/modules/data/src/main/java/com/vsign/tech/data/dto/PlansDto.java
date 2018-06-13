/**
 * 
 */
package com.vsign.tech.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Hemraj
 *
 */

@JsonInclude(Include.NON_NULL)
public class PlansDto {

	private Long	id;
	private String				durationinMonths;
	private String planName;
	private Double planPrice;
/*	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date	dateCreated;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	protected Date	dateUpdated;*/

	private String	status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
