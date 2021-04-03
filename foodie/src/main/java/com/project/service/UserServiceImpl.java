package com.project.service;

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
import com.project.exception.UserServiceException;
import com.project.model.Role;
import com.project.model.UsersDto;
import com.project.repository.UserRepo;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	Environment environment;
	
	@Override
	public String registerUser(UsersDto user) throws UserServiceException {
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
				
				userPresent.getAddressList().add(address);

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
			newUser.setPassword(user.getPassword());
			
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

}
