package com.project.model;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class UsersDto {
	
	private Integer userId;
	
	@NotNull(message="{UserValidator.INVALID_USERNAME_NULL}")
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*",message="{UserValidator.INVALID_USERNAME_FORMAT}")
	private String userName;
	
	@NotNull(message="{UserValidator.INVALID_CONTACT_NUMBER_NULL}")
	@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}")
	private String contactNumber;
	
	@NotNull(message="{UserValidator.INVALID_EMAIL_ID_NULL}")
	@Pattern(regexp="[A-Za-z0-9]+@([A-Za-z]+).(com|in)",message="{UserValidator.INVALID_EMAIL_ID_FORMAT}")
	private String emailId;
	
	@NotNull(message="{UserValidator.INVALID_PASSWORD_NULL}")
	@Min(value=7,message="{UserValidator.INVALID_PASSWORD_FORMAT}")
	@Max(value=20,message="{UserValidator.INVALID_PASSWORD_FORMAT}")
	//@Pattern(regexp="[A-Z]&&[a-z]&&[0-9]&&[!@#$%^&*]",message="{UserValidator.INVALID_PASSWORD_FORMAT}")
	private String password;
	
	@NotNull(message="{UserValidator.INVALID_ROLE_TYPE_NULL}")
	private List<RolesDto> roles;
	
	private List<RestaurantDto> restaurants;
	private List<UserAddressDto> addressList;
	private WalletDto wallet;
	private List<UserLikesDto> userLikesList;
	private List<OrdersDto> ordersList;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
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
	
	public List<RolesDto> getRoles() {
		return roles;
	}
	public void setRoles(List<RolesDto> roles) {
		this.roles = roles;
	}
	public List<RestaurantDto> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<RestaurantDto> restaurants) {
		this.restaurants = restaurants;
	}
	public List<UserAddressDto> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<UserAddressDto> addressList) {
		this.addressList = addressList;
	}
	public WalletDto getWallet() {
		return wallet;
	}
	public void setWallet(WalletDto wallet) {
		this.wallet = wallet;
	}
	public List<UserLikesDto> getUserLikesList() {
		return userLikesList;
	}
	public void setUserLikesList(List<UserLikesDto> userLikesList) {
		this.userLikesList = userLikesList;
	}
	public List<OrdersDto> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<OrdersDto> ordersList) {
		this.ordersList = ordersList;
	}
	
}
