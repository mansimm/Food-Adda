package com.project.api;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.exception.InvalidCredentialsException;
import com.project.exception.UserServiceException;
import com.project.model.LoginCredentialsDto;
import com.project.model.SuccessMessage;
import com.project.model.UsersDto;
import com.project.service.UserServiceImpl;
import com.project.model.UserRegisterDto;

@CrossOrigin
@RestController
@RequestMapping(value="userAPI")
public class UserApi {

	@Autowired
	Environment environment;
	
	@Autowired
	UserServiceImpl userService;
	
	@PostMapping(value="/register")
	public ResponseEntity<SuccessMessage> registerUser(@Valid @RequestBody UsersDto user) throws UserServiceException, NoSuchAlgorithmException
	{
		System.out.println(user);
		String success = userService.registerUser(user);
		SuccessMessage s = new SuccessMessage();
		s.setMessage(success);
		return new ResponseEntity<SuccessMessage>(s,HttpStatus.OK);
	}
	
	@PostMapping(value="/userRegister")
	public ResponseEntity<SuccessMessage> newRegisterUser(@Valid @RequestBody UserRegisterDto userReg) throws UserServiceException, NoSuchAlgorithmException
	{
		System.out.println(userReg);
		String success = userService.registerUser(userReg);
		SuccessMessage s = new SuccessMessage();
		s.setMessage(success);
		return new ResponseEntity<SuccessMessage>(s,HttpStatus.OK);
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<UsersDto> userLogin(@Valid @RequestBody LoginCredentialsDto login) throws NoSuchAlgorithmException, InvalidCredentialsException
	{
		UsersDto loggedinUser = userService.userLogin(login);
		//SuccessMessage s = new SuccessMessage();
		//s.setMessage(success);
		return new ResponseEntity(loggedinUser,HttpStatus.OK);
	}
}
