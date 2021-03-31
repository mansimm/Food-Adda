package com.project.model;

public class UserLikesDto {

	private Integer likeId;
	private String vegNonveg;
	private DishDto dish;
	private RestaurantDto restaurant;
	public Integer getLikeId() {
		return likeId;
	}
	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
	}
	public String getVegNonveg() {
		return vegNonveg;
	}
	public void setVegNonveg(String vegNonveg) {
		this.vegNonveg = vegNonveg;
	}
	
	public DishDto getDish() {
		return dish;
	}
	public void setDish(DishDto dish) {
		this.dish = dish;
	}
	public RestaurantDto getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantDto restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
