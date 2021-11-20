package com.project.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterDto {

	@NotNull(message="{UserValidator.INVALID_USERNAME_NULL}")
	@Pattern(regexp="[A-Za-z]{5,12}",message="{UserValidator.INVALID_USERNAME_FORMAT}")
	private String userName;
	
	@NotNull(message="{UserValidator.INVALID_CONTACT_NUMBER_NULL}")
	@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}")
	private String contactNumber;
	
	@NotNull(message="{UserValidator.INVALID_EMAIL_ID_NULL}")
	@Pattern(regexp="[A-Za-z0-9]+@([A-Za-z]+).(com|in)",message="{UserValidator.INVALID_EMAIL_ID_FORMAT}")
	private String emailId;
	
	@NotNull(message="{UserValidator.INVALID_PASSWORD_NULL}")
	@Pattern(regexp="(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,20}",message="{UserValidator.INVALID_PASSWORD_FORMAT}")
	private String password;
	
	@NotNull(message="{UserValidator.INVALID_ROLE_TYPE_NULL}")
	@Valid
	private Role role;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
