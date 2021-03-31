package com.project.model;

public class OrderItemsDto {
	private Integer orderItemsId;
	private DishDto dish;
	private Integer qty;
	public Integer getOrderItemsId() {
		return orderItemsId;
	}
	public void setOrderItemsId(Integer orderItemsId) {
		this.orderItemsId = orderItemsId;
	}
	public DishDto getDish() {
		return dish;
	}
	public void setDish(DishDto dish) {
		this.dish = dish;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
}
