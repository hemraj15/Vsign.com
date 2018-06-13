package com.vsign.tech.rest.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.vsign.tech.rest.constant.ErrorCodes;

public class JobForm {

	@NotNull(message = ErrorCodes.JOB_TITLE_NULL)
	@NotBlank(message = ErrorCodes.JOB_TITLE_EMPTY)
	private String	jobTitle;

	@NotNull(message = ErrorCodes.JOB_COUNTRY_NULL)
	@NotBlank(message = ErrorCodes.JOB_COUNTRY_EMPTY)
	private String	country;

	@NotNull(message = ErrorCodes.JOB_STATE_NULL)
	@NotBlank(message = ErrorCodes.JOB_STATE_EMPTY)
	private String	state;

	@NotNull(message = ErrorCodes.JOB_CITY_NULL)
	@NotBlank(message = ErrorCodes.JOB_CITY_EMPTY)
	private String	city;

	@NotNull(message = ErrorCodes.JOB_USER_NULL)
	private Long	user;

	@NotNull(message = ErrorCodes.JOB_DATE_OF_EXPIRY_NULL)
	private String	dateOfExpiry;

	@NotNull(message = ErrorCodes.JOB_CATEGORY_NULL)
	private Long	category;

	@NotNull(message = ErrorCodes.JOB_SKILL_SET_NULL)
	@NotBlank(message = ErrorCodes.JOB_SKILL_SET_EMPTY)
	private String	skillSet;

	@NotNull(message = ErrorCodes.JOB_FROM_EXPERIENCE_NULL)
	private Integer	fromExperience;

	@NotNull(message = ErrorCodes.JOB_TO_EXPERIENCE_NULL)
	private Integer	toExperience;

	@NotNull(message = ErrorCodes.JOB_DESCRIPTION_NULL)
	@NotBlank(message = ErrorCodes.JOB_DESCRIPTION_EMPTY)
	private String	description;

	private String	department;

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public String getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public void setFromExperience(Integer fromExperience) {
		this.fromExperience = fromExperience;
	}

	public void setToExperience(Integer toExperience) {
		this.toExperience = toExperience;
	}

	public String getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	public int getFromExperience() {
		return fromExperience;
	}

	public void setFromExperience(int fromExperience) {
		this.fromExperience = fromExperience;
	}

	public int getToExperience() {
		return toExperience;
	}

	public void setToExperience(int toExperience) {
		this.toExperience = toExperience;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
