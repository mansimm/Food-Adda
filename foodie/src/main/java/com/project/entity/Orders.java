package com.project.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private Integer orderBill;
	private String orderStatus;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<OrderItems> orderItemsList;
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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
	public List<OrderItems> getOrderItemsList() {
		return orderItemsList;
	}
	public void setOrderItemsList(List<OrderItems> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}
	
}

