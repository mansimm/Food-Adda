package com.project.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//import org.unitils.reflectionassert.ReflectionAssert;

import com.project.service.SearchServiceImpl;
import com.project.entity.Restaurant;
import com.project.model.DishDto;
import com.project.model.RestaurantDto;
import com.project.model.RestaurantTransactionDto;
import com.project.repository.SearchRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {
	
	@Mock
	SearchRepo searchRepo;
	
	@InjectMocks
	SearchServiceImpl searchService;
	
//	@Rule
//	public ExpectedException  ee = ExpectedException.none();

		
//	@Test
//	public void invalidGetAllRestaurantTest()throws Exception{
//		ee.expect(Exception.class);
//		ee.expectMessage("SearchService.NO_RESTAURANTS_FOUND");
//		Mockito.when(searchRepo.findAll()).thenReturn(new ArrayList<Restaurant>());
//		searchService.getAllRestaurant();
//	}
	
	
	@Test
	public void validGetAllRestaurantTest()throws Exception{
		List<RestaurantDto> restaurants = new ArrayList<>();
		RestaurantDto expected = new RestaurantDto();
		RestaurantTransactionDto t=new RestaurantTransactionDto();
		 List<String> list1=new ArrayList<>();
		 List<DishDto> dishList=new ArrayList<>();
		 DishDto d1=new DishDto();
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
		 DishDto d2=new DishDto();
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
		 DishDto d3=new DishDto();
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
		 DishDto d4=new DishDto();
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
		 expected.setPhotoUrls(list1);
		 expected.setDishes(dishList);
		restaurants.add(expected);
		Mockito.when(searchRepo.findAll()).thenReturn((Iterable<Restaurant>)restaurants.stream());
		List<RestaurantDto> actual = searchService.getAllRestaurant();
		Assertions.assertEquals(restaurants, actual);
	}
}
