package com.project.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.exception.RestaurantNotFoundException;
import com.project.model.RestaurantDto;
import com.project.model.UsersDto;
import com.project.service.SearchServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("searchAPI")
public class SearchApi {

	@Autowired
	SearchServiceImpl search;
	
	@Autowired
	Environment environment;
	
	@GetMapping(value="/getAllRestaurants")
	public ResponseEntity<List<RestaurantDto>> getAllRestaurants() throws RestaurantNotFoundException
	{
		List<RestaurantDto> res = search.getAllRestaurant();
		return new ResponseEntity<List<RestaurantDto>>(res,HttpStatus.OK);
		
	}
	
	
}
