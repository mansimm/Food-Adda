package com.project.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginCredentialsDto {

	@NotNull(message="{UserValidator.INVALID_CONTACT_NUMBER_NULL}")
	@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}")
	private String contactNumber;
	
	@NotNull(message="{UserValidator.INVALID_PASSWORD_NULL}")
	@Pattern(regexp="(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,20}",message="{UserValidator.INVALID_PASSWORD_FORMAT}")
	private String password;
	
	@NotNull(message="{UserValidator.INVALID_ROLE_NULL}")
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
