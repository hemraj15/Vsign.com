/**
 * 
 */
package com.vsign.tech.rest.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.vsign.tech.rest.constant.ErrorCodes;

/**
 * @author Hemraj
 *
 */
public class SignUpStep2Form {

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_EMAILTOKEN_NULL)
	@NotBlank(message = ErrorCodes.SIGNUP_COMPANY_EMAILTOKEN_EMPTY)
	private String	emailToken;

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_CONTACTNO_NULL)
	// @Length(max=10,min=10 , message=ErrorCodes.CONTACT_NUMBER_LENGTH_INVALI)
	private Long	contactNo;

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_COUNTRY_NULL)

	private Long	countryId;

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_STATE_NULL)

	private Long	stateId;

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_CITY_NULL)

	private Long	cityId;

	@NotNull(message = ErrorCodes.SIGNUP_COMPANY_ZIPCODE_NULL)

	// @Length(max = 6, min = 6, message = ErrorCodes.ZIPCODE_LENGTH_INVALID)
	private Integer	zipCode;

	/*
	 * @NotNull(message = ErrorCodes.USER_TYPE_EMPTY)
	 * 
	 * @NotBlank(message = ErrorCodes.USER_TYPE_NULL) private String userType;
	 */

	@Length(max = 6, message = ErrorCodes.HOUSE_NUMBER_LENGTH_INVALID)
	private String	houseNo;
	@Length(max = 4, message = ErrorCodes.STREET_NUMBER_LENGTH_INVALID)
	private String	StreetNo;
	@Length(max = 100, message = ErrorCodes.LANDMARK_LENGTH_INVALID)
	private String	landMark;
	@Length(max = 100, message = ErrorCodes.AREA_LENGTH_INVALID)
	private String	area;

	public String getEmailToken() {
		return emailToken;
	}

	public void setEmailToken(String emailToken) {
		this.emailToken = emailToken;
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

	/*
	 * public String getUserType() { return userType; } public void setUserType(String userType) {
	 * this.userType = userType; }
	 */
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SignUpStep2Form [emailToken=");
		builder.append(emailToken);
		builder.append(", contactNo=");
		builder.append(contactNo);
		builder.append(", countryId=");
		builder.append(countryId);
		builder.append(", stateId=");
		builder.append(stateId);
		builder.append(", cityId=");
		builder.append(cityId);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append(", houseNo=");
		builder.append(houseNo);
		builder.append(", StreetNo=");
		builder.append(StreetNo);
		builder.append(", landMark=");
		builder.append(landMark);
		builder.append(", area=");
		builder.append(area);
		builder.append("]");
		return builder.toString();
	}

}
