package com.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.entity.Dish;
import com.project.entity.OrderItems;

public interface OrderItemsRepo extends CrudRepository<OrderItems,Integer>{

	public void deleteByDish(Dish dish);
}
