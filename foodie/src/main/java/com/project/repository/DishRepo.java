package com.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.entity.Dish;

public interface DishRepo extends CrudRepository<Dish,Integer> {

	public void deleteByDishId(Integer dishId);
}
