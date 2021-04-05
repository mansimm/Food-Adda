package com.project.service;
import java.util.List;

import com.project.exception.UserServiceException;
import com.project.model.RestaurantDto;


public interface SearchService {

	public List<RestaurantDto> getAllRestaurant() throws Exception;


}
