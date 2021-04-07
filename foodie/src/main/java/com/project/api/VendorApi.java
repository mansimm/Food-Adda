package com.project.api;

import java.util.List;

import javax.validation.Valid;
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

import com.project.exception.RestaurantNotFoundException;
import com.project.exception.UserServiceException;
import com.project.exception.VendorServiceException;
import com.project.model.DishDto;
import com.project.model.RestaurantDto;
import com.project.service.VendorServiceImpl;

@CrossOrigin
@RestController
@RequestMapping(value="vendorAPI")
@Validated
public class VendorApi {
	
	@Autowired
	VendorServiceImpl vendorService;

	@PostMapping(value="/registerRestaurant/{contactNumber}")
	public ResponseEntity<String> registerRestaurant(
			@PathVariable(value="contactNumber")
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}")
			String contactNumber,
			@RequestBody
			@Valid
			RestaurantDto restaurantDto) throws UserServiceException, RestaurantNotFoundException, VendorServiceException
	{
		String success = vendorService.registerRestaurant(restaurantDto, contactNumber);
		return new ResponseEntity(success,HttpStatus.OK);
	}
	
	@GetMapping(value="/viewRestaurantAndMenu/{contactNumber}")
	public ResponseEntity<String> viewRestaurantAndMenu(
			@PathVariable(value="contactNumber")
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}")
			String contactNumber) throws UserServiceException, RestaurantNotFoundException, VendorServiceException
	{
		List<RestaurantDto> list = vendorService.viewRestaurantAndMenu(contactNumber);
		return new ResponseEntity(list,HttpStatus.OK);
	}
	
	@PostMapping(value="/addDish/{contactNumber}/{restaurantId}")
	public ResponseEntity<String> addDish(
			@PathVariable(value="contactNumber")
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}")
			String contactNumber,
			@PathVariable(value="restaurantId")
			Integer restaurantId,
			@RequestBody
			@Valid
			DishDto dishDto) throws UserServiceException, RestaurantNotFoundException, VendorServiceException
	{
		String success = vendorService.addDish( contactNumber, restaurantId,  dishDto);
		return new ResponseEntity(success,HttpStatus.OK);
	}
	

	@PostMapping(value="/updateDish/{contactNumber}/{restaurantId}")
	public ResponseEntity<String> updateDish(
			@PathVariable(value="contactNumber")
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}")
			String contactNumber,
			@PathVariable(value="restaurantId")
			Integer restaurantId,
			@RequestBody
			@Valid
			DishDto dishDto) throws UserServiceException, RestaurantNotFoundException, VendorServiceException
	{
		String success = vendorService.updateDish( contactNumber, restaurantId,  dishDto);
		return new ResponseEntity(success,HttpStatus.OK);
	}
	
	@PostMapping(value="/deleteDish/{contactNumber}/{restaurantId}")
	public ResponseEntity<String> deleteDish(
			@PathVariable(value="contactNumber")
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}")
			String contactNumber,
			@PathVariable(value="restaurantId")
			Integer restaurantId,
			@RequestBody
			@Valid
			DishDto dishDto) throws UserServiceException, RestaurantNotFoundException, VendorServiceException
	{
		String success = vendorService.deleteDish( contactNumber, restaurantId,  dishDto);
		return new ResponseEntity(success,HttpStatus.OK);
	}
}
