package com.project.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.project.entity.Roles;
import com.project.entity.UserAddress;
import com.project.entity.Users;
import com.project.exception.InvalidCredentialsException;
import com.project.exception.UserServiceException;
import com.project.model.LoginCredentialsDto;
import com.project.model.Role;
import com.project.model.RolesDto;
import com.project.model.UserRegisterDto;
import com.project.model.UsersDto;
import com.project.repository.UserRepo;
import com.project.utility.HashingUtility;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	Environment environment;
	
	HashingUtility hashingUtility = new HashingUtility();
	
	@Override
	public String registerUser(UsersDto user) throws UserServiceException, NoSuchAlgorithmException {
		Optional<Users> op = userRepo.findByContactNumber(user.getContactNumber());
		if(op.isPresent())
		{
			//if user already present
			Users userPresent = op.get();
			for(Roles role: userPresent.getRoles())
			{
				if(user.getRoles().get(0).getRoleType().equals(role.getRoleType()))
				{
					throw new UserServiceException("UserService.USER_ALREADY_EXISTS");
				}				
			}
			//add new role into already present customer
			if(user.getRoles()!=null)
			{
				Roles r = new Roles();
				r.setRoleType(user.getRoles().get(0).getRoleType());
				userPresent.getRoles().add(r);
			}
			if(user.getRoles().get(0).getRoleType().equals(Role.CUSTOMER))
			{
				//redirect to add address
				UserAddress address = new UserAddress();
				if(user.getAddressList()!=null)
				{
					System.out.println(user.getAddressList().get(0).getAddressLine1());
					address.setAddressLine1(user.getAddressList().get(0).getAddressLine1());
					address.setAddressLine2(user.getAddressList().get(0).getAddressLine2());
					address.setArea(user.getAddressList().get(0).getArea());
					address.setCity(user.getAddressList().get(0).getCity());
					address.setPincode(user.getAddressList().get(0).getPincode());
					address.setUserAddressName(user.getAddressList().get(0).getUserAddressName());
					address.setUserState(user.getAddressList().get(0).getUserState());
					
				}
				if(userPresent.getAddressList()==null)
				{
					List<UserAddress> newadd = new ArrayList<UserAddress>();
					newadd.add(address);
					userPresent.setAddressList(newadd);
				}
				else
				{
					userPresent.getAddressList().add(address);
				}

			}
			else if(user.getRoles().get(0).getRoleType().equals(Role.VENDOR))
			{
				//can register directly
			}
		}
		else
		{
			//register new user
			Users newUser = new Users();
			newUser.setUserName(user.getUserName());
			newUser.setEmailId(user.getEmailId());
			newUser.setContactNumber(user.getContactNumber());
			newUser.setPassword(hashingUtility.getHashValue(user.getPassword()));
			
			if(user.getRoles() != null)
			{
				Roles role = new Roles();
				role.setRoleType(user.getRoles().get(0).getRoleType());
				List<Roles> roles = new ArrayList<Roles>();
				roles.add(role);
				newUser.setRoles(roles);
			}

			if(user.getRoles().get(0).getRoleType().equals(Role.CUSTOMER))
			{

				//redirect to add address
				UserAddress address = new UserAddress();
				if(user.getAddressList()!=null)
				{
					address.setAddressLine1(user.getAddressList().get(0).getAddressLine1());
					address.setAddressLine2(user.getAddressList().get(0).getAddressLine2());
					address.setArea(user.getAddressList().get(0).getArea());
					address.setCity(user.getAddressList().get(0).getCity());
					address.setPincode(user.getAddressList().get(0).getPincode());
					address.setUserAddressName(user.getAddressList().get(0).getUserAddressName());
					address.setUserState(user.getAddressList().get(0).getUserState());
					
				}
				List<UserAddress> newadd = new ArrayList<UserAddress>();
				newadd.add(address);
				newUser.setAddressList(newadd);

			}
			else if(user.getRoles().get(0).getRoleType().equals(Role.VENDOR))
			{
				//can register directly
			}
			userRepo.save(newUser);
		}
		return environment.getProperty("UserService.USER_REGISTER_SUCCESS");
	}
	
	public UsersDto userLogin(LoginCredentialsDto login) throws InvalidCredentialsException, NoSuchAlgorithmException
	{
		Optional<Users> op = userRepo.findByContactNumber(login.getContactNumber());
		if(op.isPresent())
		{
			Users user = op.get();
			Role role = login.getRole();
			boolean match = false;
			for(Roles r : user.getRoles())
			{
				if(r.getRoleType().toString().equals(role.toString()))
				{
					match = true;
					break;
				}
			}
			if(match && user.getPassword().equals(hashingUtility.getHashValue(login.getPassword())))
			{
				
				//System.out.println("Suucess in service - "+environment.getProperty("UserService.USER_LOGIN_SUCCESS"));
				//return environment.getProperty("UserService.USER_LOGIN_SUCCESS");
				return entityToDto(user);
			}
			else if(match == false)
			{
				throw new InvalidCredentialsException("UserService.INVALID_ROLE");
			}
			else
			{
				throw new InvalidCredentialsException("UserService.INVALID_CREDENTIALS");
			}
		}
		else
		{
			throw new InvalidCredentialsException("UserService.INVALID_CREDENTIALS");
		}
	}
	public UsersDto entityToDto(Users user)
	{
		UsersDto u = new UsersDto();
		u.setContactNumber(user.getContactNumber());
		u.setPassword(user.getPassword());
		List<RolesDto> rolesList = new ArrayList();
		for(Roles r: user.getRoles())
		{
			RolesDto temp = new RolesDto();
			temp.setRoleId(r.getRoleId());
			temp.setRoleType(r.getRoleType());
			rolesList.add(temp);
		}
		u.setRoles(rolesList);
		return u;
	}

	@Override
	public String registerUser(UserRegisterDto userReg) throws UserServiceException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		Optional<Users> op = userRepo.findByContactNumber(userReg.getContactNumber());
		if(op.isPresent())
		{
			//if user already present
			Users userPresent = op.get();
			for(Roles role: userPresent.getRoles())
			{
				if(userReg.getRole().equals(role.getRoleType()))
				{
					throw new UserServiceException("UserService.USER_ALREADY_EXISTS");
				}				
			}
		
			//add new role into already present customer
			if(userPresent.getRoles()!=null)
			{
				Roles r = new Roles();
				r.setRoleType(userReg.getRole());
				userPresent.getRoles().add(r);
			}
			//will add address from profile not from register
//			if(userReg.getRole().equals(Role.CUSTOMER))
//			{
//				//redirect to add address
//				UserAddress address = new UserAddress();
//				if(user.getAddressList()!=null)
//				{
//					System.out.println(user.getAddressList().get(0).getAddressLine1());
//					address.setAddressLine1(user.getAddressList().get(0).getAddressLine1());
//					address.setAddressLine2(user.getAddressList().get(0).getAddressLine2());
//					address.setArea(user.getAddressList().get(0).getArea());
//					address.setCity(user.getAddressList().get(0).getCity());
//					address.setPincode(user.getAddressList().get(0).getPincode());
//					address.setUserAddressName(user.getAddressList().get(0).getUserAddressName());
//					address.setUserState(user.getAddressList().get(0).getUserState());
//					
//				}
//				if(userPresent.getAddressList()==null)
//				{
//					List<UserAddress> newadd = new ArrayList<UserAddress>();
//					newadd.add(address);
//					userPresent.setAddressList(newadd);
//				}
//				else
//				{
//					userPresent.getAddressList().add(address);
//				}
//
//			}
//			else if(user.getRoles().get(0).getRoleType().equals(Role.VENDOR))
//			{
//				//can register directly
//			}
		}
		else
		{
			//register new user
			Users newUser = new Users();
			newUser.setUserName(userReg.getUserName());
			newUser.setEmailId(userReg.getEmailId());
			newUser.setContactNumber(userReg.getContactNumber());
			newUser.setPassword(hashingUtility.getHashValue(userReg.getPassword()));
			
			if(userReg.getRole() != null)
			{
				Roles role = new Roles();
				role.setRoleType(userReg.getRole());
				List<Roles> roles = new ArrayList<Roles>();
				roles.add(role);
				newUser.setRoles(roles);
			}

			// will do in profile to add address
//			if(userReg.getRole().equals(Role.CUSTOMER))
//			{
//
//				//redirect to add address
//				UserAddress address = new UserAddress();
//				if(user.getAddressList()!=null)
//				{
//					address.setAddressLine1(user.getAddressList().get(0).getAddressLine1());
//					address.setAddressLine2(user.getAddressList().get(0).getAddressLine2());
//					address.setArea(user.getAddressList().get(0).getArea());
//					address.setCity(user.getAddressList().get(0).getCity());
//					address.setPincode(user.getAddressList().get(0).getPincode());
//					address.setUserAddressName(user.getAddressList().get(0).getUserAddressName());
//					address.setUserState(user.getAddressList().get(0).getUserState());
//					
//				}
//				List<UserAddress> newadd = new ArrayList<UserAddress>();
//				newadd.add(address);
//				newUser.setAddressList(newadd);
//
//			}
//			else if(user.getRoles().get(0).getRoleType().equals(Role.VENDOR))
//			{
//				//can register directly
//			}
			userRepo.save(newUser);
		}
		return environment.getProperty("UserService.USER_REGISTER_SUCCESS");
	
	}

}
