package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.project.entity.Dish;
import com.project.entity.Restaurant;
import com.project.entity.RestaurantTransaction;
import com.project.entity.Roles;
import com.project.entity.Users;
import com.project.exception.RestaurantNotFoundException;
import com.project.exception.UserServiceException;
import com.project.exception.VendorServiceException;
import com.project.model.ApprovalStatus;
import com.project.model.DishDto;
import com.project.model.RestaurantDto;
import com.project.model.Role;
import com.project.repository.DishRepo;
import com.project.repository.RestaurantRepo;
import com.project.repository.UserRepo;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {

	@Autowired
	UserRepo userRepo;
	@Autowired
	RestaurantRepo restaurantRepo;
	@Autowired
	DishRepo dishRepo;
	@Autowired
	Environment environment;
	@Autowired
	SearchServiceImpl searchService;

	@Override
	public String registerRestaurant(RestaurantDto restaurantDto, String contactNumber)
			throws UserServiceException, RestaurantNotFoundException, VendorServiceException {
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(() -> new UserServiceException("orderService.NO_USER_FOUND"));

		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new UserServiceException("vendorService.Invalid_USER_ROLE");
		}
		Boolean flag = false;
		for (Roles role : user.getRoles()) {
			if (role.getRoleType().equals(Role.VENDOR)) {
				flag = true;
			}
		}
		if (flag == false) {
			throw new UserServiceException("vendorService.Invalid_USER_ROLE");
		}
		// to get all the users
		Iterable<Users> usersIt = userRepo.findAll();
		List<Users> users = new ArrayList();
		usersIt.forEach(x -> {
			users.add(x);
		});
		if (users.isEmpty()) {
			throw new UserServiceException("orderService.NO_USER_FOUND");
		}

		// check if restaurant is already registered or not
		for (Users u : users) {
			if (u.getUserId() == user.getUserId()) {
				List<Restaurant> rest = u.getRestaurants();
				for (Restaurant r : rest) {
					if (r.getRestaurantName().equalsIgnoreCase(restaurantDto.getRestaurantName())
							&& restaurantDto.getRestaurantContact().equals(r.getRestaurantContact())) {
						throw new VendorServiceException("vendorService.RESTAURANT_ALREADY_REGISTERED_BY_YOU");
					}
				}
			} else {
				List<Restaurant> rest = u.getRestaurants();
				for (Restaurant r : rest) {
					if (r.getRestaurantName().equalsIgnoreCase(restaurantDto.getRestaurantName())) {
						throw new VendorServiceException("vendorService.RESTAURANT_ALREADY_REGISTERED_BY_OTHER_VENDOR");
					}
				}
			}
		}

		Restaurant newRest = new Restaurant();
		newRest.setAddressLine1(restaurantDto.getAddressLine1());
		newRest.setApprovalStatus(ApprovalStatus.Pending.toString());
		newRest.setArea(restaurantDto.getArea());
		newRest.setAvgRating(restaurantDto.getAvgRating());
		newRest.setCity(restaurantDto.getCity());

		// cannot add dishes until restaurant registration is not approved

//		if (restaurantDto.getDishes() != null) {
//			List<Dish> dishes = restaurantDto.getDishes().stream().map(x -> {
//				Dish d = new Dish();
//				d.setAvgRating(x.getAvgRating());
//				d.setDishCuisine(x.getDishCuisine());
//				d.setDishDescription(x.getDishDescription());
//				d.setDishName(x.getDishName());
//				d.setDishType(x.getDishType());
//				d.setImageUrl(x.getImageUrl());
//				d.setPrice(x.getPrice());
//				d.setSpeciality(x.getSpeciality());
//				return d;
//			}).collect(Collectors.toList());
//			newRest.setDishes(dishes);
//		}
		String arr = "";
		for (String url : restaurantDto.getPhotoUrls()) {
			arr = arr + url + "-";
		}
		newRest.setPhotoUrls(arr.substring(0, arr.length() - 2));
		newRest.setPincode(restaurantDto.getPincode());
		newRest.setResState(restaurantDto.getResState());
		newRest.setRestaurantContact(restaurantDto.getRestaurantContact());
		newRest.setRestaurantName(restaurantDto.getRestaurantName());
		newRest.setRestaurantType(restaurantDto.getRestaurantType());

		if (restaurantDto.getTransaction() != null) {
			RestaurantTransaction t = new RestaurantTransaction();
			t.setRestaurantApproxCost(restaurantDto.getTransaction().getRestaurantApproxCost());
			t.setRestaurantOrderCounter(restaurantDto.getTransaction().getRestaurantOrderCounter());
			t.setRestaurantStatus(restaurantDto.getTransaction().getRestaurantStatus());
			newRest.setTransaction(t);
		}
		user.getRestaurants().add(newRest);
		userRepo.save(user);

		return environment.getProperty("vendorService.RESTAURANT_REGISTERED_SUCCESS");

	}

	@Override
	public List<RestaurantDto> viewRestaurantAndMenu(String contactNumber)
			throws UserServiceException, RestaurantNotFoundException {
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(() -> new UserServiceException("vendorService.Invalid_USER_ROLE"));

		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new UserServiceException("vendorService.Invalid_USER_ROLE");
		}
		Boolean flag = false;
		for (Roles role : user.getRoles()) {
			if (role.getRoleType().equals(Role.VENDOR)) {
				flag = true;
			}
		}
		if (flag == false) {
			throw new UserServiceException("vendorService.Invalid_USER_ROLE");
		}
		List<RestaurantDto> rest = searchService.getAllRestaurant();
		return rest;
	}

	@Override
	public String addDish(String contactNumber, Integer restaurantId, DishDto dishDto)
			throws UserServiceException, VendorServiceException {
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(() -> new UserServiceException("vendorService.Invalid_USER_ROLE"));

		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new UserServiceException("vendorService.Invalid_USER_ROLE");
		}
		Boolean flag = false;
		for (Roles role : user.getRoles()) {
			if (role.getRoleType().equals(Role.VENDOR)) {
				flag = true;
			}
		}
		if (flag == false) {
			throw new UserServiceException("vendorService.Invalid_USER_ROLE");
		}

		Optional<Restaurant> restOp = restaurantRepo.findById(restaurantId);
		Restaurant rest = restOp.orElseThrow(() -> new VendorServiceException("vendorService.RESTAURANT_NOT_FOUND"));
		//if registration is approved then only vendor can add dish
		if (!rest.getApprovalStatus().equals(ApprovalStatus.Accepted.toString())) {
			throw new VendorServiceException("vendorService.RESTAURANT_REGISTRATION_NOT_APPROVED");
		}
		// to check if dish is already present
		for (Dish d : rest.getDishes()) {
			if (d.getDishName().equalsIgnoreCase(dishDto.getDishName())) {
				throw new VendorServiceException("vendorService.DISH_ALREADY_PRESENT");
			}
		}
		Dish dish = new Dish();
		dish.setAvgRating(2.5);// rating should be 2.5 initial for all restaurants
		dish.setDishCuisine(dishDto.getDishCuisine());
		dish.setDishDescription(dishDto.getDishDescription());
		dish.setDishName(dishDto.getDishName());
		dish.setDishType(dishDto.getDishType());
		dish.setImageUrl(dishDto.getImageUrl());
		dish.setPrice(dishDto.getPrice());
		dish.setSpeciality(dishDto.getSpeciality());

		if (rest.getDishes() == null) {
			List<Dish> dlist = new ArrayList();
			rest.setDishes(dlist);
		}
		rest.getDishes().add(dish);

		return environment.getProperty("vendorService.DISH_ADDED_SUCCESS");
	}

	@Override
	public String updateDish(String contactNumber, Integer restaurantId, DishDto dishDto)
			throws UserServiceException, VendorServiceException {
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(() -> new UserServiceException("vendorService.Invalid_USER_ROLE"));

		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new UserServiceException("vendorService.Invalid_USER_ROLE");
		}
		Boolean flag = false;
		for (Roles role : user.getRoles()) {
			if (role.getRoleType().equals(Role.VENDOR)) {
				flag = true;
			}
		}
		if (flag == false) {
			throw new UserServiceException("vendorService.Invalid_USER_ROLE");
		}

		Optional<Restaurant> restOp = restaurantRepo.findById(restaurantId);
		Restaurant rest = restOp.orElseThrow(() -> new VendorServiceException("vendorService.RESTAURANT_NOT_FOUND"));

		//if registration is approved then only vendor can update
		if (!rest.getApprovalStatus().equals(ApprovalStatus.Accepted.toString())) {
			throw new VendorServiceException("vendorService.RESTAURANT_REGISTRATION_NOT_APPROVED");
		}
		if (rest.getDishes() == null) {
			throw new VendorServiceException("vendorService.DISH_NOT_FOUND");
		}
		// to check if dish is already present
		Optional<Dish> dishOp = dishRepo.findById(dishDto.getDishId());
		Dish dish = dishOp.orElseThrow(()->new VendorServiceException("vendorService.DISH_NOT_FOUND"));
		
		//rating should not be updated
		dish.setDishCuisine(dishDto.getDishCuisine());
		dish.setDishDescription(dishDto.getDishDescription());
		dish.setDishName(dishDto.getDishName());
		dish.setDishType(dishDto.getDishType());
		dish.setImageUrl(dishDto.getImageUrl());
		dish.setPrice(dishDto.getPrice());
		dish.setSpeciality(dishDto.getSpeciality());

		return environment.getProperty("vendorService.DISH_UPDATED_SUCCESS");
	}
	
	@Override
	public String deleteDish(String contactNumber, Integer restaurantId, DishDto dishDto)
			throws UserServiceException, VendorServiceException {
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(() -> new UserServiceException("vendorService.Invalid_USER_ROLE"));

		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new UserServiceException("vendorService.Invalid_USER_ROLE");
		}
		Boolean flag = false;
		for (Roles role : user.getRoles()) {
			if (role.getRoleType().equals(Role.VENDOR)) {
				flag = true;
			}
		}
		if (flag == false) {
			throw new UserServiceException("vendorService.Invalid_USER_ROLE");
		}

		Optional<Restaurant> restOp = restaurantRepo.findById(restaurantId);
		Restaurant rest = restOp.orElseThrow(() -> new VendorServiceException("vendorService.RESTAURANT_NOT_FOUND"));

		//if registration is approved then only vendor can update
		if (!rest.getApprovalStatus().equals(ApprovalStatus.Accepted.toString())) {
			throw new VendorServiceException("vendorService.RESTAURANT_REGISTRATION_NOT_APPROVED");
		}
		if (rest.getDishes() == null) {
			throw new VendorServiceException("vendorService.DISH_NOT_FOUND");
		}
		List<Dish> newDishes = new ArrayList();
		boolean deleted=false;
		for(Dish d:rest.getDishes())
		{
			if(d.getDishId().equals(dishDto.getDishId()))
			{
				//dont add
				deleted=true;
				
			}
			else
			{
				newDishes.add(d);
			}
		}
		rest.setDishes(newDishes);
		if(deleted==false)
		{
			throw new VendorServiceException("vendorService.DISH_NOT_FOUND");
		}
		dishRepo.deleteById(dishDto.getDishId());
		return environment.getProperty("vendorService.DISH_DELETED_SUCCESS");
	}
}
