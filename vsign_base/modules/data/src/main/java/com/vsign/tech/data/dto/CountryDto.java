package com.vsign.tech.data.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Hemraj
 * 
 */

@JsonInclude(Include.NON_NULL)
public class CountryDto {
	private Long id;

	private String name;

	@JsonInclude(Include.NON_EMPTY)
	private List<StateDto> states = new ArrayList<>();

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

	public List<StateDto> getStates() {
		return states;
	}

	public void setStates(List<StateDto> states) {
		this.states = states;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CountryDto [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", states=");
		builder.append(states);
		builder.append("]");
		return builder.toString();
	}

}
