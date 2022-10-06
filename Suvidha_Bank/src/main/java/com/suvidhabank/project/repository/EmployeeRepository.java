package com.suvidhabank.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.suvidhabank.project.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
