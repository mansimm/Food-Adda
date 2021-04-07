package com.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Restaurant;
import com.project.exception.RestaurantNotFoundException;
import com.project.model.RestaurantDto;
import com.project.model.RestaurantTransactionDto;
import com.project.repository.RestaurantRepo;
import com.project.model.DishDto;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

	@Autowired
	RestaurantRepo searchRepo;

	@Override
	public List<RestaurantDto> getAllRestaurant() throws RestaurantNotFoundException {

		Iterable<Restaurant> restaurant = searchRepo.findAll();
		List<RestaurantDto> res = new ArrayList<RestaurantDto>();
		restaurant.forEach(x -> {
			RestaurantDto rt = new RestaurantDto(x.getRestaurantId(), x.getRestaurantName(), x.getRestaurantContact(),
					x.getRestaurantType(), x.getAddressLine1(), x.getArea(), x.getCity(), x.getResState(),
					x.getPincode(), x.getApprovalStatus());

			rt.setAvgRating(x.getAvgRating());

			String[] photos = x.getPhotoUrls().split("-");
			rt.setPhotoUrls(Arrays.asList(photos));

			if (x.getDishes() != null) {
				List<DishDto> dishes = x.getDishes().stream()
						.map(y -> new DishDto(y.getDishId(), y.getDishName(), y.getDishCuisine(), y.getDishType(),
								y.getDishDescription(), y.getPrice(), y.getAvgRating(), y.getSpeciality(),
								y.getImageUrl()))
						.collect(Collectors.toList());
				rt.setDishes(dishes);
			}
			
			if (x.getTransaction() != null) {
				RestaurantTransactionDto trans = new RestaurantTransactionDto(
						x.getTransaction().getRestaurantTransactionId(), x.getTransaction().getRestaurantOrderCounter(),
						x.getTransaction().getRestaurantApproxCost(), x.getTransaction().getRestaurantStatus());

				rt.setTransaction(trans);
			}
			res.add(rt);
		});
		// System.out.println(res);

		if (res.isEmpty()) {
			throw new RestaurantNotFoundException("SearchService.NO_RESTAURANTS_FOUND");
		}
		return res;
	}

}
