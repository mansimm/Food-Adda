package com.project.model;

import java.time.LocalDateTime;
import java.util.Date;

public class TableBookingDto {

	private Integer bookingId;
	private Date bookingDate;
	private LocalDateTime timeOfBooking;
	private Integer noOfPeople;
	private RestaurantDto restaurant;
	private UsersDto user;
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public LocalDateTime getTimeOfBooking() {
		return timeOfBooking;
	}
	public void setTimeOfBooking(LocalDateTime timeOfBooking) {
		this.timeOfBooking = timeOfBooking;
	}
	public Integer getNoOfPeople() {
		return noOfPeople;
	}
	public void setNoOfPeople(Integer noOfPeople) {
		this.noOfPeople = noOfPeople;
	}
	public RestaurantDto getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantDto restaurant) {
		this.restaurant = restaurant;
	}
	public UsersDto getUser() {
		return user;
	}
	public void setUser(UsersDto user) {
		this.user = user;
	}
	

}
