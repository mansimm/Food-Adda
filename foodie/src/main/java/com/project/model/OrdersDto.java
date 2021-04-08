package com.project.model;

import java.util.List;


public class OrdersDto {

	private Integer orderId;
	private Integer orderBill;
	private String orderStatus;
	private List<OrderItemsDto> orderItemsList;
	private RestaurantDto restaurantDto;

	
	public RestaurantDto getRestaurantDto() {
		return restaurantDto;
	}
	public void setRestaurantDto(RestaurantDto restaurantDto) {
		this.restaurantDto = restaurantDto;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Integer getOrderBill() {
		return orderBill;
	}
	public void setOrderBill(Integer orderBill) {
		this.orderBill = orderBill;
	}
	public List<OrderItemsDto> getOrderItemsList() {
		return orderItemsList;
	}
	public void setOrderItemsList(List<OrderItemsDto> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}
	

}

