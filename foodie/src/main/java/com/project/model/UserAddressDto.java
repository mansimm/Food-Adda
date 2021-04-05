package com.project.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserAddressDto {
	
	private Integer userAddressId;
	
	@NotEmpty(message="{UserValidator.INVALID_ADDRESS_NAME}")
	private String userAddressName;
	
	@Pattern(regexp="[A-Za-z0-9, ]+",message="{UserValidator.INVALID_ADDRESS_LINE1}")
	private String addressLine1;
	
	@Pattern(regexp="[A-Za-z0-9, ]+",message="{UserValidator.INVALID_ADDRESS_LINE2}")
	private String addressLine2;
	
	@Pattern(regexp="[A-Za-z ]+",message="{UserValidator.INVALID_ADDRESS_AREA}")
	private String area;
	
	@Pattern(regexp="[A-Za-z]+",message="{UserValidator.INVALID_ADDRESS_CITY}")
	private String city;
	
	@Pattern(regexp="[A-Za-z]+",message="{UserValidator.INVALID_ADDRESS_STATE}")
	private String userState;
	
	@Pattern(regexp="[0-9]{6}",message="{UserValidator.INVALID_ADDRESS_PINCODE}")
	private String pincode;
	
	public Integer getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(Integer userAddressId) {
		this.userAddressId = userAddressId;
	}
	public String getUserAddressName() {
		return userAddressName;
	}
	public void setUserAddressName(String userAddressName) {
		this.userAddressName = userAddressName;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	

}
