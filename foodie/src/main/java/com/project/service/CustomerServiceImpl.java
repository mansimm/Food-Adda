package com.project.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.project.entity.Dish;
import com.project.entity.OrderItems;
import com.project.entity.Orders;
import com.project.entity.Restaurant;
import com.project.entity.Roles;
import com.project.entity.UserAddress;
import com.project.entity.Users;
import com.project.exception.UserServiceException;
import com.project.model.DishDto;
import com.project.model.LoginCredentialsDto;
import com.project.model.OrderItemsDto;
import com.project.model.OrderStatus;
import com.project.model.OrdersDto;
import com.project.model.RestaurantDto;
import com.project.model.RestaurantTransactionDto;
import com.project.model.Role;
import com.project.model.UserAddressDto;
import com.project.model.UsersDto;
import com.project.model.ViewOrdersDto;
import com.project.repository.UserRepo;
import com.project.repository.AddressRepo;
import com.project.repository.DishRepo;
import com.project.repository.RestaurantRepo;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	DishRepo dishRepo;
	@Autowired
	RestaurantRepo restaurantRepo;
	@Autowired
	AddressRepo addressRepo;
	@Autowired
	Environment environment;
	
	@Override
	public Orders placeOrder(List<OrderItemsDto> orderItemsList,String contactNumber,Integer restaurantId) throws UserServiceException
	{
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(()->new UserServiceException("orderService.NO_USER_FOUND"));
		
		if(orderItemsList==null || orderItemsList.isEmpty())
		{
			throw  new UserServiceException("orderService.NO_ORDER_FOUND");
		}
		List<OrderItems> orderItemsEntityList = new ArrayList<OrderItems>();
		for(OrderItemsDto x:orderItemsList)
		{
			OrderItems orderItem = new OrderItems();
			orderItem.setQty(x.getQty());
			
			//check if dish present 
			Optional<Dish> opDish = dishRepo.findById(x.getDish().getDishId());
			Dish addDish = opDish.orElseThrow(()->new UserServiceException("CustomerService.Dish_NOT_FOUND"));
			orderItem.setDish(addDish);
			
			orderItemsEntityList.add(orderItem);
		}
		
		Orders Orders = new Orders();
		Orders.setOrderItemsList(orderItemsEntityList);
		Orders.setOrderStatus(OrderStatus.ACTIVE.toString());
		Orders.setOrderBill(calculateOrderBill(orderItemsEntityList));
		Restaurant r = new Restaurant();
		r = restaurantRepo.findById(restaurantId).orElseThrow(()-> new UserServiceException("CustomerService.RESTAURANT_NOT_FOUND"));
		Orders.setRestaurant(r);
		
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
	@Override
	public List<ViewOrdersDto> viewOrder(String contactNumber) throws UserServiceException
	{		
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(()->new UserServiceException("orderService.NO_USER_FOUND"));
		
		if(user.getOrdersList()==null || user.getOrdersList().isEmpty())
		{
			throw  new UserServiceException("orderService.NO_ORDER_FOUND");
		}
		List<ViewOrdersDto> viewOrdersList;
		List<Orders> ordersEntityList = user.getOrdersList();
		viewOrdersList = ordersEntityList.stream().map(x->{
			ViewOrdersDto viewOrder = new ViewOrdersDto();
			viewOrder.setOrderId(x.getOrderId());
			viewOrder.setTotalPrice(x.getOrderBill());
			
			List<OrderItems> orderItems = x.getOrderItemsList();
			List<OrderItemsDto> orderItemsDtoList = orderItems.stream().map(y->{
				OrderItemsDto orderItemsDto = new OrderItemsDto();
				orderItemsDto.setQty(y.getQty());
				DishDto dish = new DishDto();
				dish.setDishName(y.getDish().getDishName());
				dish.setPrice(y.getDish().getPrice());
				orderItemsDto.setDish(dish);
				return orderItemsDto;
			}).collect(Collectors.toList());
			viewOrder.setOrderItems(orderItemsDtoList);
			if(x.getRestaurant()!=null)
			{
				viewOrder.setRestaurantName(x.getRestaurant().getRestaurantName());
			}
			return viewOrder;
		}).collect(Collectors.toList());
		return viewOrdersList;
	}
	
	@Override
	public List<RestaurantDto>  viewNearbyRestaurant(String contactNumber,String area) throws UserServiceException
	{
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(()->new UserServiceException("orderService.NO_USER_FOUND"));
		
		if(user.getRoles()==null|| user.getRoles().isEmpty())
		{
			throw new UserServiceException("orderService.Invalid_USER_ROLE");
		}
		Boolean flag = false;
		for(Roles role: user.getRoles())
		{
			if(role.getRoleType().equals(Role.CUSTOMER))
			{
				flag=true;
			}
		}
		if(flag==false)
		{
			throw new UserServiceException("orderService.Invalid_USER_ROLE");
		}
		//find by default ie. only one address
		if(area.equals("noarea"))
		{
			area = user.getAddressList().get(0).getArea();
		}
		//else find by given area
		List<Restaurant> rest = restaurantRepo.getAllRestaurantByArea(area);
		if(rest == null || rest.isEmpty())
		{
			throw new UserServiceException("UserService.RESTAURANT_NOT_FOUND");
		}
		List<RestaurantDto> restDto;
		restDto = rest.stream().map(x->{
			RestaurantDto rd = new RestaurantDto();
			rd.setAddressLine1(x.getAddressLine1());
			rd.setApprovalStatus(x.getApprovalStatus());
			rd.setArea(x.getArea());
			rd.setAvgRating(x.getAvgRating());
			rd.setCity(x.getCity());
			rd.setPincode(x.getPincode());
			List<DishDto> dd = x.getDishes().stream().map(y->{
				DishDto d = new DishDto();
				d.setAvgRating(y.getAvgRating());
				d.setDishCuisine(y.getDishCuisine());
				d.setDishDescription(y.getDishDescription());
				d.setDishId(y.getDishId());
				d.setDishName(y.getDishName());
				d.setDishType(y.getDishType());
				d.setImageUrl(y.getImageUrl());
				d.setPrice(y.getPrice());
				d.setSpeciality(y.getSpeciality());
				return d;
			}).collect(Collectors.toList());
			
			rd.setDishes(dd);
			String[] photos=x.getPhotoUrls().split("-");
			rd.setPhotoUrls(Arrays.asList(photos));
			rd.setResState(x.getResState());
			rd.setRestaurantContact(x.getRestaurantContact());
			rd.setRestaurantId(x.getRestaurantId());
			rd.setRestaurantName(x.getRestaurantName());
			rd.setRestaurantType(x.getRestaurantType());
			
			RestaurantTransactionDto tr = new RestaurantTransactionDto();
			tr.setRestaurantApproxCost(x.getTransaction().getRestaurantApproxCost());
			tr.setRestaurantOrderCounter(x.getTransaction().getRestaurantOrderCounter());
			tr.setRestaurantStatus(x.getTransaction().getRestaurantStatus());
			tr.setRestaurantTransactionId(x.getTransaction().getRestaurantTransactionId());
			
			rd.setTransaction(tr);
			return rd;
		}).collect(Collectors.toList());
		return restDto;
	}
	@Override
	public List<UserAddressDto> viewAllAddresses(String contactNumber) throws UserServiceException
	{
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(()->new UserServiceException("orderService.NO_USER_FOUND"));
		
		if(user.getRoles()==null|| user.getRoles().isEmpty())
		{
			throw new UserServiceException("orderService.Invalid_USER_ROLE");
		}
		Boolean flag = false;
		for(Roles role: user.getRoles())
		{
			if(role.getRoleType().equals(Role.CUSTOMER))
			{
				flag=true;
			}
		}
		if(flag==false)
		{
			throw new UserServiceException("orderService.Invalid_USER_ROLE");
		}
		
		List<UserAddressDto> userAddressDtoList;
		userAddressDtoList = user.getAddressList().stream().map(x->{
			UserAddressDto userAddressDto = new UserAddressDto();
			
			userAddressDto.setAddressLine1(x.getAddressLine1());
			userAddressDto.setAddressLine2(x.getAddressLine2());
			userAddressDto.setArea(x.getArea());
			userAddressDto.setCity(x.getCity());
			userAddressDto.setPincode(x.getPincode());
			userAddressDto.setUserAddressId(x.getUserAddressId());
			userAddressDto.setUserAddressName(x.getUserAddressName());
			userAddressDto.setUserState(x.getUserState());
			return userAddressDto;
		}).collect(Collectors.toList());
		return userAddressDtoList;
		
	}
	@Override
	public String addNewAddress(UserAddressDto addressDto,String contactNumber) throws UserServiceException
	{
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(()->new UserServiceException("orderService.NO_USER_FOUND"));
		
		if(user.getRoles()==null|| user.getRoles().isEmpty())
		{
			throw new UserServiceException("orderService.Invalid_USER_ROLE");
		}
		Boolean flag = false;
		for(Roles role: user.getRoles())
		{
			if(role.getRoleType().equals(Role.CUSTOMER))
			{
				flag=true;
			}
		}
		if(flag==false)
		{
			throw new UserServiceException("orderService.Invalid_USER_ROLE");
		}
		Boolean duplicate = false;
		if(user.getAddressList()!=null || !user.getAddressList().isEmpty())
		{
			for(UserAddress userAddress : user.getAddressList())
			{
				if(userAddress.getUserAddressName().equals(addressDto.getUserAddressName()))
				{
					duplicate = true;
					break;
				}
			}
			if(duplicate)
			{
				throw new UserServiceException("UserService.ADDRESS_NAME_ALREADY_EXISTS");
			}	
		}
		if(user.getAddressList()==null)
		{
			List<UserAddress> list=new ArrayList();
			user.setAddressList(list);
		}
		UserAddress add = new UserAddress();
		add.setAddressLine1(addressDto.getAddressLine1());
		add.setAddressLine2(addressDto.getAddressLine2());
		add.setArea(addressDto.getArea());
		add.setCity(addressDto.getCity());
		add.setPincode(addressDto.getPincode());
		add.setUserAddressName(addressDto.getUserAddressName());
		add.setUserState(addressDto.getUserState());
		
		user.getAddressList().add(add);
		userRepo.save(user);
		return environment.getProperty("UserService.ADDRESS_ADDED_SUCCESS");
	}
	
	@Override
	public String updateAddress(UserAddressDto addressDto,String contactNumber) throws UserServiceException
	{
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(()->new UserServiceException("orderService.NO_USER_FOUND"));
		
		if(user.getRoles()==null|| user.getRoles().isEmpty())
		{
			throw new UserServiceException("orderService.Invalid_USER_ROLE");
		}
		if(user.getAddressList()==null|| user.getAddressList().isEmpty())
		{
			throw new UserServiceException("UserService.ADDRESS_NOT_FOUND");
		}
		Boolean flag = false;
		for(Roles role: user.getRoles())
		{
			if(role.getRoleType().equals(Role.CUSTOMER))
			{
				flag=true;
			}
		}
		if(flag==false)
		{
			throw new UserServiceException("orderService.Invalid_USER_ROLE");
		}
		Boolean present = false;
		for(UserAddress userAddress : user.getAddressList())
		{
			if(userAddress.getUserAddressId().equals(addressDto.getUserAddressId()))
			{
				present = true;
				break;
			}
		}
		if(present==false)
		{
			throw new UserServiceException("UserService.ADDRESS_NOT_FOUND");
		}
		List<UserAddress> list = new ArrayList();
		for(UserAddress userAddress : user.getAddressList())
		{
			if(userAddress.getUserAddressId().equals(addressDto.getUserAddressId()))
			{
				userAddress.setUserAddressId(addressDto.getUserAddressId());
				userAddress.setAddressLine1(addressDto.getAddressLine1());
				userAddress.setAddressLine2(addressDto.getAddressLine2());
				userAddress.setArea(addressDto.getArea());
				userAddress.setCity(addressDto.getCity());
				userAddress.setPincode(addressDto.getPincode());
				userAddress.setUserAddressName(addressDto.getUserAddressName());
				userAddress.setUserState(addressDto.getUserState());
				list.add(userAddress);
				
			}
			else
			{
				list.add(userAddress);
			}
		}
		user.setAddressList(list);
		userRepo.save(user);
		return environment.getProperty("UserService.UPDATE_ADDRESS");
		
	}
	
	@Override
	public String deleteAddress(UserAddressDto addressDto,String contactNumber) throws UserServiceException
	{
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(()->new UserServiceException("orderService.NO_USER_FOUND"));
		
		if(user.getRoles()==null|| user.getRoles().isEmpty())
		{
			throw new UserServiceException("orderService.Invalid_USER_ROLE");
		}
		if(user.getAddressList()==null|| user.getAddressList().isEmpty())
		{
			throw new UserServiceException("UserService.ADDRESS_NOT_FOUND");
		}
		Boolean flag = false;
		for(Roles role: user.getRoles())
		{
			if(role.getRoleType().equals(Role.CUSTOMER))
			{
				flag=true;
			}
		}
		if(flag==false)
		{
			throw new UserServiceException("orderService.Invalid_USER_ROLE");
		}
		Boolean present = false;
		for(UserAddress userAddress : user.getAddressList())
		{
			if(userAddress.getUserAddressId().equals(addressDto.getUserAddressId()))
			{
				present = true;
				break;
			}
		}
		if(present==false)
		{
			throw new UserServiceException("UserService.ADDRESS_NOT_FOUND");
		}
		Integer id = 0;
		List<UserAddress> list = new ArrayList();
		for(UserAddress userAddress : user.getAddressList())
		{
			if(userAddress.getUserAddressId().equals(addressDto.getUserAddressId()))
			{
				//don't add	
				id=addressDto.getUserAddressId();
			}
			else
			{
				list.add(userAddress);
			}
		}
		user.setAddressList(list);
		addressRepo.deleteById(id);
		userRepo.save(user);
		return environment.getProperty("UserService.DELETE_ADDRESS_success");
	}

	
}
