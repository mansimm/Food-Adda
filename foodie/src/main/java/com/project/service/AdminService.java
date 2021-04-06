package com.project.service;

import java.util.List;

import com.project.exception.RestaurantNotFoundException;
import com.project.exception.UserServiceException;
import com.project.model.RestaurantDto;

public interface AdminService {
	public List<RestaurantDto> viewAllRegistrationRequests(String contactNumber) throws RestaurantNotFoundException, UserServiceException;

}
