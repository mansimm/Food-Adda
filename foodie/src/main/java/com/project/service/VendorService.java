package com.project.service;

import java.util.List;

import com.project.exception.RestaurantNotFoundException;
import com.project.exception.UserServiceException;
import com.project.exception.VendorServiceException;
import com.project.model.DishDto;
import com.project.model.RestaurantDto;

public interface VendorService {
	public String registerRestaurant(RestaurantDto restaurantDto, String contactNumber)
			throws UserServiceException, RestaurantNotFoundException, VendorServiceException;
	
	public List<RestaurantDto> viewRestaurantAndMenu(String contactNumber)
			throws UserServiceException,RestaurantNotFoundException;
	
	public String addDish(String contactNumber, Integer restaurantId, DishDto dishDto)
			throws UserServiceException, VendorServiceException; 
	
	public String updateDish(String contactNumber, Integer restaurantId, DishDto dishDto)
			throws UserServiceException, VendorServiceException;
	
	public String deleteDish(String contactNumber, Integer restaurantId, DishDto dishDto)
			throws UserServiceException, VendorServiceException ;

}
