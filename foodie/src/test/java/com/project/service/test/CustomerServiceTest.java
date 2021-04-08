package com.project.service.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.entity.Dish;
import com.project.entity.OrderItems;
import com.project.entity.Orders;
import com.project.entity.Restaurant;
import com.project.entity.Roles;
import com.project.entity.Users;
import com.project.exception.UserServiceException;
import com.project.model.DishDto;
import com.project.model.OrderItemsDto;
import com.project.model.Role;
import com.project.repository.DishRepo;
import com.project.repository.RestaurantRepo;
import com.project.repository.UserRepo;
import com.project.service.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceTest {

	@Mock
	UserRepo userRepo;
	@Mock
	DishRepo dishRepo;
	@Mock
	RestaurantRepo restaurantRepo;
	@InjectMocks
	CustomerServiceImpl customerService = new CustomerServiceImpl();
	
	@Test
	public void placeOrderUserNotFoundException()
	{
		List<OrderItemsDto> orderItemsList= new ArrayList();
		String contactNumber = "9234567890";
		Integer restaurantId = 1;
		
		Mockito.when(userRepo.findByContactNumber(contactNumber)).thenReturn(Optional.ofNullable(null));
		Mockito.when(dishRepo.findById(1111)).thenReturn(Optional.ofNullable(null));
		Mockito.when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.ofNullable(null));

		
		Exception e = Assertions.assertThrows(UserServiceException.class, ()->customerService.placeOrder(orderItemsList, contactNumber,restaurantId));
		Assertions.assertEquals("orderService.NO_USER_FOUND", e.getMessage());
	}
	
	@Test
	public void placeOrderNoOrderFoundException()
	{
		List<OrderItemsDto> orderItemsList= new ArrayList();
		String contactNumber = "9234567890";
		
		Users userEntity = new Users();
		userEntity.setContactNumber("9876543210");
		userEntity.setEmailId("mansi@gmail.com");
		userEntity.setUserName("mansi");
		userEntity.setPassword("Mansi@13");
		List<Roles> rolesEntity = new ArrayList<Roles>();
		Roles roleEntity = new Roles();
		roleEntity.setRoleType(Role.VENDOR);
		roleEntity.setRoleId(1);

		rolesEntity.add(roleEntity);
		userEntity.setRoles(rolesEntity);
		Integer restaurantId=1;
		
		Mockito.when(userRepo.findByContactNumber(contactNumber)).thenReturn(Optional.ofNullable(userEntity));
		Mockito.when(dishRepo.findById(1111)).thenReturn(Optional.ofNullable(null));
		Mockito.when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.ofNullable(null));

		Exception e = Assertions.assertThrows(UserServiceException.class, ()->customerService.placeOrder(orderItemsList, contactNumber,restaurantId));
		Assertions.assertEquals("orderService.NO_ORDER_FOUND", e.getMessage());
	}
	
	@Test
	public void calculateOrderBillValid()
	{
		Integer bill= 0;
		List<OrderItems> orderItemsEntityList = new ArrayList();
		OrderItems item = new OrderItems();
		item.setOrderItemsId(1);
		Dish dish = new Dish();
		dish.setPrice(100.0);
		item.setDish(dish);
		item.setQty(5);
		orderItemsEntityList.add(item);
		
		bill = customerService.calculateOrderBill(orderItemsEntityList);
		Assertions.assertEquals(500, bill);
		
	}
	
	@Test
	public void placeOrderDishNotFound() throws UserServiceException
	{
		List<OrderItemsDto> orderItemsList= new ArrayList();
		String contactNumber = "9234567890";
		OrderItemsDto orderItemsDto = new OrderItemsDto();
		orderItemsDto.setOrderItemsId(101);
		orderItemsDto.setQty(3);
		DishDto dish = new DishDto();
		dish.setDishId(1111);
		orderItemsDto.setDish(dish);
		orderItemsList.add(orderItemsDto);
		
		Users userEntity = new Users();
		userEntity.setContactNumber("9876543210");
		userEntity.setEmailId("mansi@gmail.com");
		userEntity.setUserName("mansi");
		userEntity.setPassword("Mansi@13");
		List<Roles> rolesEntity = new ArrayList<Roles>();
		Roles roleEntity = new Roles();
		roleEntity.setRoleType(Role.VENDOR);
		roleEntity.setRoleId(1);

		rolesEntity.add(roleEntity);
		userEntity.setRoles(rolesEntity);
		Integer restaurantId =1;
				
		Restaurant r = new Restaurant();
		r.setRestaurantName("KFC");
		
		Mockito.when(userRepo.findByContactNumber(contactNumber)).thenReturn(Optional.ofNullable(userEntity));
		Mockito.when(dishRepo.findById(1111)).thenReturn(Optional.ofNullable(null));
		Mockito.when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.ofNullable(r));

		Exception e = Assertions.assertThrows(UserServiceException.class, ()->customerService.placeOrder(orderItemsList, contactNumber,restaurantId));
		Assertions.assertEquals("CustomerService.Dish_NOT_FOUND", e.getMessage());
	}
	@Test
	public void placeOrderValid() throws UserServiceException
	{
		List<OrderItemsDto> orderItemsList= new ArrayList();
		String contactNumber = "9234567890";
		OrderItemsDto orderItemsDto = new OrderItemsDto();
		orderItemsDto.setOrderItemsId(101);
		orderItemsDto.setQty(3);
		DishDto dish = new DishDto();
		dish.setDishId(1111);

		orderItemsDto.setDish(dish);
		orderItemsList.add(orderItemsDto);
		
		Users userEntity = new Users();
		userEntity.setContactNumber("9876543210");
		userEntity.setEmailId("mansi@gmail.com");
		userEntity.setUserName("mansi");
		userEntity.setPassword("Mansi@13");
		List<Roles> rolesEntity = new ArrayList<Roles>();
		Roles roleEntity = new Roles();
		roleEntity.setRoleType(Role.VENDOR);
		roleEntity.setRoleId(1);

		rolesEntity.add(roleEntity);
		userEntity.setRoles(rolesEntity);
		Integer restaurantId =1;
		
		Dish dishEntity = new Dish();
		dishEntity.setDishId(1111);
		dishEntity.setAvgRating(5.0);
		dishEntity.setDishCuisine("Burger");
		dishEntity.setDishDescription("Spicy and chrunchy chicken tikki in soft bun with fresh lettuce and mustard sauce");
		dishEntity.setDishName("Chicken Burger");
		dishEntity.setDishType("Nonveg");
		dishEntity.setImageUrl("url");
		dishEntity.setPrice(100.0);
		dishEntity.setSpeciality("special");
		orderItemsDto.setDish(dish);
		orderItemsList.add(orderItemsDto);
		
		Restaurant r = new Restaurant();
		r.setRestaurantName("KFC");
		
		Mockito.when(userRepo.findByContactNumber(contactNumber)).thenReturn(Optional.ofNullable(userEntity));
		Mockito.when(dishRepo.findById(1111)).thenReturn(Optional.ofNullable(dishEntity));
		Mockito.when(restaurantRepo.findById(restaurantId)).thenReturn(Optional.ofNullable(r));

		Orders order = customerService.placeOrder(orderItemsList, contactNumber,restaurantId);
		Assertions.assertNotNull(order);
	}
}
