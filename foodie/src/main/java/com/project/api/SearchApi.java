package com.project.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.RestaurantDto;
import com.project.service.SearchServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("searchAPI")
public class SearchApi {

	@Autowired
	SearchServiceImpl search;
	
	@GetMapping(value="/getAllRestaurants")
	public ResponseEntity<List<RestaurantDto>> getAllRestaurants()
	{
		System.out.println("in api class");
		List<RestaurantDto> res = search.getAllRestaurant();
		return new ResponseEntity<List<RestaurantDto>>(res,HttpStatus.OK);
	}
	
}
