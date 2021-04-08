package com.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.entity.Orders;

public interface OrdersRepo extends CrudRepository<Orders,Integer> {

}
