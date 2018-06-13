/**
 * 
 */
package com.vsign.tech.rest.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.vsign.tech.rest.constant.ErrorCodes;
import com.vsign.tech.rest.utils.ValidationUtils;

/**
 * @author Hemraj
 *
 */
public class GuestForm {
	
	@NotNull(message = ErrorCodes.LOGIN_USER_ID_NULL)
	@NotEmpty(message = ErrorCodes.LOGIN_USER_ID_EMPTY)
	@Email(message = ErrorCodes.LOGIN_EMAIL_INVALID, regexp = ValidationUtils.EMAIL_PATTERN)
	private String username;
	
	/*@NotNull(message = ErrorCodes.LOGIN_USER_PASSWORD_NULL)
	@NotEmpty(message = ErrorCodes.LOGIN_USER_PASSWORD_EMPTY)
	@Length(max = 25, min =5, message = ErrorCodes.PASSWORD_INVALID)
	private String password;*/
	@NotNull(message = ErrorCodes.MACHINE_ID_NULL)
	@NotEmpty(message = ErrorCodes.MACHINE_ID_EMPTY)
	private String machineId ;
	@NotNull(message = ErrorCodes.USER_TYPE_NULL)
	@NotEmpty(message = ErrorCodes.USER_TYPE_EMPTY)
	private String userType;
	@NotNull(message = ErrorCodes.GUEST_FIRST_NAME_NULL)
	@NotEmpty(message = ErrorCodes.GUEST_FIRST_NAME_EMPTY)
	private String firstName;
	private String lastName;
	private String anyOtherRequest ;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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

	public String getAnyOtherRequest() {
		return anyOtherRequest;
	}

	public void setAnyOtherRequest(String anyOtherRequest) {
		this.anyOtherRequest = anyOtherRequest;
	}

	/*public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	*/
	
	
}