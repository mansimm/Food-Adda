package com.project.model;

import java.util.List;

public class ViewOrdersDto {
	Integer orderId;
	List<OrderItemsDto> orderItems;
	String restaurantName;
	Integer totalPrice;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public List<OrderItemsDto> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemsDto> orderItems) {
		this.orderItems = orderItems;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
