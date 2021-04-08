package com.project.service;

import java.util.List;

import com.project.exception.AdminServiceException;
import com.project.exception.RestaurantNotFoundException;
import com.project.exception.UserServiceException;
import com.project.model.RestaurantDto;

public interface AdminService {
	public List<RestaurantDto> viewAllRegistrationRequests(String contactNumber)
			throws RestaurantNotFoundException, UserServiceException;

	public String accepctRegistrationRequest(RestaurantDto restaurantDto, String contactNumber)
			throws UserServiceException, RestaurantNotFoundException, AdminServiceException;

	public String rejectRegistrationRequest(RestaurantDto restaurantDto, String contactNumber)
			throws UserServiceException, RestaurantNotFoundException, AdminServiceException;

	public List<RestaurantDto> viewLowRatingRestaurants(String contactNumber)
			throws RestaurantNotFoundException, UserServiceException, AdminServiceException;

	public String deleteLowRatingRestaurant(RestaurantDto restaurantDto, String contactNumber)
			throws UserServiceException, AdminServiceException;

}
