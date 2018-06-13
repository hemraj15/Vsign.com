/**
 * 
 */
package com.vsign.tech.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.vsign.tech.data.dao.entity.City;
import com.vsign.tech.data.dao.entity.State;

/**
 * @author Hemraj
 *
 */
@JsonInclude(Include.NON_NULL)
public class AddressDto {

	private String		houseNo;
	private String		StreetNo;
	private String		landMark;
	private String		area;
	private Integer		pinCode;
	private CityDto		cityDto;
	private StateDto	stateDto;
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
	public CityDto getCityDto() {
		return cityDto;
	}
	public void setCityDto(CityDto cityDto) {
		this.cityDto = cityDto;
	}
	public StateDto getStateDto() {
		return stateDto;
	}
	public void setStateDto(StateDto stateDto) {
		this.stateDto = stateDto;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressDto [houseNo=");
		builder.append(houseNo);
		builder.append(", StreetNo=");
		builder.append(StreetNo);
		builder.append(", landMark=");
		builder.append(landMark);
		builder.append(", area=");
		builder.append(area);
		builder.append(", pinCode=");
		builder.append(pinCode);
		builder.append(", cityDto=");
		builder.append(cityDto);
		builder.append(", stateDto=");
		builder.append(stateDto);
		builder.append("]");
		return builder.toString();
	}
	
	

}
