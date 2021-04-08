package com.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.entity.Restaurant;
import com.project.entity.TableBooking;

public interface TableBookingRepo extends CrudRepository<TableBooking,Integer> {
	
	public void deleteByRestaurant(Restaurant restaurant);
}
