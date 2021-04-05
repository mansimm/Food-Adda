package com.project.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Dish;
import com.project.entity.OrderItems;
import com.project.entity.Orders;
import com.project.entity.Users;
import com.project.exception.UserServiceException;
import com.project.model.LoginCredentialsDto;
import com.project.model.OrderItemsDto;
import com.project.model.OrderStatus;
import com.project.model.UsersDto;
import com.project.repository.UserRepo;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public Orders placeOrder(List<OrderItemsDto> orderItemsList,String contactNumber) throws UserServiceException
	{
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(()->new UserServiceException("orderService.NO_USER_FOUND"));
		
		if(orderItemsList==null || orderItemsList.isEmpty())
		{
			throw  new UserServiceException("orderService.NO_ORDER_FOUND");
		}
		List<OrderItems> orderItemsEntityList;
		orderItemsEntityList = orderItemsList.stream().map(x->{
			OrderItems orderItem = new OrderItems();
			orderItem.setQty(x.getQty());
			Dish dish = new Dish();
			dish.setAvgRating(x.getDish().getAvgRating());
			dish.setDishCuisine(x.getDish().getDishCuisine());
			dish.setDishDescription(x.getDish().getDishDescription());
			dish.setDishName(x.getDish().getDishName());
			dish.setDishType(x.getDish().getDishType());
			dish.setImageUrl(x.getDish().getImageUrl());
			dish.setPrice(x.getDish().getPrice());
			dish.setSpeciality(x.getDish().getSpeciality());
			orderItem.setDish(dish);
			return orderItem;
		}).collect(Collectors.toList());
		
		Orders Orders = new Orders();
		Orders.setOrderItemsList(orderItemsEntityList);
		Orders.setOrderStatus(OrderStatus.ACTIVE.toString());
		Orders.setOrderBill(calculateOrderBill(orderItemsEntityList));
		
		if(user.getOrdersList()== null)
		{
			List<Orders> ordersList = new ArrayList<Orders>();
			ordersList.add(Orders);
			user.setOrdersList(ordersList);
		}
		else
		{
			user.getOrdersList().add(Orders);
		}
		userRepo.save(user);
		return Orders;
	}
	
	public Integer calculateOrderBill(List<OrderItems> orderItemsEntityList)
	{
		Double bill = 0.0;
		for(OrderItems orderItem :orderItemsEntityList)
		{
			bill = bill + orderItem.getQty() * orderItem.getDish().getPrice();
		}
		return bill.intValue();
	}
}
