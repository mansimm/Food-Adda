package com.project.service.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.entity.Roles;
import com.project.entity.Users;
import com.project.exception.InvalidCredentialsException;
import com.project.exception.UserServiceException;
import com.project.model.LoginCredentialsDto;
import com.project.model.Role;
import com.project.model.RolesDto;
import com.project.model.UserAddressDto;
import com.project.model.UsersDto;
import com.project.repository.UserRepo;
import com.project.service.UserServiceImpl;
import com.project.utility.HashingUtility;

@SpringBootTest
public class UserServiceTest {
	@Mock
	UserRepo userRepo;
	@Mock
	Environment environment;
	@InjectMocks
	UserServiceImpl userService = new UserServiceImpl();
	
	LoginCredentialsDto login;
	Users userEntity;
	UsersDto user;

	@Test
	public void registerUserExceptionTest() {
		// to pass argument to register user method
		UsersDto user = new UsersDto();
		user.setContactNumber("9876543210");
		user.setEmailId("mansi@gmail.com");
		user.setUserName("mansi");
		user.setPassword("Mansi@13");
		List<RolesDto> roles = new ArrayList<RolesDto>();
		RolesDto role = new RolesDto();
		role.setRoleType(Role.CUSTOMER);
		roles.add(role);
		user.setRoles(roles);

		// to return as entity
		Users userEntity = new Users();
		userEntity.setContactNumber("9876543210");
		userEntity.setEmailId("mansi@gmail.com");
		userEntity.setUserName("mansi");
		userEntity.setPassword("Mansi@13");
		List<Roles> rolesEntity = new ArrayList<Roles>();
		Roles roleEntity = new Roles();
		roleEntity.setRoleType(Role.CUSTOMER);
		roleEntity.setRoleId(1);

		rolesEntity.add(roleEntity);
		userEntity.setRoles(rolesEntity);
		Mockito.when(userRepo.findByContactNumber(user.getContactNumber())).thenReturn(Optional.ofNullable(userEntity));

		Exception e = Assertions.assertThrows(UserServiceException.class, () -> userService.registerUser(user));
		Assertions.assertEquals("UserService.USER_ALREADY_EXISTS", e.getMessage());
	}

	@Test
	public void registerUserExistingUserValidTest() throws NoSuchAlgorithmException, UserServiceException {
		// to pass argument to register user method
		UsersDto user = new UsersDto();
		user.setContactNumber("9876543210");
		user.setEmailId("mansi@gmail.com");
		user.setUserName("mansi");
		user.setPassword("Mansi@13");
		List<RolesDto> roles = new ArrayList<RolesDto>();
		RolesDto role = new RolesDto();
		role.setRoleType(Role.CUSTOMER);
		roles.add(role);
		user.setRoles(roles);
		List<UserAddressDto> addressList = new ArrayList<UserAddressDto>();
		UserAddressDto add = new UserAddressDto();
		add.setAddressLine1("addline1");
		add.setAddressLine2("addline2");
		add.setArea("Noida");
		add.setCity("Bombay");
		add.setPincode("987657");
		add.setUserAddressName("myaddress");
		add.setUserState("maharashtra");
		addressList.add(add);

		// to return as entity
		Users userEntity = new Users();
		userEntity.setContactNumber("9876543210");
		userEntity.setEmailId("mansi@gmail.com");
		userEntity.setUserName("mansi");
		userEntity.setPassword("Mansi@13");
		List<Roles> rolesEntity = new ArrayList<Roles>();
		Roles roleEntity = new Roles();
		roleEntity.setRoleType(Role.VENDOR);
		roleEntity.setRoleId(1);

		rolesEntity.add(roleEntity);
		userEntity.setRoles(rolesEntity);
		//Mocking repository method
		Mockito.when(userRepo.findByContactNumber(user.getContactNumber())).thenReturn(Optional.ofNullable(userEntity));
		
		String success = userService.registerUser(user);
		Assertions.assertEquals(environment.getProperty("UserService.USER_REGISTER_SUCCESS"), success);

	}
	
	@Test
	public void registerUserNewUserValidTest() throws NoSuchAlgorithmException, UserServiceException {
		// to pass argument to register user method
		UsersDto user = new UsersDto();
		user.setContactNumber("9876543210");
		user.setEmailId("mansi@gmail.com");
		user.setUserName("mansi");
		user.setPassword("Mansi@13");
		List<RolesDto> roles = new ArrayList<RolesDto>();
		RolesDto role = new RolesDto();
		role.setRoleType(Role.CUSTOMER);
		roles.add(role);
		user.setRoles(roles);
		List<UserAddressDto> addressList = new ArrayList<UserAddressDto>();
		UserAddressDto add = new UserAddressDto();
		add.setAddressLine1("addline1");
		add.setAddressLine2("addline2");
		add.setArea("Noida");
		add.setCity("Bombay");
		add.setPincode("987657");
		add.setUserAddressName("myaddress");
		add.setUserState("maharashtra");
		addressList.add(add);

		// to return as entity as null
		//Mocking repository method
		Mockito.when(userRepo.findByContactNumber(user.getContactNumber())).thenReturn(Optional.ofNullable(null));
		
		String success = userService.registerUser(user);
		Assertions.assertEquals(environment.getProperty("UserService.USER_REGISTER_SUCCESS"), success);

	}
	
	@Test
	public void userLoginSuccessTest() throws NoSuchAlgorithmException, InvalidCredentialsException
	{
		init();
		Mockito.when(userRepo.findByContactNumber(login.getContactNumber())).thenReturn(Optional.of(userEntity));
		
		UsersDto ans = userService.userLogin(login);
		//System.out.println("\n	login.getPassword()="+login.getPassword()+"\n ans.getPassword()="+ans.getPassword());
		Assertions.assertEquals(HashingUtility.getHashValue(login.getPassword()), ans.getPassword());
	}
	//Test for user not present in database
	@Test
	public void userLoginExceptionTest1() throws NoSuchAlgorithmException, InvalidCredentialsException
	{
		init();
		Mockito.when(userRepo.findByContactNumber(login.getContactNumber())).thenReturn(Optional.ofNullable(null));
		
		Exception e = Assertions.assertThrows(InvalidCredentialsException.class, ()->userService.userLogin(login));
		//System.out.println("	environment.getProperty(\"UserService.INVALID_CREDENTIALS\")="+environment.getProperty("UserService.INVALID_CREDENTIALS"));
		//System.out.println("	e.getMessage()="+e.getMessage());
		Assertions.assertEquals("UserService.INVALID_CREDENTIALS", e.getMessage());
	}
	//test for different password
	@Test
	public void userLoginExceptionTest2() throws NoSuchAlgorithmException, InvalidCredentialsException
	{
		init();
		userEntity.setPassword("SomethingElse11!");
		Mockito.when(userRepo.findByContactNumber(login.getContactNumber())).thenReturn(Optional.of(userEntity));
		
		Exception e = Assertions.assertThrows(InvalidCredentialsException.class, ()->userService.userLogin(login));
		//System.out.println("	environment.getProperty(\"UserService.INVALID_CREDENTIALS\")="+environment.getProperty("UserService.INVALID_CREDENTIALS"));
		//System.out.println("	e.getMessage()="+e.getMessage());
		Assertions.assertEquals("UserService.INVALID_CREDENTIALS", e.getMessage());
	}
	//Test for different roles
	@Test
	public void userLoginExceptionTest3() throws NoSuchAlgorithmException, InvalidCredentialsException
	{
		init();
		List<Roles> rolesEntity = new ArrayList<Roles>();
		Roles roleEntity = new Roles();
		roleEntity.setRoleType(Role.CUSTOMER);
		roleEntity.setRoleId(1);
		userEntity.setRoles(rolesEntity);
		Mockito.when(userRepo.findByContactNumber(login.getContactNumber())).thenReturn(Optional.of(userEntity));
		
		Exception e = Assertions.assertThrows(InvalidCredentialsException.class, ()->userService.userLogin(login));
		//System.out.println("	environment.getProperty(\"UserService.INVALID_CREDENTIALS\")="+environment.getProperty("UserService.INVALID_CREDENTIALS"));
		//System.out.println("	e.getMessage()="+e.getMessage());
		Assertions.assertEquals("UserService.INVALID_CREDENTIALS", e.getMessage());
	}
	public void init() throws NoSuchAlgorithmException
	{
		login = new LoginCredentialsDto();
		login.setContactNumber("1234567890");
		login.setPassword("passD123word!");
		login.setRole(Role.CUSTOMER);
		
		// to pass argument to register user method
				user = new UsersDto();
				user.setContactNumber("1234567890");
				user.setEmailId("micky@gmail.com");
				user.setUserName("micky");
				user.setPassword("passD123word!");
				List<RolesDto> roles = new ArrayList<RolesDto>();
				RolesDto role = new RolesDto();
				role.setRoleType(Role.CUSTOMER);
				roles.add(role);
				user.setRoles(roles);

				// to return as entity
				userEntity = new Users();
				userEntity.setContactNumber("1234567890");
				userEntity.setEmailId("micky@gmail.com");
				userEntity.setUserName("micky");
				userEntity.setPassword(HashingUtility.getHashValue("passD123word!"));
				List<Roles> rolesEntity = new ArrayList<Roles>();
				Roles roleEntity = new Roles();
				roleEntity.setRoleType(Role.CUSTOMER);
				roleEntity.setRoleId(1);

				rolesEntity.add(roleEntity);
				userEntity.setRoles(rolesEntity);
	}

}
