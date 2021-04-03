package com.project.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.entity.Users;

public interface UserRepo extends CrudRepository<Users,Integer>{

	public Optional<Users> findByContactNumber(String contactNumber);
}
