package com.project.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.unitils.reflectionassert.ReflectionAssert;

import com.project.entity.Dish;
import com.project.entity.Restaurant;
import com.project.entity.RestaurantTransaction;
import com.project.exception.RestaurantNotFoundException;
import com.project.model.DishDto;
import com.project.model.RestaurantDto;
import com.project.model.RestaurantTransactionDto;
import com.project.repository.RestaurantRepo;
import com.project.service.SearchServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {
	
	@Mock
	RestaurantRepo searchRepo;
	
	@InjectMocks
	SearchServiceImpl searchService;
	
	@Rule
	public ExpectedException  ee = ExpectedException.none();

		
	@Test
	public void invalidGetAllRestaurant() throws Exception{
		Mockito.when(searchRepo.findAll()).thenReturn(new ArrayList<Restaurant>());
		Exception e = Assertions.assertThrows(RestaurantNotFoundException.class, 
						()->searchService.getAllRestaurant());
		Assertions.assertEquals("SearchService.NO_RESTAURANTS_FOUND", e.getMessage());
	}
	
	
	@Test
	public void validviewAllRestaurants()throws Exception{
		List<Restaurant> restaurants = new ArrayList<>();
		Restaurant expected = new Restaurant();
		RestaurantTransaction t=new RestaurantTransaction();
		 List<String> list1=new ArrayList<>();
		 List<Dish> dishList=new ArrayList<>();
		 Dish d1=new Dish();
		 d1.setAvgRating(4.5);
		 d1.setDishCuisine("Burger");
		 d1.setDishDescription("Spicy and chrunchy chicken tikki in soft bun with fresh lettuce and mustard sauce");
		 d1.setDishId(1001);
		 d1.setDishName("Chicken Burger");
		 d1.setDishType("Nonveg");
		 d1.setImageUrl("assets/chicken_burger.jpg");
		 d1.setPrice(150.0);
		 d1.setSpeciality("Chef Special");
		 dishList.add(d1);
		 Dish d2=new Dish();
		 d2.setAvgRating(4.2);
		 d2.setDishCuisine("Burger");
		 d2.setDishDescription("Spicy and chrunchy chicken tikki with soft bun with fresh lettuce and extra cheese");
		 d2.setDishId(1002);
		 d2.setDishName("Chicken Cheese Burger");
		 d2.setDishType("Nonveg");
		 d2.setImageUrl("assets/chicken_cheese_burger.jpg");
		 d2.setPrice(200.0);
		 d2.setSpeciality("Chef Special");
		 dishList.add(d2);
		 Dish d3=new Dish();
		 d3.setAvgRating(4.6);
		 d3.setDishCuisine("chicken");
		 d3.setDishDescription("Spicy and chrunchy chicken wings");
		 d3.setDishId(1003);
		 d3.setDishName("Chicken Wings");
		 d3.setDishType("Nonveg");
		 d3.setImageUrl("assets/chicken_wings.jpg");
		 d3.setPrice(550.0);
		 d3.setSpeciality("Chef Special");
		 dishList.add(d3);
		 Dish d4=new Dish();
		 d4.setAvgRating(4.5);
		 d4.setDishCuisine("Fries");
		 d4.setDishDescription("Classic crunchy and tasty french fries");
		 d4.setDishId(1012);
		 d4.setDishName("French Fries");
		 d4.setDishType("Veg");
		 d4.setImageUrl("assets/french_fries.jpg");
		 d4.setPrice(200.0);
		 d4.setSpeciality("Chef Special");
		 dishList.add(d4);
		 list1.add("assets/kfca1.jpg");
		 list1.add("assets/kfca2.jpg");
		 list1.add("assets/kfca3.jpg");
		 t.setRestaurantApproxCost(500);
		 t.setRestaurantOrderCounter(2);
		 t.setRestaurantStatus(true);
		 t.setRestaurantTransactionId(1);
		 expected.setRestaurantType("Nonveg");
		 expected.setRestaurantName("KFC");
		 expected.setRestaurantId(1);
		 expected.setRestaurantContact("9823414141");
		 expected.setResState("Maharashtra");
		 expected.setPincode(411041);
		 expected.setCity("Pune");
		 expected.setAddressLine1("23, Shastri Nagar");
		 expected.setAvgRating(4.5);
		 expected.setArea("Baner");
		 expected.setApprovalStatus("Accepted");
		 expected.setPhotoUrls("assets/kfca1.jpg"+"-"+"assets/kfca2.jpg"+"-"+"assets/kfca3.jpg");
		 expected.setDishes(dishList);
		 expected.setTransaction(t);
		restaurants.add(expected);
		Mockito.when(searchRepo.findAll()).thenReturn(restaurants);
		List<RestaurantDto> actual = searchService.getAllRestaurant();
		
		List<RestaurantDto> restaurantsDto = new ArrayList<>();
		RestaurantDto expectedDto = new RestaurantDto();
		RestaurantTransactionDto tDto=new RestaurantTransactionDto();
		 List<String> list1Dto=new ArrayList<>();
		 List<DishDto> dishListDto=new ArrayList<>();
		 DishDto d1Dto=new DishDto();
		 d1Dto.setAvgRating(4.5);
		 d1Dto.setDishCuisine("Burger");
		 d1Dto.setDishDescription("Spicy and chrunchy chicken tikki in soft bun with fresh lettuce and mustard sauce");
		 d1Dto.setDishId(1001);
		 d1Dto.setDishName("Chicken Burger");
		 d1Dto.setDishType("Nonveg");
		 d1Dto.setImageUrl("assets/chicken_burger.jpg");
		 d1Dto.setPrice(150.0);
		 d1Dto.setSpeciality("Chef Special");
		 dishListDto.add(d1Dto);
		 DishDto d2Dto=new DishDto();
		 d2Dto.setAvgRating(4.2);
		 d2Dto.setDishCuisine("Burger");
		 d2Dto.setDishDescription("Spicy and chrunchy chicken tikki with soft bun with fresh lettuce and extra cheese");
		 d2Dto.setDishId(1002);
		 d2Dto.setDishName("Chicken Cheese Burger");
		 d2Dto.setDishType("Nonveg");
		 d2Dto.setImageUrl("assets/chicken_cheese_burger.jpg");
		 d2Dto.setPrice(200.0);
		 d2Dto.setSpeciality("Chef Special");
		 dishListDto.add(d2Dto);
		 DishDto d3Dto=new DishDto();
		 d3Dto.setAvgRating(4.6);
		 d3Dto.setDishCuisine("chicken");
		 d3Dto.setDishDescription("Spicy and chrunchy chicken wings");
		 d3Dto.setDishId(1003);
		 d3Dto.setDishName("Chicken Wings");
		 d3Dto.setDishType("Nonveg");
		 d3Dto.setImageUrl("assets/chicken_wings.jpg");
		 d3Dto.setPrice(550.0);
		 d3Dto.setSpeciality("Chef Special");
		 dishListDto.add(d3Dto);
		 DishDto d4Dto=new DishDto();
		 d4Dto.setAvgRating(4.5);
		 d4Dto.setDishCuisine("Fries");
		 d4Dto.setDishDescription("Classic crunchy and tasty french fries");
		 d4Dto.setDishId(1012);
		 d4Dto.setDishName("French Fries");
		 d4Dto.setDishType("Veg");
		 d4Dto.setImageUrl("assets/french_fries.jpg");
		 d4Dto.setPrice(200.0);
		 d4Dto.setSpeciality("Chef Special");
		 dishListDto.add(d4Dto);
		 list1Dto.add("assets/kfca1.jpg");
		 list1Dto.add("assets/kfca2.jpg");
		 list1Dto.add("assets/kfca3.jpg");
		 tDto.setRestaurantApproxCost(500);
		 tDto.setRestaurantOrderCounter(2);
		 tDto.setRestaurantStatus(true);
		 tDto.setRestaurantTransactionId(1);
		 expectedDto.setRestaurantType("Nonveg");
		 expectedDto.setRestaurantName("KFC");
		 expectedDto.setRestaurantId(1);
		 expectedDto.setRestaurantContact("9823414141");
		 expectedDto.setResState("Maharashtra");
		 expectedDto.setPincode(411041);
		 expectedDto.setCity("Pune");
		 expectedDto.setAddressLine1("23, Shastri Nagar");
		 expectedDto.setAvgRating(4.5);
		 expectedDto.setArea("Baner");
		 expectedDto.setApprovalStatus("Accepted");
		 expectedDto.setPhotoUrls(list1Dto);
		 expectedDto.setDishes(dishListDto);
		 expectedDto.setTransaction(tDto);
		restaurantsDto.add(expectedDto);
		ReflectionAssert.assertLenientEquals(restaurantsDto,actual);
	}
}
