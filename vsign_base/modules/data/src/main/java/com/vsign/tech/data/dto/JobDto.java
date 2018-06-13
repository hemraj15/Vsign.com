package com.vsign.tech.data.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class JobDto {
	
	private Long						jobId;
	
	private String						jobTitle;
	
	private String						category;
	
	private String						skillSet;
	
	
	
	private String						experience;
	
	private String						location;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	
	private Date						expiryDate;
	
	private String						recruiter;
	
	private Integer						noOfCandidate;
	
	public Long getJobId() {
		return jobId;
	}
	
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getSkillset() {
		return skillSet;
	}
	
	public void setSkillset(String skillset) {
		this.skillSet = skillset;
		
	}
	
	public String getExperience() {
		return experience;
	}
	
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Date getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public String getRecruiter() {
		return recruiter;
	}
	
	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
	}
	
	
	
	public Integer getNoOfCandidate() {
		return noOfCandidate;
	}
	
	public void setNoOfCandidate(Integer candidateCount) {
		this.noOfCandidate = candidateCount;
	}
	
}
