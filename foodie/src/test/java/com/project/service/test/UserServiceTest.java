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
import com.project.exception.UserServiceException;
import com.project.model.Role;
import com.project.model.RolesDto;
import com.project.model.UserAddressDto;
import com.project.model.UsersDto;
import com.project.repository.UserRepo;
import com.project.service.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {
	@Mock
	UserRepo userRepo;
	@Mock
	Environment environment;
	@InjectMocks
	UserServiceImpl userService = new UserServiceImpl();
	

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

}
