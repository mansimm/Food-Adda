package com.project.service;

import java.util.List;

import com.project.entity.Orders;
import com.project.exception.UserServiceException;
import com.project.model.LoginCredentialsDto;
import com.project.model.OrderItemsDto;
import com.project.model.UsersDto;

public interface CustomerService {
	
	public Orders placeOrder(List<OrderItemsDto> orderItemsList,String contactNumber) throws UserServiceException;

}
