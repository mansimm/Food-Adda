package com.project.api;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.exception.AdminServiceException;
import com.project.exception.RestaurantNotFoundException;
import com.project.exception.UserServiceException;
import com.project.model.RestaurantDto;
import com.project.service.AdminServiceImpl;

@CrossOrigin
@RestController
@RequestMapping(value="adminAPI")
@Validated
public class AdminApi {
	
	@Autowired
	AdminServiceImpl adminServiceImpl;
	
	@GetMapping(value="/viewAllRegistrationRequests/{contactNumber}")
	public ResponseEntity<List<RestaurantDto>> viewAllRegistrationRequests(
			@PathVariable
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}") 
			String contactNumber) throws RestaurantNotFoundException, UserServiceException
	{
		List<RestaurantDto> list = adminServiceImpl.viewAllRegistrationRequests(contactNumber);
		return new ResponseEntity(list,HttpStatus.OK);
	}
	
	@PostMapping(value="/accepctRegistrationRequest/{contactNumber}")
	public ResponseEntity<String> accepctRegistrationRequest(
			@PathVariable
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}") 
			String contactNumber,
			@RequestBody
			RestaurantDto restaurantDto) throws UserServiceException, RestaurantNotFoundException, AdminServiceException
	{
		String success = adminServiceImpl.accepctRegistrationRequest(restaurantDto, contactNumber);
		return new ResponseEntity(success,HttpStatus.OK);
	}
	
	@PostMapping(value="/rejectRegistrationRequest/{contactNumber}")
	public ResponseEntity<String> rejectRegistrationRequest(
			@PathVariable
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}") 
			String contactNumber,
			@RequestBody
			RestaurantDto restaurantDto) throws UserServiceException, RestaurantNotFoundException, AdminServiceException
	{
		String success = adminServiceImpl.rejectRegistrationRequest(restaurantDto, contactNumber);
		return new ResponseEntity(success,HttpStatus.OK);
	}
	
	@GetMapping(value="/viewLowRatingRestaurants/{contactNumber}")
	public ResponseEntity<List<RestaurantDto>> viewLowRatingRestaurants(
			@PathVariable
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}") 
			String contactNumber) throws RestaurantNotFoundException, UserServiceException, AdminServiceException
	{
		List<RestaurantDto> list = adminServiceImpl.viewLowRatingRestaurants(contactNumber);
		return new ResponseEntity(list,HttpStatus.OK);
	}
	
	@PostMapping(value="/deleteLowRatingRestaurant/{contactNumber}")
	public ResponseEntity<String> deleteLowRatingRestaurant(
			@PathVariable
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}") 
			String contactNumber,
			@RequestBody
			RestaurantDto restaurantDto) throws RestaurantNotFoundException, UserServiceException, AdminServiceException
	{
		String success = adminServiceImpl.deleteLowRatingRestaurant(restaurantDto,contactNumber);
		return new ResponseEntity(success,HttpStatus.OK);
	}
}
