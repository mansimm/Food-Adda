package com.project.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class DishDto {

	private Integer dishId;
	
	@Pattern(regexp="[A-Z][A-Za-z]+( [A-Za-z]+)*",message="{DishValidator.INVALID_DISH_NAME}")
	private String dishName;
	
	@Pattern(regexp="[A-Za-z]+( [A-Za-z]+)*",message="{DishValidator.INVALID_DISH_CUISINE}")
	private String dishCuisine;
	
	@Pattern(regexp="(Nonveg|Veg|Vegan|Other)",message="{DishValidator.INVALID_DISH_TYPE}")
	private String dishType;
	
	@Pattern(regexp="[A-Z][A-Za-z,]+( [A-Za-z,]+)*",message="{DishValidator.INVALID_DISH_DESCRIPTION}")
	private String dishDescription;
	
	@Min(value=0,message="{DishValidator.INVALID_DISH_PRICE}")
	private Double price;
	
	@Min(value=0,message="{DishValidator.INVALID_DISH_RATING}")
	@Max(value=5,message="{DishValidator.INVALID_DISH_RATING}")
	private Double avgRating;
	
	@Pattern(regexp="[A-Z][A-Za-z ]+",message="{DishValidator.INVALID_DISH_SPECIALTY}")
	private String speciality;
	private String imageUrl;

	public DishDto() {}
	
	
	public DishDto(Integer dishId, String dishName, String dishCuisine, String dishType, String dishDescription,
			Double price, Double avgRating, String speciality, String imageUrl) {
		super();
		this.dishId = dishId;
		this.dishName = dishName;
		this.dishCuisine = dishCuisine;
		this.dishType = dishType;
		this.dishDescription = dishDescription;
		this.price = price;
		this.avgRating = avgRating;
		this.speciality = speciality;
		this.imageUrl = imageUrl;
	}
	public Integer getDishId() {
		return dishId;
	}
	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public String getDishCuisine() {
		return dishCuisine;
	}
	public void setDishCuisine(String dishCuisine) {
		this.dishCuisine = dishCuisine;
	}
	public String getDishType() {
		return dishType;
	}
	public void setDishType(String dishType) {
		this.dishType = dishType;
	}
	public String getDishDescription() {
		return dishDescription;
	}
	public void setDishDescription(String dishDescription) {
		this.dishDescription = dishDescription;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
