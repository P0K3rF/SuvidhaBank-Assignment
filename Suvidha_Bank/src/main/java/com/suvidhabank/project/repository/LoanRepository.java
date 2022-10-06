package com.suvidhabank.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suvidhabank.project.entity.Customer;
import com.suvidhabank.project.entity.Loan;

public interface LoanRepository extends CrudRepository<Loan, String>{
	
	@Query(value = "select * from loan where employee_Id=:empId",nativeQuery = true)
	public List<Loan> findByEmployeeEmployeeId(String empId);
	
	@Query()
	public boolean existsByCustomer(String customerId);
}
