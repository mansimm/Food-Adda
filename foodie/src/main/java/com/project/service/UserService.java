package com.project.service;

import java.security.NoSuchAlgorithmException;

import com.project.exception.UserServiceException;
import com.project.model.UsersDto;

public interface UserService {

	public String registerUser(UsersDto user) throws UserServiceException, NoSuchAlgorithmException;
}
