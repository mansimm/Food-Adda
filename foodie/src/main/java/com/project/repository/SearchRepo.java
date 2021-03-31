package com.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.entity.Restaurant;

public interface SearchRepo extends CrudRepository<Restaurant,Integer>{

}
