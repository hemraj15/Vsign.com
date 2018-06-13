package com.vsign.tech.data.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Hemraj
 * 
 * 
 * */
@JsonInclude(Include.NON_NULL)
public class StateDto {
	Long			id;
	String			name;
	@JsonInclude(Include.NON_EMPTY)
	List<CityDto>	cityDtos	= new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CityDto> getCityDtos() {
		return cityDtos;
	}

	public void setCityDtos(List<CityDto> cityDtos) {
		this.cityDtos = cityDtos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StateDto [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
