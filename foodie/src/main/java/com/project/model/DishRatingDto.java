package com.project.model;

public class DishRatingDto {

	private Integer dishRatingId;
	private DishDto dish;
	private UsersDto user;
	private Integer rating;
	
	public Integer getDishRatingId() {
		return dishRatingId;
	}
	public void setDishRatingId(Integer dishRatingId) {
		this.dishRatingId = dishRatingId;
	}
	
	public DishDto getDish() {
		return dish;
	}
	public void setDish(DishDto dish) {
		this.dish = dish;
	}
	public UsersDto getUser() {
		return user;
	}
	public void setUser(UsersDto user) {
		this.user = user;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
