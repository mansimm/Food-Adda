package com.project.service.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import com.project.model.UsersDto;
import com.project.repository.UserRepo;
import com.project.service.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {
	@Mock
	UserRepo userRepo;
	@InjectMocks
	UserServiceImpl userService = new UserServiceImpl();
	
	@Test
	public void registerUserInvalidPasswordTest()
	{
		UsersDto user = new UsersDto();
		user.setContactNumber("9876543210");
		user.setEmailId("mansi@gmail.com");
		user.setUserName("mansi");
		user.setPassword("mansi");
		Mockito.when(userRepo.findByContactNumber(user.getContactNumber())).thenReturn(Optional.ofNullable(null));
		
		Exception e = Assertions.assertThrows(Exception.class, ()->userService.registerUser(user));
		Assertions.assertEquals("UserValidator.INVALID_PASSWORD_FORMAT", e.getMessage());
	}

}
