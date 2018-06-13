/**
 * 
 */
package com.vsign.tech.rest.form;

import com.vsign.tech.data.dao.entity.City;
import com.vsign.tech.data.dao.entity.Country;
import com.vsign.tech.data.dao.entity.State;

/**
 * @author Hemraj
 *
 */
public class AddressForm {

	private String	houseNo;
	private String	StreetNo;
	private String	landMark;
	private String	area;
	private Integer	pinCode;
	private Country	country;
	private City	city;
	private State	state;
	
	
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
	public Integer getPinCode() {
		return pinCode;
	}
	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressForm [houseNo=");
		builder.append(houseNo);
		builder.append(", StreetNo=");
		builder.append(StreetNo);
		builder.append(", landMark=");
		builder.append(landMark);
		builder.append(", area=");
		builder.append(area);
		builder.append(", pinCode=");
		builder.append(pinCode);
		builder.append(", country=");
		builder.append(country);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}
	
	

}
