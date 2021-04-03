package com.project.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String emailId;
	private String contactNumber;
	private String password;
	
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private List<Roles> roles;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private List<Restaurant> restaurants;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<UserAddress> addressList;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", unique = true)
	private Wallet wallet;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private List<UserLikes> userLikesList;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Orders> ordersList;
	
	public List<Roles> getRoles() {
		return roles;
	}
	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	public List<UserAddress> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<UserAddress> addressList) {
		this.addressList = addressList;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public List<UserLikes> getUserLikesList() {
		return userLikesList;
	}
	public void setUserLikesList(List<UserLikes> userLikesList) {
		this.userLikesList = userLikesList;
	}
	public List<Orders> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
