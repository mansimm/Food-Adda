package com.project.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Restaurant;
import com.project.entity.Roles;
import com.project.entity.Users;
import com.project.exception.RestaurantNotFoundException;
import com.project.exception.UserServiceException;
import com.project.model.RestaurantDto;
import com.project.model.Role;
import com.project.repository.RestaurantRepo;
import com.project.repository.UserRepo;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	RestaurantRepo restaurantRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	SearchServiceImpl searchService;
	
	@Override
	public List<RestaurantDto> viewAllRegistrationRequests(String contactNumber) throws RestaurantNotFoundException, UserServiceException
	{
		Optional<Users> op = userRepo.findByContactNumber(contactNumber);
		Users user = op.orElseThrow(()->new UserServiceException("AdminService.NO_USER_FOUND"));
		
		if(user.getRoles()==null|| user.getRoles().isEmpty())
		{
			throw new UserServiceException("AdminService.Invalid_USER_ROLE");
		}
		Boolean flag = false;
		for(Roles role: user.getRoles())
		{
			if(role.getRoleType().equals(Role.ADMIN))
			{
				flag=true;
			}
		}
		if(flag==false)
		{
			throw new UserServiceException("AdminService.Invalid_USER_ROLE");
		}
		List<RestaurantDto> rest = searchService.getAllRestaurant();
		return rest;
	}
}
