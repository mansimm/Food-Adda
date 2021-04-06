package com.project.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.project.entity.Restaurant;
import com.project.entity.Roles;
import com.project.entity.Users;
import com.project.exception.AdminServiceException;
import com.project.exception.RestaurantNotFoundException;
import com.project.exception.UserServiceException;
import com.project.model.ApprovalStatus;
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
	@Autowired
	Environment environment;
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
	
	public String accepctRegistrationRequest(RestaurantDto restaurantDto,String contactNumber) throws UserServiceException, RestaurantNotFoundException, AdminServiceException
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
		Optional<Restaurant> opRest =  restaurantRepo.findById(restaurantDto.getRestaurantId());
		Restaurant rest = opRest.orElseThrow(()-> new RestaurantNotFoundException("AdminService.RESTAURANT_NOT_FOUND"));
		
		if(rest.getApprovalStatus().equals(ApprovalStatus.Pending.toString()))
		{
			rest.setApprovalStatus(ApprovalStatus.Accepted.toString());
		}
		else
		{
			throw new AdminServiceException("AdminService.INVALID_STATUS");
		}
		return environment.getProperty("AdminService.REGISTRATION_ACCEPTED");
	}
	
	public String rejectRegistrationRequest()
	{
		return null;
	}
}
