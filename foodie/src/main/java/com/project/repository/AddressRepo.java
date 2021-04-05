package com.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.entity.UserAddress;

public interface AddressRepo extends CrudRepository<UserAddress,Integer>{

}
