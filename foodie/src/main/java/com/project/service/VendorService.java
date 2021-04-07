package com.project.service;

import java.util.List;

import com.project.exception.RestaurantNotFoundException;
import com.project.exception.UserServiceException;
import com.project.exception.VendorServiceException;
import com.project.model.RestaurantDto;

public interface VendorService {
	public String registerRestaurant(RestaurantDto restaurantDto, String contactNumber)
			throws UserServiceException, RestaurantNotFoundException, VendorServiceException;
	
	public List<RestaurantDto> viewRestaurantAndMenu(String contactNumber)
			throws UserServiceException,RestaurantNotFoundException;

}
