package com.vsign.tech.data.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.vsign.tech.data.dao.entity.City;
import com.vsign.tech.data.dao.entity.State;

/**
 * @author Hemraj
 * */
@JsonInclude(Include.NON_NULL)
public class CustomerDto {

	private Long	customerId;
	private String	firstName;
	private String	lastName;
	private String	email;
	private String	contactNumber;
	private AddressDto addressDto;
	@JsonInclude(Include.NON_EMPTY)
	private Set<OrderDto> orderDtos=new HashSet<>();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date	addedDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date	modifiedDate;
	private String	status;
	
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public AddressDto getAddressDto() {
		return addressDto;
	}
	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<OrderDto> getOrderDtos() {
		return orderDtos;
	}
	public void setOrderDtos(Set<OrderDto> orderDtos) {
		this.orderDtos = orderDtos;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerDto [customerId=");
		builder.append(customerId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", contactNumber=");
		builder.append(contactNumber);
		builder.append(", addressDto=");
		builder.append(addressDto);
		builder.append(", orderDtos=");
		builder.append(orderDtos);
		builder.append(", addedDate=");
		builder.append(addedDate);
		builder.append(", modifiedDate=");
		builder.append(modifiedDate);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
