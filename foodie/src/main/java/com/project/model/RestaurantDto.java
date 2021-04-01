package com.project.model;

import java.util.List;

public class RestaurantDto {
	
	private Integer restaurantId;
	private String restaurantName;
	private String restaurantContact;
	private String restaurantType;
	private String addressLine1;
	private String area;
	private String city;
	private String resState;
	private Integer pincode;
	private String approvalStatus;
	
	private double avgRating;
	private List<DishDto> dishes;
	private List<String> photoUrls;
	private RestaurantTransactionDto transaction;
	
	public RestaurantDto()
	{
		
	}
	
	
	public RestaurantDto(Integer restaurantId, String restaurantName, String restaurantContact, String restaurantType,
			String addressLine1, String area, String city, String resState, Integer pincode, String approvalStatus) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantContact = restaurantContact;
		this.restaurantType = restaurantType;
		this.addressLine1 = addressLine1;
		this.area = area;
		this.city = city;
		this.resState = resState;
		this.pincode = pincode;
		this.approvalStatus = approvalStatus;
	}
	
	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public List<DishDto> getDishes() {
		return dishes;
	}

	public void setDishes(List<DishDto> dishes) {
		this.dishes = dishes;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public RestaurantTransactionDto getTransaction() {
		return transaction;
	}

	public void setTransaction(RestaurantTransactionDto transaction) {
		this.transaction = transaction;
	}

	
	
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantContact() {
		return restaurantContact;
	}
	public void setRestaurantContact(String restaurantContact) {
		this.restaurantContact = restaurantContact;
	}
	public String getRestaurantType() {
		return restaurantType;
	}
	public void setRestaurantType(String restaurantType) {
		this.restaurantType = restaurantType;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
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
	public String getResState() {
		return resState;
	}
	public void setResState(String resState) {
		this.resState = resState;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	
}
