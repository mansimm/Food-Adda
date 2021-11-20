package com.project.service;

import java.security.NoSuchAlgorithmException;

import com.project.exception.InvalidCredentialsException;
import com.project.exception.UserServiceException;
import com.project.model.LoginCredentialsDto;
import com.project.model.UserRegisterDto;
import com.project.model.UsersDto;

public interface UserService {

	public String registerUser(UsersDto user) throws UserServiceException, NoSuchAlgorithmException;
	public UsersDto userLogin(LoginCredentialsDto login) throws InvalidCredentialsException, NoSuchAlgorithmException;
	public String registerUser(UserRegisterDto user) throws UserServiceException, NoSuchAlgorithmException;//new method
}
