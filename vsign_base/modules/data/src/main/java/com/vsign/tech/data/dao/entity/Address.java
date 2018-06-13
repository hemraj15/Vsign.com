package com.vsign.tech.data.dao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

/**
 * 
 * @author Hemraj
 * */

@Entity
@Table(name = "address")
public class Address extends VsignBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5050890745292541687L;

	private String				houseNo;
	private String				StreetNo;
	private String				landMark;
	private String				area;
	private Integer				pinCode;
	private Long				cityId;
	private Long				stateId;
	private Long                countryId;

	@Column(name = "houseNo")
	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	@Column(name = "streetNo")
	public String getStreetNo() {
		return StreetNo;
	}

	public void setStreetNo(String streetNo) {
		StreetNo = streetNo;
	}

	@Column(name = "landmark")
	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	@Column(name = "area")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "pincode")
	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}
/*
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "cityId")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}


	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "stateId")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "countryId")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

*/

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}	
	
}
