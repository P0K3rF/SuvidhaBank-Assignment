package com.suvidhabank.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.suvidhabank.project.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String>{

}
