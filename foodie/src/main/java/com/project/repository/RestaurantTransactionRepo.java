package com.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.entity.RestaurantTransaction;

public interface RestaurantTransactionRepo extends CrudRepository<RestaurantTransaction,Integer>{

	public void deleteByRestaurantTransactionId(Integer restaurantTransactionId);
}
