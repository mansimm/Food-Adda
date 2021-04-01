package com.project.model;

public class RestaurantTransactionDto {
	private Integer restaurantTransactionId;
	private Integer restaurantOrderCounter;
	private Integer restaurantApproxCost;
	private Boolean restaurantStatus;
	
	
	public RestaurantTransactionDto()
	{
		
	}
	public RestaurantTransactionDto(Integer restaurantTransactionId, Integer restaurantOrderCounter,
			Integer restaurantApproxCost, Boolean restaurantStatus) {
		super();
		this.restaurantTransactionId = restaurantTransactionId;
		this.restaurantOrderCounter = restaurantOrderCounter;
		this.restaurantApproxCost = restaurantApproxCost;
		this.restaurantStatus = restaurantStatus;
	}
	
	public Integer getRestaurantTransactionId() {
		return restaurantTransactionId;
	}
	public void setRestaurantTransactionId(Integer restaurantTransactionId) {
		this.restaurantTransactionId = restaurantTransactionId;
	}
	public Integer getRestaurantOrderCounter() {
		return restaurantOrderCounter;
	}
	public void setRestaurantOrderCounter(Integer restaurantOrderCounter) {
		this.restaurantOrderCounter = restaurantOrderCounter;
	}
	public Integer getRestaurantApproxCost() {
		return restaurantApproxCost;
	}
	public void setRestaurantApproxCost(Integer restaurantApproxCost) {
		this.restaurantApproxCost = restaurantApproxCost;
	}
	public Boolean getRestaurantStatus() {
		return restaurantStatus;
	}
	public void setRestaurantStatus(Boolean restaurantStatus) {
		this.restaurantStatus = restaurantStatus;
	}
	
}
