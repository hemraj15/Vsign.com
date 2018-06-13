package com.vsign.tech.rest.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.utils.ValidationUtils;

public class SignUpStep1Form {

	@NotNull(message = ErrorCodes.SIGNUP_FIRST_NAME_NULL)
	@NotBlank(message = ErrorCodes.SIGNUP_FIRST_NAME_EMPTY)
	private String	firstName;

	@NotNull(message = ErrorCodes.SIGNUP_LAST_NAME_NULL)
	@NotBlank(message = ErrorCodes.SIGNUP_LAST_NAME_EMPTY)
	private String	lastName;

	@NotNull(message = ErrorCodes.SIGNUP_EMAIL_NULL)
	@NotBlank(message = ErrorCodes.SIGNUP_EMAIL_EMPTY)
	@Email(message = ErrorCodes.SIGNUP_EMAIL_INVALID, regexp = ValidationUtils.EMAIL_PATTERN)
	private String	email;

	@NotNull(message = ErrorCodes.SIGNUP_PASSWORD_NULL)
	@NotEmpty(message = ErrorCodes.SIGNUP_PASSWORD_EMPTY)
	@Length(max = 25, min = 5, message = ErrorCodes.SIGNUP_PASSWORD_LENGTH_INVALID)
	private String	password;
	
	/*@NotNull(message = ErrorCodes.SIGNUP_MACHINE_ID_NULL)
	@NotEmpty(message = ErrorCodes.SIGNUP_MACHINE_ID_NULL)
	//@Length(max = 25, min = 5, message = ErrorCodes.SIGNUP_PASSWORD_LENGTH_INVALID)
	private String	machineId;*/
	
	@NotNull(message = ErrorCodes.USER_TYPE_NULL)
	@NotBlank(message = ErrorCodes.USER_TYPE_EMPTY)
	private String	userType;
	

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_CONTACTNO_NULL)
	// @Length(max=10,min=10 , message=ErrorCodes.CONTACT_NUMBER_LENGTH_INVALI)
	private Long	contactNo;

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_COUNTRY_NULL)

	private Long	countryId;

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_STATE_NULL)

	private Long	stateId;

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_CITY_NULL)

	private Long	cityId;

	//@NotNull(message = ErrorCodes.SIGNUP_COMPANY_ZIPCODE_NULL)

	//@Length(max = 6, min = 6, message = ErrorCodes.ZIPCODE_LENGTH_INVALID)
	private Integer	zipCode;
	
	private String	emailToken;

	/*
	 * @NotNull(message = ErrorCodes.USER_TYPE_EMPTY)
	 * 
	 * @NotBlank(message = ErrorCodes.USER_TYPE_NULL) private String userType;
	 */

	//@Length(max = 6, message = ErrorCodes.HOUSE_NUMBER_LENGTH_INVALID)
	private String	houseNo;
//	@Length(max = 4, message = ErrorCodes.STREET_NUMBER_LENGTH_INVALID)
	private String	StreetNo;
	//@Length(max = 100, message = ErrorCodes.LANDMARK_LENGTH_INVALID)
	private String	landMark;
	//@Length(max = 100, message = ErrorCodes.AREA_LENGTH_INVALID)
	private String	area;

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String mchineId) {
		this.machineId = mchineId;
	}*/
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreetNo() {
		return StreetNo;
	}

	public void setStreetNo(String streetNo) {
		StreetNo = streetNo;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEmailToken() {
		return emailToken;
	}

	public void setEmailToken(String emailToken) {
		this.emailToken = emailToken;
	}

	
}
