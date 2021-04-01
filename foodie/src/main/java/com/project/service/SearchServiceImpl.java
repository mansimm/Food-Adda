package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Restaurant;
import com.project.model.RestaurantDto;
import com.project.model.RestaurantTransactionDto;
import com.project.repository.SearchRepo;
import com.project.model.DishDto;

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
			
			rt.setAvgRating(x.getAvgRating());
			List<String> url = new ArrayList<String>();
			url.add(x.getPhotoUrls());
			rt.setPhotoUrls(url);
			
			List<DishDto> dishes = x.getDishes().stream().map(y->new DishDto(y.getDishId(),
					 y.getDishName(), 
					 y.getDishCuisine(),  y.getDishType(),  y.getDishDescription(),
					 y.getPrice(),  y.getAvgRating(),  y.getSpeciality(),  y.getImageUrl())).collect(Collectors.toList());
			rt.setDishes(dishes);
			
			RestaurantTransactionDto trans = new RestaurantTransactionDto(
					x.getTransaction().getRestaurantTransactionId(),
					x.getTransaction().getRestaurantOrderCounter(),
					x.getTransaction().getRestaurantApproxCost(),
					x.getTransaction().getRestaurantStatus());
			
			rt.setTransaction(trans);
			res.add(rt);
		});
		System.out.println(res);
		return res;
	}

}
