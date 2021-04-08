package com.project.service;

import java.util.List;

import com.project.entity.Orders;
import com.project.exception.UserServiceException;
import com.project.model.LoginCredentialsDto;
import com.project.model.OrderItemsDto;
import com.project.model.OrdersDto;
import com.project.model.RestaurantDto;
import com.project.model.UserAddressDto;
import com.project.model.UsersDto;
import com.project.model.ViewOrdersDto;

public interface CustomerService {

	public Orders placeOrder(List<OrderItemsDto> orderItemsList, String contactNumber,Integer restaurantId) throws UserServiceException;

	public List<ViewOrdersDto> viewOrder(String contactNumber) throws UserServiceException;

	public List<RestaurantDto> viewNearbyRestaurant(String contactNumber, String area) throws UserServiceException;

	public List<UserAddressDto> viewAllAddresses(String contactNumber) throws UserServiceException;

	public String addNewAddress(UserAddressDto addressDto, String contactNumber) throws UserServiceException;

	public String updateAddress(UserAddressDto addressDto, String contactNumber) throws UserServiceException;

	public String deleteAddress(UserAddressDto addressDto, String contactNumber) throws UserServiceException;

}
