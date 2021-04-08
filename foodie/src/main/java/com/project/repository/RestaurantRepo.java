package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.entity.Restaurant;
import com.project.model.RestaurantDto;

public interface RestaurantRepo extends CrudRepository<Restaurant,Integer>{

	
	public List<Restaurant> getAllRestaurantByArea(String area);

}
