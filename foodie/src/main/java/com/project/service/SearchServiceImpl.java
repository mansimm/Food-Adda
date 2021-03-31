package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Restaurant;
import com.project.model.RestaurantDto;
import com.project.repository.SearchRepo;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchRepo searchRepo;
	
	@Override
	public List<RestaurantDto> getAllRestaurant() {
		
		Iterable<Restaurant> restaurant = searchRepo.findAll();
		List<RestaurantDto> res = new ArrayList<RestaurantDto>();
		restaurant.forEach(x->{
			RestaurantDto rt = new RestaurantDto(x.getRestaurantId(),x.getRestaurantName(),x.getRestaurantContact(),
					x.getRestaurantType(),x.getAddressLine1(),x.getArea(),x.getCity(),x.getResState(),x.getPincode(),x.getApprovalStatus());
			res.add(rt);
		});
		System.out.println(res);
		return res;
	}

}
