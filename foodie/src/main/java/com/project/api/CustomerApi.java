package com.project.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

import com.project.entity.Orders;
import com.project.exception.UserServiceException;
import com.project.model.LoginCredentialsDto;
import com.project.model.OrderItemsDto;
import com.project.model.RestaurantDto;
import com.project.model.ViewOrdersDto;
import com.project.service.CustomerServiceImpl;

@CrossOrigin
@RestController
@RequestMapping(value="customerAPI")
@Validated
public class CustomerApi {

	@Autowired
	CustomerServiceImpl customerService;
	
	@PostMapping(value="/placeOrder/{contactNumber}")
	public ResponseEntity<Orders> placeOrder(
			@PathVariable(value="contactNumber")
			//@Min(value=6000000000L,message="{UserValidator.INVALID_CONTACT_NUMBER_Min}")
			//@Max(value=9999999999L,message="{UserValidator.INVALID_CONTACT_NUMBER_Max}")
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}") 
			String contactNumber,
			@RequestBody List<OrderItemsDto> orderItemsList) throws UserServiceException
	{
		Orders orders = customerService.placeOrder(orderItemsList, contactNumber);
		return new ResponseEntity(orders,HttpStatus.OK);
	}
	
	@PostMapping(value="/viewOrder/{contactNumber}")
	public ResponseEntity<ViewOrdersDto> placeOrder(
			@PathVariable(value="contactNumber")
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}") 
			String contactNumber) throws UserServiceException
	{
		List<ViewOrdersDto> viewOrdersDto = customerService.viewOrder(contactNumber);
		return new ResponseEntity(viewOrdersDto,HttpStatus.OK);
	}
	
	@GetMapping(value="/viewNearbyRestaurant/{contactNumber}/{area}")
	public ResponseEntity<List<RestaurantDto>> viewNearbyRestaurant(
			@PathVariable(value="contactNumber")
			@Pattern(regexp="[6789][0-9]{9}",message="{UserValidator.INVALID_CONTACT_NUMBER_FORMAT}") 
			String contactNumber,
			@PathVariable(value="area")
			String area) throws UserServiceException
	{
		List<RestaurantDto> rest = customerService.viewNearbyRestaurant(contactNumber, area);
		return new ResponseEntity(rest,HttpStatus.OK);
	}
}
