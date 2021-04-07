package com.project.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class RestaurantDto {
	
	private Integer restaurantId;
	
	@NotNull(message="{RestaurantValidator.INVALID_RESTAURANT_NAME_NULL}")
	@Pattern(regexp="[A-Z][A-Za-z]*( [A-Za-z]+)*",message="{RestaurantValidator.INVALID_RESTAURANT_NAME}")
	private String restaurantName;
	
	@NotNull(message="{RestaurantValidator.INVALID_RESTAURANT_CONTACT_NULL}")
	@Pattern(regexp="[6789][0-9]{9}",message="{RestaurantValidator.INVALID_RESTAURANT_CONTACT}")
	private String restaurantContact;
	
	@Pattern(regexp="(Nonveg|Veg|Vegan|Other)",message="{RestaurantValidator.INVALID_RESTAURANT_TYPE}")
	private String restaurantType;
	
	@Pattern(regexp="[A-Za-z ,0-9]+",message="{RestaurantValidator.INVALID_RESTAURANT_ADDRESSLINE1}")
	private String addressLine1;
	
	@Pattern(regexp="[a-zA-Z ]+",message="{RestaurantValidator.INVALID_RESTAURANT_AREA}")
	private String area;
	
	@Pattern(regexp="[a-zA-Z]+",message="{RestaurantValidator.INVALID_RESTAURANT_CITY}")
	private String city;
	
	@Pattern(regexp="[a-zA-Z]+",message="{RestaurantValidator.INVALID_RESTAURANT_STATE}")
	private String resState;
	
	@Min(value=100000,message="{RestaurantValidator.INVALID_RESTAURANT_PIN}")
	@Max(value=999999,message="{RestaurantValidator.INVALID_RESTAURANT_PIN}")
	private Integer pincode;
	
	private String approvalStatus;
	
	@Min(value=0,message="{RestaurantValidator.INVALID_RESTAURANT_RATING}")
	@Max(value=5,message="{RestaurantValidator.INVALID_RESTAURANT_RATING}")
	private double avgRating;
	
	//@NotNull(message="{RestaurantValidator.INVALID_RESTAURANT_DISH_NULL}")
	@Valid
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
